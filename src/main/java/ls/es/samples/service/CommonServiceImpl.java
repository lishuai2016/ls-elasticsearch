package ls.es.samples.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;

import com.google.common.collect.Maps;
import ls.es.samples.api.CommonService;
import ls.es.samples.dao.CasOrgPubMapper;
import ls.es.samples.dao.CasUserPubMapper;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import ls.es.samples.entity.*;
import ls.es.samples.util.*;
@Service
public class CommonServiceImpl implements CommonService {
	@Override
	public String testDubbo() {
		return "test dubbo ok!";
	}

	private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Resource
	private CasOrgPubMapper orgMapper;
	@Resource
	private CasUserPubMapper userMapper;

	@Override
	public void initIndex() {
		try {
			IndexOrg.execute();

			IndexUser.execute();
			IndexUserAndOrg.execute();
			List<CasOrgPub> orgs = orgMapper.findAllOrg();
			TransportClient client = ESUtils.getTransportClient();
			BulkRequestBuilder bulkRequest = ESUtils.getTransportClient().prepareBulk().setRefresh(true);
			for (CasOrgPub casOrgPub : orgs) {
				Map<String, Object> orgPub = Maps.newHashMap();
				orgPub.put("id", casOrgPub.getId());
				orgPub.put("org_id", casOrgPub.getOrgId());
				orgPub.put("fullname", casOrgPub.getFullname());
				orgPub.put("license_number", casOrgPub.getLicenseNumber());
				orgPub.put("website", casOrgPub.getWebsite());
				orgPub.put("credit_code", casOrgPub.getCreditCode());
				orgPub.put("supervision_org", casOrgPub.getSupervisionOrg());
				orgPub.put("complaint_tel", casOrgPub.getComplaintTel());
				orgPub.put("contact_tel", casOrgPub.getContactTel());
				orgPub.put("complaint_addr", casOrgPub.getComplaintAddr());
				orgPub.put("intro", casOrgPub.getIntro());
				orgPub.put("province", casOrgPub.getProvince());
				orgPub.put("city", casOrgPub.getCity());
				orgPub.put("addr", casOrgPub.getAddr());
				orgPub.put("legal_person", casOrgPub.getLegalPerson());
				orgPub.put("work_range", casOrgPub.getWorkRange());
				orgPub.put("search_keywords", casOrgPub.getSearchKeywords());
				orgPub.put("reward_des", casOrgPub.getRewardDes());
				orgPub.put("punishment_des", casOrgPub.getPunishmentDes());
				IndexRequestBuilder irb = client.prepareIndex(IndexOrg.index_alias, IndexOrg.type_name).setSource(orgPub);
				bulkRequest.add(irb);
//				client.index(Requests.indexRequest(IndexOrg.index_alias).type(IndexOrg.type_name).source(orgPub));
//				request.add(Requests.indexRequest(IndexOrg.index_alias).type(IndexOrg.type_name).source(map));
			}
			
			for (CasOrgPub casOrgPub : orgs) {
				Map<String, Object> orgPub = Maps.newHashMap();
				orgPub.put("casOrgId", casOrgPub.getId());
				orgPub.put("org_org_id", casOrgPub.getOrgId());
				orgPub.put("fullname", casOrgPub.getFullname());
				orgPub.put("license_number", casOrgPub.getLicenseNumber());
				orgPub.put("website", casOrgPub.getWebsite());
				orgPub.put("credit_code", casOrgPub.getCreditCode());
				orgPub.put("supervision_org", casOrgPub.getSupervisionOrg());
				orgPub.put("complaint_tel", casOrgPub.getComplaintTel());
				orgPub.put("contact_tel", casOrgPub.getContactTel());
				orgPub.put("complaint_addr", casOrgPub.getComplaintAddr());
				orgPub.put("orgIntro", casOrgPub.getIntro());
				orgPub.put("province", casOrgPub.getProvince());
				orgPub.put("city", casOrgPub.getCity());
				orgPub.put("addr", casOrgPub.getAddr());
				orgPub.put("legal_person", casOrgPub.getLegalPerson());
				orgPub.put("work_range", casOrgPub.getWorkRange());
				orgPub.put("search_keywords", casOrgPub.getSearchKeywords());
				orgPub.put("reward_des", casOrgPub.getRewardDes());
				orgPub.put("punishment_des", casOrgPub.getPunishmentDes());
				IndexRequestBuilder cz = client.prepareIndex(IndexUserAndOrg.index_alias, IndexUserAndOrg.type_name).setSource(orgPub);
				bulkRequest.add(cz);
//				client.index(Requests.indexRequest(IndexOrg.index_alias).type(IndexOrg.type_name).source(orgPub));
//				request.add(Requests.indexRequest(IndexOrg.index_alias).type(IndexOrg.type_name).source(map));
			}
//			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//			if (bulkResponse.hasFailures()) {
//				// process failures by iterating through each bulk response item
//				System.out.println(bulkResponse.getItems().toString());
//			}
//			bulkRequest = client.prepareBulk();
			List<CasUserPub> users = userMapper.findAllUser();
			for (CasUserPub casUserPub : users) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", casUserPub.getId());
				map.put("realname", casUserPub.getRealname());
				map.put("sex", casUserPub.getSex());
				if(casUserPub.getBirth()!=null){
					map.put("birth", new SimpleDateFormat("yyyy-MM-dd").format(casUserPub.getBirth()));
				}
				if(casUserPub.getWorkType()==null ||  casUserPub.getWorkType()== 0){
					map.put("work_type", "兼职");
				}else{
					map.put("work_type", "专职");
				}
				map.put("zyzh", casUserPub.getZyzh());
				map.put("work_range", casUserPub.getWorkRange());
				map.put("degree", casUserPub.getDegree());
				map.put("title", casUserPub.getTitle());
				map.put("intro", casUserPub.getIntro());
				map.put("addr", casUserPub.getAddr());
				map.put("province", casUserPub.getProvince());
				map.put("orgname", casUserPub.getOrgName());
				map.put("user_type", casUserPub.getUserType());
				map.put("org_id", casUserPub.getOrgId());
				map.put("regist_time", casUserPub.getRegistTime());
				map.put("org_pub_id", casUserPub.getOrgPubId());
				map.put("city", casUserPub.getCity());
				IndexRequestBuilder irb = client.prepareIndex(IndexUser.index_alias, IndexUser.type_name).setSource(map);
				bulkRequest.add(irb);
//				response = client.index(Requests.indexRequest(IndexUser.index_alias).type(IndexUser.type_name).source(map));
//				System.out.println("version: " + response.get().getVersion());
//				request.add(Requests.indexRequest(IndexUser.index_alias).type(IndexUser.type_name).source(map));
			}
			for (CasUserPub casUserPub : users) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("casUserId", casUserPub.getId());
				map.put("realname", casUserPub.getRealname());
				map.put("sex", casUserPub.getSex());
				if(casUserPub.getBirth()!=null){
					map.put("birth", new SimpleDateFormat("yyyy-MM-dd").format(casUserPub.getBirth()));
				}
				if(casUserPub.getWorkType()==null ||  casUserPub.getWorkType() == 0){
					map.put("work_type", "兼职");
				}else{
					map.put("work_type", "专职");
				}
				map.put("zyzh", casUserPub.getZyzh());
				map.put("work_range", casUserPub.getWorkRange());
				map.put("degree", casUserPub.getDegree());
				map.put("title", casUserPub.getTitle());
				map.put("cas_user_intro", casUserPub.getIntro());
				map.put("province", casUserPub.getProvince());
				map.put("addr", casUserPub.getAddr());
				map.put("orgname", casUserPub.getOrgName());
				map.put("user_type", casUserPub.getUserType());
				map.put("org_id", casUserPub.getOrgId());
				map.put("regist_time", casUserPub.getRegistTime());
				map.put("org_pub_id", casUserPub.getOrgPubId());
				map.put("city", casUserPub.getCity());
				IndexRequestBuilder cz = client.prepareIndex(IndexUserAndOrg.index_alias, IndexUserAndOrg.type_name).setSource(map);
				bulkRequest.add(cz);
//				response = client.index(Requests.indexRequest(IndexUser.index_alias).type(IndexUser.type_name).source(map));
//				System.out.println("version: " + response.get().getVersion());
//				request.add(Requests.indexRequest(IndexUser.index_alias).type(IndexUser.type_name).source(map));
			}
//			ActionFuture<BulkResponse> response = client.bulk(request);
			bulkRequest.execute().actionGet();
			client.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<CasOrgPub> findAllOrg() {
		List<CasOrgPub> result = orgMapper.findAllOrg();
		return result;
	}

	@Override
	public Pagination findOrg(SearchParam param) {
		TransportClient client = ESUtils.getTransportClient();
		String[] queryFields = { "fullname", "work_range", "addr", "legal_person", "contact_tel","license_number","credit_code"};
		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		if(StringUtils.isNotEmpty(param.getKey())){
			bqb.must(QueryBuilders.multiMatchQuery(param.getKey(), queryFields));
		}
		if(StringUtils.isNotEmpty(param.getProvince())){
			bqb.must(QueryBuilders.matchPhraseQuery("province",param.getProvince()));
		}
		if(StringUtils.isNotEmpty(param.getRange())){
			bqb.must(QueryBuilders.matchQuery("work_range",param.getRange()));
		}
		SearchResponse res = client.prepareSearch(IndexOrg.index_alias)
				.setTypes(IndexOrg.type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(bqb)
				.setFrom(param.getStart()).setSize(param.getSize())
				.addHighlightedField("license_number")
				.addHighlightedField("credit_code")
				.addHighlightedField("website")
				.addHighlightedField("supervision_org")
				.addHighlightedField("legal_person")
				.addHighlightedField("contact_tel")
				.addHighlightedField("complaint_tel")
				.addHighlightedField("fullname")
				.addHighlightedField("complaint_addr")
				.addHighlightedField("addr")
				.addHighlightedField("city")
				.addHighlightedField("contact_tel")
				.addHighlightedField("addr")
				.addHighlightedField("intro")
				.addHighlightedField("province")
				.addHighlightedField("work_range")
				.setHighlighterPreTags("<span style=\"color:red\">")
		        .setHighlighterPostTags("</span>")
				.execute().actionGet();
		List<CasOrgPub> result = new ArrayList<CasOrgPub>();
		for (SearchHit hit : res.getHits().getHits()) {
			Map<String,Object> orgMap = hit.getSource();
			CasOrgPub orgPub = new CasOrgPub();
			orgPub.setId((Integer)orgMap.get("id"));
			orgPub.setOrgId((Integer)orgMap.get("org_id"));
			orgPub.setFullname((String)orgMap.get("fullname"));
			orgPub.setLicenseNumber((String)orgMap.get("license_number"));
			orgPub.setCreditCode((String)orgMap.get("credit_code"));
			orgPub.setWebsite((String)orgMap.get("website"));
			orgPub.setLegalPerson((String)orgMap.get("legal_person"));
			orgPub.setSupervisionOrg((String)orgMap.get("supervision_org"));
			orgPub.setComplaintTel((String)orgMap.get("complaint_tel"));
			orgPub.setComplaintAddr((String)orgMap.get("complaint_addr"));
			orgPub.setAddr((String)orgMap.get("addr"));
			orgPub.setContactTel((String)orgMap.get("contact_tel"));
			orgPub.setIntro((String)orgMap.get("intro"));
			orgPub.setProvince((String)orgMap.get("province"));
			orgPub.setWorkRange((String)orgMap.get("work_range"));
			// 获取对应的高亮域
            Map<String, HighlightField> hresutl = hit.highlightFields();
            HighlightField fullnameField = hresutl.get("fullname");
            if (fullnameField !=null) {
                // 取得定义的高亮标签
                Text[] fullnameTexts = fullnameField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : fullnameTexts) {
                    tmp += text;
                }
                orgPub.setFullname(tmp);
            }
            HighlightField addrField = hresutl.get("addr");
            if (addrField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = addrField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	orgPub.setAddr(tmp);
            }
            HighlightField contactTelField = hresutl.get("contact_tel");
            if (contactTelField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = contactTelField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	orgPub.setContactTel(tmp);
            }
            HighlightField legalPersonField = hresutl.get("legal_person");
            if (legalPersonField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = legalPersonField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	orgPub.setLegalPerson(tmp);
            }
            HighlightField creditCodeField = hresutl.get("credit_code");
            if (creditCodeField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = creditCodeField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	orgPub.setCreditCode(tmp);
            }
            HighlightField complainTelField = hresutl.get("complaint_tel");
            if (complainTelField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = complainTelField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	orgPub.setComplaintTel(tmp);
            }
    /*        HighlightField introField = hresutl.get("intro");
            if (introField !=null) {
                // 取得定义的高亮标签
                Text[] tmpTexts = introField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : tmpTexts) {
                    tmp += text;
                }
                orgPub.setIntro(tmp);
            }*/
            HighlightField supervisionField = hresutl.get("supervision_org");
            if (supervisionField !=null) {
            	// 取得定义的高亮标签
            	Text[] tmpTexts = supervisionField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : tmpTexts) {
            		tmp += text;
            	}
            	orgPub.setSupervisionOrg(tmp);
            }
            HighlightField licenseField = hresutl.get("license_number");
            if (licenseField !=null) {
            	// 取得定义的高亮标签
            	Text[] tmpTexts = licenseField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : tmpTexts) {
            		tmp += text;
            	}
            	orgPub.setLicenseNumber(tmp);
            }
            HighlightField provinceField = hresutl.get("province");
            if (provinceField !=null) {
                // 取得定义的高亮标签
                Text[] tmpTexts = provinceField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : tmpTexts) {
                    tmp += text;
                }
                orgPub.setProvince(tmp);
            }
            HighlightField complaintAddrField = hresutl.get("complaint_addr");
            if (complaintAddrField !=null) {
            	// 取得定义的高亮标签
            	Text[] tmpTexts = complaintAddrField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : tmpTexts) {
            		tmp += text;
            	}
            	orgPub.setComplaintAddr(tmp);
            }
            HighlightField rangeField = hresutl.get("work_range");
            if (rangeField !=null) {
                // 取得定义的高亮标签
                Text[] tmpTexts = rangeField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : tmpTexts) {
                    tmp += text;
                }
                orgPub.setWorkRange(tmp);
            }
			result.add(orgPub);
		}
		Pagination page = new Pagination(param.getStart(),param.getSize(),res.getHits().getTotalHits(), result);
		client.close();
		return page;
	}
	@Override
	public List<CasUserPub> findAllUser() {
		return null;
	}

	@Override
	public Pagination findUser(SearchParam param) {
		TransportClient client = ESUtils.getTransportClient();
		String[] queryFields = { "realname","work_range","orgname","addr","zyzh" };
		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		if(StringUtils.isNotEmpty(param.getKey())){
			bqb.must(QueryBuilders.multiMatchQuery(param.getKey(), queryFields));
		}
		if(StringUtils.isNotEmpty(param.getProvince())){
			bqb.must(QueryBuilders.matchPhraseQuery("province",param.getProvince()));
		}
		if(StringUtils.isNotEmpty(param.getRange())){
			bqb.must(QueryBuilders.matchQuery("work_range",param.getRange()));
		}
		SearchResponse res = client.prepareSearch(IndexUser.index_alias)
				.setTypes(IndexUser.type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(bqb)
				.setFrom(param.getStart()).setSize(param.getSize())
				.addHighlightedField("license_number")
				.addHighlightedField("credit_code")
				.addHighlightedField("website")
				.addHighlightedField("supervision_org")
				.addHighlightedField("contact_tel")
				.addHighlightedField("fullname")
				.addHighlightedField("complaint_addr")
				.addHighlightedField("city")
				.addHighlightedField("addr")
				.addHighlightedField("intro")
				.addHighlightedField("province")
				.addHighlightedField("work_range")
				.addHighlightedField("zyzh")
				.addHighlightedField("realname")
				.addHighlightedField("intro")
				.addHighlightedField("work_range")
				.addHighlightedField("orgname")
				.setHighlighterPreTags("<span style=\"color:red\">")
		        .setHighlighterPostTags("</span>")
				.execute().actionGet();
		List<CasUserPub> result = new ArrayList<CasUserPub>();
		for (SearchHit hit : res.getHits().getHits()) {
			Map<String,Object> map = hit.getSource();
			CasUserPub userPub = new CasUserPub();
			userPub.setId((Integer)map.get("id"));
			userPub.setRealname((String)map.get("realname"));
			userPub.setSex((Integer)map.get("sex"));
			try {
				if(map.get("birth")!=null){
					userPub.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse((String)map.get("birth")));
				}
			} catch (ParseException e) {
				userPub.setBirth(null);
			}
			if("兼职".equals((String)map.get("work_type"))){
				userPub.setWorkType(0);
			}else{
				userPub.setWorkType(1);
			}
			userPub.setWorkRange((String)map.get("work_range"));
			userPub.setZyzh((String)map.get("zyzh"));
			userPub.setIntro((String)map.get("intro"));
			userPub.setCity((String)map.get("city"));
			userPub.setAddr((String)map.get("addr"));
			userPub.setProvince((String)map.get("province"));
			userPub.setOrgName((String)map.get("orgname"));
			userPub.setUserType((Integer)map.get("user_type"));
			userPub.setOrgId((Integer)map.get("org_id"));
			userPub.setOrgPubId((Integer)map.get("org_pub_id"));
			try {
				if(map.get("regist_time")!=null){
					userPub.setRegistTime(new SimpleDateFormat("yyyy-MM-dd").parse((String)map.get("regist_time")));
				}
			} catch (ParseException e) {
				userPub.setRegistTime(null);
			}
			// 获取对应的高亮域
            Map<String, HighlightField> hresutl = hit.highlightFields();
            HighlightField realnameField = hresutl.get("realname");
            if (realnameField !=null) {
                // 取得定义的高亮标签
                Text[] fullnameTexts = realnameField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : fullnameTexts) {
                    tmp += text;
                }
                userPub.setRealname(tmp);
            }
            
            HighlightField zyzhField = hresutl.get("zyzh");
            if (zyzhField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = zyzhField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	userPub.setZyzh(tmp);
            }
            HighlightField provinceField = hresutl.get("province");
            if (provinceField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = provinceField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	userPub.setProvince(tmp);
            }
            HighlightField addrField = hresutl.get("addr");
            if (addrField !=null) {
            	// 取得定义的高亮标签
            	Text[] fullnameTexts = addrField.fragments();
            	// 为title串值增加自定义的高亮标签
            	String tmp = "";
            	for (Text text : fullnameTexts) {
            		tmp += text;
            	}
            	userPub.setAddr(tmp);
            }
      /*      
            HighlightField introField = hresutl.get("intro");
            if (introField !=null) {
                // 取得定义的高亮标签
                Text[] tmpTexts = introField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : tmpTexts) {
                    tmp += text;
                }
                userPub.setIntro(tmp);
            }*/
            HighlightField rangeField = hresutl.get("work_range");
            if (rangeField !=null) {
                // 取得定义的高亮标签
                Text[] tmpTexts = rangeField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : tmpTexts) {
                    tmp += text;
                }
                userPub.setWorkRange(tmp);
            }
            HighlightField orgnameField = hresutl.get("orgname");
            if (orgnameField !=null) {
                // 取得定义的高亮标签
                Text[] tmpTexts = orgnameField.fragments();
                // 为title串值增加自定义的高亮标签
                String tmp = "";
                for (Text text : tmpTexts) {
                    tmp += text;
                }
                userPub.setOrgName(tmp);
            }
			result.add(userPub);
		}
		Pagination page = new Pagination(param.getStart(),param.getSize(),res.getHits().getTotalHits(), result);
		client.close();
		return page;
	}
	@Override
	public Pagination findUserAndOrg(SearchParam param) {
		TransportClient client = ESUtils.getTransportClient();
		String[] queryFields = { "fullname","realname","orgname", "work_range", "work_type",  "addr", "legal_person", "contact_tel","license_number","credit_code","zyzh"};
		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		if(StringUtils.isNotEmpty(param.getKey())){
			bqb.must(QueryBuilders.multiMatchQuery(param.getKey(), queryFields));
		//	bqb.must(QueryBuilders.multiMatchQuery(param.getKey(), queryFields));
			//bqb.must(QueryBuilders.multiMatchQuery(param.getKey(), queryFields));
		}
		if(StringUtils.isNotEmpty(param.getProvince())){
			bqb.must(QueryBuilders.matchPhraseQuery("province",param.getProvince()));
		}
		if(StringUtils.isNotEmpty(param.getRange())){
			bqb.must(QueryBuilders.matchQuery("work_range",param.getRange()));
		}
		SearchResponse res = client.prepareSearch(IndexUserAndOrg.index_alias)
				.setTypes(IndexUserAndOrg.type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(bqb)
				.setFrom(param.getStart()).setSize(param.getSize())
				.addHighlightedField("fullname")
				.addHighlightedField("license_number")
				.addHighlightedField("work_range")
				.addHighlightedField("legal_person")
				.addHighlightedField("website")
				.addHighlightedField("supervision_org")
				.addHighlightedField("contact_tel")
				.addHighlightedField("complaint_tel")
				.addHighlightedField("complaint_addr")
				.addHighlightedField("city")
				.addHighlightedField("addr")
				.addHighlightedField("cas_user_intro")
				.addHighlightedField("orgProvince")
				.addHighlightedField("work_range")
				.addHighlightedField("credit_code")
				.addHighlightedField("website")
				.addHighlightedField("fullname")
				.addHighlightedField("orgIntro")
				.addHighlightedField("province")
				.addHighlightedField("city")
				.addHighlightedField("work_range")
				.addHighlightedField("zyzh")
				.addHighlightedField("realname")
				.addHighlightedField("orgname")
				.setHighlighterPreTags("<span style=\"color:red\">")
				.setHighlighterPostTags("</span>")
				.execute().actionGet();
		List<CasUserAndOrgPub> result = new ArrayList<CasUserAndOrgPub>();
		
		for (SearchHit hit : res.getHits().getHits()) {
			Map<String,Object> map = hit.getSource();
			CasUserAndOrgPub casUserAndOrgPub = new CasUserAndOrgPub();
			casUserAndOrgPub.setCasUserId((Integer)map.get("casUserId"));
			casUserAndOrgPub.setRealname((String)map.get("realname"));
			casUserAndOrgPub.setSex((Integer)map.get("sex"));
			try {
				if(map.get("birth")!=null){
					casUserAndOrgPub.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse((String)map.get("birth")));
				}
			} catch (ParseException e) {
				casUserAndOrgPub.setBirth(null);
			}
			casUserAndOrgPub.setSex((Integer)map.get("sex"));
			casUserAndOrgPub.setDegree((String)map.get("degree"));
			casUserAndOrgPub.setTitle((String)map.get("title"));
			if("兼职".equals((String)map.get("work_type"))){
				casUserAndOrgPub.setWorkType(0);
			}else{
				casUserAndOrgPub.setWorkType(1);
			}
			casUserAndOrgPub.setAddr((String)map.get("addr"));
			casUserAndOrgPub.setCity((String)map.get("city"));
			casUserAndOrgPub.setWorkRange((String)map.get("work_range"));
			casUserAndOrgPub.setZyzh((String)map.get("zyzh"));
			casUserAndOrgPub.setUserIntro((String)map.get("cas_user_intro"));
			casUserAndOrgPub.setUserProvince((String)map.get("province"));
			casUserAndOrgPub.setOrgName((String)map.get("orgname"));
			casUserAndOrgPub.setUserType((Integer)map.get("user_type"));
			casUserAndOrgPub.setOrgId((Integer)map.get("org_id"));
			try {
				if(map.get("regist_time")!=null){
					casUserAndOrgPub.setUserRegistTime(new SimpleDateFormat("yyyy-MM-dd").parse((String)map.get("regist_time")));
				}
			} catch (ParseException e) {
				casUserAndOrgPub.setUserRegistTime(null);
			}
			casUserAndOrgPub.setOrgPubId((Integer)map.get("org_pub_id"));
			
			
			casUserAndOrgPub.setCreditCode((String)map.get("credit_code"));
			casUserAndOrgPub.setCasOrgId((Integer)map.get("casOrgId"));
			casUserAndOrgPub.setOrgOrgId((Integer)map.get("org_org_id"));
			casUserAndOrgPub.setFullname((String)map.get("fullname"));
			casUserAndOrgPub.setLicenseNumber((String)map.get("license_number"));
			casUserAndOrgPub.setWebsite((String)map.get("website"));
			casUserAndOrgPub.setSupervisionOrg((String)map.get("supervision_org"));
			casUserAndOrgPub.setComplaintTel((String)map.get("complaint_tel"));
			casUserAndOrgPub.setContactTel((String)map.get("contact_tel"));
			casUserAndOrgPub.setLegalPerson((String)map.get("legal_person"));
			casUserAndOrgPub.setOrgIntro((String)map.get("orgIntro"));
			casUserAndOrgPub.setOrgProvince((String)map.get("province"));
			casUserAndOrgPub.setOrgPubId((Integer)map.get("org_pub_id"));

			// 获取对应的高亮域
			Map<String, HighlightField> hresutl = hit.highlightFields();
			HighlightField realnameField = hresutl.get("realname");
			if (realnameField !=null) {
				// 取得定义的高亮标签
				Text[] fullnameTexts = realnameField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : fullnameTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setRealname(tmp);
			}
			HighlightField legalPersonField = hresutl.get("legal_person");
			if (legalPersonField !=null) {
				// 取得定义的高亮标签
				Text[] fullnameTexts = legalPersonField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : fullnameTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setLegalPerson(tmp);
			}
			HighlightField provinceField = hresutl.get("province");
			if (provinceField !=null) {
				// 取得定义的高亮标签
				Text[] fullnameTexts = provinceField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : fullnameTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setUserProvince(tmp);
			}
			HighlightField cityField = hresutl.get("city");
			if (cityField !=null) {
				// 取得定义的高亮标签
				Text[] fullnameTexts = cityField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : fullnameTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setCity(tmp);
			}
			HighlightField addrField = hresutl.get("addr");
			if (addrField !=null) {
				// 取得定义的高亮标签
				Text[] fullnameTexts = addrField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : fullnameTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setAddr(tmp);
			}
			HighlightField creditCodeField = hresutl.get("credit_code");
			if (creditCodeField !=null) {
				// 取得定义的高亮标签
				Text[] fullnameTexts = creditCodeField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : fullnameTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setCreditCode(tmp);
			}
			
			HighlightField zyzhField = hresutl.get("zyzh");
			if (zyzhField !=null) {
				// 取得定义的高亮标签
				Text[] fullnameTexts = zyzhField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : fullnameTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setZyzh(tmp);
			}
			
	/*		HighlightField userIntroField = hresutl.get("cas_user_intro");
			if (userIntroField !=null) {
				// 取得定义的高亮标签
				Text[] tmpTexts = userIntroField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : tmpTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setUserIntro(tmp);
			}*/
			HighlightField complaintTelField = hresutl.get("complaint_tel");
			if (complaintTelField !=null) {
				// 取得定义的高亮标签
				Text[] tmpTexts = complaintTelField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : tmpTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setComplaintTel(tmp);
			}
			HighlightField complaintAddrField = hresutl.get("complaint_addr");
			if (complaintAddrField !=null) {
				// 取得定义的高亮标签
				Text[] tmpTexts = complaintAddrField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : tmpTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setAddr(tmp);
			}
			HighlightField workRangeField = hresutl.get("work_range");
			if (workRangeField !=null) {
				// 取得定义的高亮标签
				Text[] tmpTexts = workRangeField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : tmpTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setWorkRange(tmp);
			}

			HighlightField orgnameField = hresutl.get("orgname");
			if (orgnameField !=null) {
				// 取得定义的高亮标签
				Text[] tmpTexts = orgnameField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : tmpTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setOrgName(tmp);
			}
			HighlightField contactTelField = hresutl.get("contact_tel");
			if (contactTelField !=null) {
				// 取得定义的高亮标签
				Text[] tmpTexts = contactTelField.fragments();
				// 为title串值增加自定义的高亮标签
				String tmp = "";
				for (Text text : tmpTexts) {
					tmp += text;
				}
				casUserAndOrgPub.setContactTel(tmp);
			}
			   HighlightField fullnameField = hresutl.get("fullname");
	            if (fullnameField !=null) {
	                // 取得定义的高亮标签
	                Text[] fullnameTexts = fullnameField.fragments();
	                // 为title串值增加自定义的高亮标签
	                String tmp = "";
	                for (Text text : fullnameTexts) {
	                    tmp += text;
	                }
	                casUserAndOrgPub.setFullname(tmp);
	            }
	 /*           HighlightField orgIntroField = hresutl.get("orgIntro");
	            if (orgIntroField !=null) {
	                // 取得定义的高亮标签
	                Text[] tmpTexts = orgIntroField.fragments();
	                // 为title串值增加自定义的高亮标签
	                String tmp = "";
	                for (Text text : tmpTexts) {
	                    tmp += text;
	                }
	                casUserAndOrgPub.setOrgIntro(tmp);
	            }*/
	            HighlightField supervisionField = hresutl.get("supervision_org");
	            if (supervisionField !=null) {
	            	// 取得定义的高亮标签
	            	Text[] tmpTexts = supervisionField.fragments();
	            	// 为title串值增加自定义的高亮标签
	            	String tmp = "";
	            	for (Text text : tmpTexts) {
	            		tmp += text;
	            	}
	            	casUserAndOrgPub.setSupervisionOrg(tmp);
	            }
	            HighlightField licenseField = hresutl.get("license_number");
	            if (licenseField !=null) {
	            	// 取得定义的高亮标签
	            	Text[] tmpTexts = licenseField.fragments();
	            	// 为title串值增加自定义的高亮标签
	            	String tmp = "";
	            	for (Text text : tmpTexts) {
	            		tmp += text;
	            	}
	            	casUserAndOrgPub.setLicenseNumber(tmp);
	            }
	       /*     HighlightField provinceField = hresutl.get("province");
	            if (provinceField !=null) {
	                // 取得定义的高亮标签
	                Text[] tmpTexts = provinceField.fragments();
	                // 为title串值增加自定义的高亮标签
	                String tmp = "";
	                for (Text text : tmpTexts) {
	                    tmp += text;
	                }
	                casUserAndOrgPub.setOrgProvince(tmp);
	            }*/
	            result.add(casUserAndOrgPub);
			}
		
		
		Pagination page = new Pagination(param.getStart(),param.getSize(),res.getHits().getTotalHits(), result);
		client.close();
		return page;
	}

	@Override
	public List<CountByProvince> countByProvince(String province) {
		List<CountByProvince> result = orgMapper.countByProvince(province);
		return result;
	}

	@Override
	public Integer countOrg() {
		return orgMapper.countOrg();
	}

	@Override
	public Integer countUser() {
		return userMapper.countUser();
	}

	@Override
	public CasOrgPub findOrgById(Integer id) {
		return orgMapper.findOrgById(id);
	}

	@Override
	public CasUserPub findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	@Override
	public List<CasUserPub> findUserByOrgPubId(Integer id) {
		return userMapper.findUserByOrgPubId(id);
	}

	
//	@Override
//	public CasOrgPub findOrgByOrgId(Integer orgId) {
//		
//		return orgMapper.findOrgByOrgId(orgId);
//	}

}
