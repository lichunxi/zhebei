/**
 * 
 */
package com.zhebei.auth.service;

import java.util.List;
import java.util.Map;

import com.zhebei.auth.domain.Policy;

/**
 * 授权管理接口
 * 
 * @author lichunxi
 * 
 */
public interface AuthService {
	/**
	 * 所有操作权限
	 */
	final String ALL_OPERATIONS = ".*";

	/**
	 * 所有的资源
	 */
	// final String ALL_OBJECTS = ".*";

	/**
	 * 创建权限
	 * 
	 * @param user
	 *            用户标识
	 * @param objects
	 *            服务或资源对象标识
	 * @param operations
	 *            权限
	 * @param conditions
	 *            生效条件
	 * @return String 该记录的主键
	 */
	String createPolicy(String user, String objects, String operations,
			String conditions);

	/**
	 * 删除用户在某个对象上的权限
	 * 
	 * @param user
	 *            用户标识
	 * @param objects
	 *            服务或资源对象标识，如果为null，则仅匹配user和operations
	 * @param operations
	 *            权限，如果为null，则仅匹配user和objects
	 * @return int 影响的记录数
	 */
	int deletePolicy(String user, String objects, String operations);

	/**
	 * 更新权限
	 * 
	 * @param policyId
	 *            Policy标识
	 * @param user
	 *            用户标识
	 * @param objects
	 *            服务或资源对象标识
	 * @param operations
	 *            权限
	 * @param conditions
	 *            生效条件
	 * @return int 影响的记录数
	 */
	int updatePolicy(Long policyId, String user, String objects,
			String operations, String conditions);

	/**
	 * 读取用户权限
	 * 
	 * @param user
	 *            用户标识
	 * @param objects
	 *            服务或资源对象标识，如果为null，则仅匹配user
	 * @param operations
	 *            操作，如果为null，则仅匹配user和object
	 * @return List<Policy> 权限列表
	 */
	List<Policy> readPolicy(String user, String objects, String operations);

	/**
	 * 判断用户是否有权限对资源或服务进行相应操作
	 * 
	 * @param user
	 *            用户标识
	 * @param objects
	 *            服务或资源对象标识
	 * @param operations
	 *            权限
	 * @param extParams
	 *            用于condtions字段的额外参数
	 * @return boolean true有权限， false无权限
	 */
	boolean isAllowed(String user, String objects, String operations,
			Map<String, String> extParams);
}
