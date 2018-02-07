package ls.es.samples.util;


import com.google.common.collect.Maps;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class IndexOrg {	
	public static String index_name = "index_company";
	public static String index_alias = "index_alias_company";
	public static String type_name = "type_company";
	
	public static void execute() throws IOException, ParseException, InterruptedException, ExecutionException{
		if(index_exists()){
			delete_index();
		}
		create_index_type_mapping();
	}
	
	/**
	 * 创建索引
	 */
	private static void create_index_type_mapping() throws IOException{
		System.out.println("create_index_type_setting_mapping......");
		TransportClient client = ESUtils.getTransportClient();
		
		XContentBuilder builder_mapping = XContentFactory.jsonBuilder().startObject()
				.startObject(type_name)
				.startObject("properties")
					.startObject("id").field("type", "integer").field("store", "yes").endObject()
					.startObject("org_id").field("type", "integer").field("store", "yes").endObject()
					.startObject("fullname").field("type", "string").field("store", "yes").field("analyzer", "ik").endObject()
					.startObject("license_number").field("type", "string").field("store", "yes").endObject()
					.startObject("website").field("type", "string").field("store", "yes").endObject()
					.startObject("supervision_org").field("type", "string").field("store", "yes").endObject()
					.startObject("complaint_tel").field("type", "string").field("store", "yes").endObject()
					.startObject("contact_tel").field("type", "string").field("store", "yes").endObject()
					.startObject("complaint_addr").field("type", "string").field("store", "yes").endObject()
					.startObject("intro").field("type", "string").field("store", "yes").endObject()
					.startObject("province").field("type", "string").field("store", "yes").field("analyzer", "ik").field("search_analyzer", "ik").endObject()
					.startObject("city").field("type", "string").field("store", "yes").endObject()
					.startObject("addr").field("type", "string").field("store", "yes").field("analyzer", "ik").field("search_analyzer", "ik").endObject()
					.startObject("legal_person").field("type", "string").field("store", "yes").field("analyzer", "ik").field("search_analyzer", "ik").endObject()
					.startObject("work_range").field("type", "string").field("analyzer", "ik").field("search_analyzer", "ik").field("store", "yes").endObject()
					.startObject("search_keywords").field("type", "string").field("store", "yes").endObject()
					.startObject("reward_des").field("type", "string").field("store", "yes").endObject()
					.startObject("punishment_des").field("type", "string").field("store", "yes").endObject()
				.endObject()
				.endObject().endObject();
		
		Map<String, String> settings = Maps.newHashMap();
		settings.put("number_of_shards", "10");
		settings.put("number_of_replicas", "1");
		ActionFuture<CreateIndexResponse> response = client.admin().indices().create(Requests.createIndexRequest(index_name).settings(settings).alias(new Alias(index_alias)).mapping(type_name, builder_mapping));
		System.out.println("isAcknowledged: " + response.actionGet().isAcknowledged());
		client.close();
	}
	
	/**
	 * 判断索引是否存在
	 */
	private static boolean index_exists() {
		System.out.println("index_exists......");
		TransportClient client = ESUtils.getTransportClient();
		
		ActionFuture<IndicesExistsResponse> response = client.admin().indices().exists(Requests.indicesExistsRequest(index_alias));
		System.out.println("isExists: " + response.actionGet().isExists());
		client.close();
		return response.actionGet().isExists();
	}
	
	/**
	 * 删除索引
	 */
	private static void delete_index() {
		System.out.println("delete_index......");
		TransportClient client = ESUtils.getTransportClient();
		ActionFuture<DeleteIndexResponse> response = client.admin().indices().delete(Requests.deleteIndexRequest(index_name));
		System.out.println("isContextEmpty: " + response.actionGet().isContextEmpty());
		client.close();
	}
	
	/**
	 * 删除指定文档
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * */
	public static void delete_index(String id) throws InterruptedException, ExecutionException {
		System.out.println("delete_index......");
		TransportClient client = ESUtils.getTransportClient();
		ActionFuture<DeleteResponse> response = client.delete(Requests.deleteRequest(index_alias).type(type_name).id(id));
		System.out.println("isFound: " + response.get().isFound());
		
		client.close();
	}
}
