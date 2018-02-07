package ls.es.samples.dao;



import ls.es.samples.entity.CasUserPub;

import java.util.List;

public interface CasUserPubMapper {
	List<CasUserPub> findAllUser();
	
	List<CasUserPub> findUserByOrgPubId(Integer id);
	
	CasUserPub findUserById(Integer id);
	/**
	 * 查询鉴定人总数
	 * @return
	 */
	Integer countUser();
}
