/**
 * 
 */
package com.zhebei.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhebei.auth.domain.Policy;
import com.zhebei.auth.orm.AuthorizationMapper;

/**
 * @author lichunxi
 *
 */
@Service
public class DefaultAuthService implements AuthService {

	@Autowired
	private AuthorizationMapper authMapper;
	
	/* (non-Javadoc)
	 * @see com.zhebei.auth.service.AuthService#createPolicy(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String createPolicy(String user, String objects, String operations,
			String conditions) {
		Policy policy = new Policy(user, objects, operations, conditions);
		Integer counts = authMapper.findByRegexp(policy);
		// 如果该权限已经存在，则直接返回，不再向数据库中插入该权限
		if (0 < counts) {
			throw new IllegalStateException("the policy exist, you should update it.");
		} 
		authMapper.createPolicy(policy);
		return policy.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.zhebei.auth.service.AuthService#deletePolicy(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int deletePolicy(String user, String objects, String operations) {
		Policy policy = new Policy(user, objects, operations, null);
		return authMapper.deletePolicy(policy);
	}

	/* (non-Javadoc)
	 * @see com.zhebei.auth.service.AuthService#updatePolicy(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int updatePolicy(Long policyId, String user, String objects,
			String operations, String conditions) {
		Policy policy = new Policy(policyId, user, objects, operations,
				conditions);
		return authMapper.updatePolicy(policy);
	}

	/* (non-Javadoc)
	 * @see com.zhebei.auth.service.AuthService#readPolicy(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Policy> readPolicy(String user, String objects,
			String operations) {
		Policy policy = new Policy(user, objects, operations, null);
		return authMapper.readPolicy(policy);
	}

	/* (non-Javadoc)
	 * @see com.zhebei.auth.service.AuthService#isAllowed(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	public boolean isAllowed(String user, String objects, String operations,
			Map<String, String> extParams) {
		Policy policy = new Policy(user, objects, operations, null);
		Integer counts = authMapper.findByRegexp(policy);
		if (0 < counts) {
			return true;
		}
		return false;
	}

}
