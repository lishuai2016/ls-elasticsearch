package ls.es.samples.dao;



import ls.es.samples.entity.CasOrgPub;
import ls.es.samples.entity.CountByProvince;

import java.util.List;

public interface CasOrgPubMapper {
	List<CasOrgPub> findAllOrg();
	/**
	 * 查询机构总数
	 * @return
	 */
	Integer countOrg();
	
	CasOrgPub findOrgById(Integer id);
	
	CasOrgPub findOrgByOrgId(Integer orgId);
	
	List<CountByProvince> countByProvince(String province);
//	/**
//	 * 
//	 * @author lishuai
//	 * @data 2017-4-19 上午11:02:29
//	 * @param orgId
//	 * @return
//	 */
//	CasOrgPub findOrgByOrgId(Integer orgId);
}
