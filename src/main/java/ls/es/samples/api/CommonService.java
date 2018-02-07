package ls.es.samples.api;


import ls.es.samples.entity.*;


import java.util.List;

public interface CommonService {

	/**
	 * 初始化搜索索引
	 */
	void initIndex();
	
	
	/**
	 * 通过ID返回机构信息
	 * @return
	 */
	CasOrgPub findOrgById(Integer id);
	/**
	 * 查询所有数据
	 * @return
	 */
	List<CasOrgPub> findAllOrg();
	/**
	 * 通过参数查询机构信息
	 * @param param
	 * @return
	 */
	Pagination findOrg(SearchParam param);
	/**
	 * 查询机构总数
	 * @return
	 */
	Integer countOrg();
	
	
	
	/**
	 * 通过ID查询鉴定人信息
	 * @return
	 */
	CasUserPub findUserById(Integer id);
	/**
	 * 查询所有数据
	 * @return
	 */
	List<CasUserPub> findAllUser();
	/**
	 * 通过机构发布ID查询鉴定人信息
	 * @param
	 * @return
	 */
	List<CasUserPub> findUserByOrgPubId(Integer id);
	/**
	 * 通过参数查询鉴定人信息
	 * @param param
	 * @return
	 */
	Pagination findUser(SearchParam param);
	/**
	 * 查询鉴定人总数
	 * @return
	 */
	Integer countUser();
	/**
	 * 按省进行统计
	 * @return
	 */
	
	
	
	List<CountByProvince> countByProvince(String province);
	/**
	 * 查询所有数据
	 * @return
	 */
	Pagination findUserAndOrg(SearchParam param);
	
//	/**
//	 * 
//	 * @author lishuai
//	 * @data 2017-4-19 上午11:01:13
//	 * @param id
//	 * @return
//	 */
//	CasOrgPub findOrgByOrgId(Integer orgId);
}
