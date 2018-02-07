package ls.es.utils;

import ls.es.bean.Doc;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class Esutil {
	public static Client client = null;

		/**
		 * 获取客户端
		 * @return
		 */
		public static  Client getClient() {
			if(client!=null){
				return client;
			}
			Settings settings = Settings.settingsBuilder().put("cluster.name", "lishuai").build();
			try {
				client = TransportClient.builder().settings(settings).build()
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("master.hadoop"), 9300))
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("slave1.hadoop"), 9300))
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("slave2.hadoop"), 9300));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return client;
		}
	
	
	
	
	public static String addIndex(String index,String type,Doc Doc){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("id", Doc.getId());
		hashMap.put("title", Doc.getTitle());
		hashMap.put("describe", Doc.getDescribe());
		hashMap.put("author", Doc.getAuthor());
		
		IndexResponse response = getClient().prepareIndex(index, type).setSource(hashMap).execute().actionGet();
		return response.getId();
	}
	
	
	public static Map<String, Object> search(String key,String index,String type,int start,int row){
		SearchRequestBuilder builder = getClient().prepareSearch(index);
		builder.setTypes(type);
		builder.setFrom(start);
		builder.setSize(row);
		//设置高亮字段名称
		builder.addHighlightedField("title");
		builder.addHighlightedField("describe");
		//设置高亮前缀
		builder.setHighlighterPreTags("<font color='red' >");
		//设置高亮后缀
		builder.setHighlighterPostTags("</font>");
		builder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		if(StringUtils.isNotBlank(key)){
//			builder.setQuery(QueryBuilders.termQuery("title",key));
			builder.setQuery(QueryBuilders.multiMatchQuery(key, "title","describe"));
		}
		builder.setExplain(true);
		SearchResponse searchResponse = builder.get();
		
		SearchHits hits = searchResponse.getHits();
		long total = hits.getTotalHits();
		Map<String, Object> map = new HashMap<String,Object>();
		SearchHit[] hits2 = hits.getHits();
		map.put("count", total);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SearchHit searchHit : hits2) {
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("title");
			Map<String, Object> source = searchHit.getSource();
			if(highlightField!=null){
				Text[] fragments = highlightField.fragments();
				String name = "";
				for (Text text : fragments) {
					name+=text;
				}
				source.put("title", name);
			}
			HighlightField highlightField2 = highlightFields.get("describe");
			if(highlightField2!=null){
				Text[] fragments = highlightField2.fragments();
				String describe = "";
				for (Text text : fragments) {
					describe+=text;
				}
				source.put("describe", describe);
			}
			list.add(source);
		}
		map.put("dataList", list);
		return map;
	}

	public static void main(String[] args) {

//		Doc doc  = new Doc();
//		doc.setAuthor("lishau1i hbase ");
//		doc.setContent("666666666666666666李帅hbase1  hbase ");
//		doc.setDescribe("999999999999999就的撒hbase1 hbase ");
//		doc.setTitle("李帅测试1 hbase");
//
//		System.out.println(addIndex("bupt","doc",doc));

		Map<String, Object> map = Esutil.search("李帅", "bupt", "doc", 0, 10);

		List<Map<String, Object>> articleList = (List<Map<String, Object>>)map.get("dataList");
		for (Map<String, Object> m : articleList) {
			Set<String> set = m.keySet();
			for (String s : set) {
				System.out.println("key=" + s +"  "+"value="+ m.get(s));
			}
		}

	}
}
