/**
 * 
 */
package com.zhebei.auth.orm;

import java.util.List;

import com.zhebei.auth.domain.Policy;

/**
 * 授权管理接口
 * 
 * @author lichunxi
 * 
 */
public interface AuthorizationMapper {

	/**
	 * 创建权限
	 * 
	 * @param policy，包含如下字段：<p>
	 *  user
	 *            用户标识<p>
	 *  objects
	 *            服务或资源对象标识<p>
	 *  operations
	 *            权限<p>
	 *  conditions
	 *            生效条件<p>
	 * @return int 创建记录的条数
	 */
	int createPolicy(Policy policy);

	/**
	 * 删除用户在某个对象上的权限
	 * 
	 * @param policy，包含如下字段：<p>
	 *  user
	 *            用户标识<p>
	 *  objects
	 *            服务或资源对象标识，如果为null，则仅匹配user和operations<p>
	 *  operations
	 *            权限，如果为null，则仅匹配user和objects
	 * @return int 影响的记录数
	 */
	int deletePolicy(Policy policy);

	/**
	 * 更新权限
	 * 
	 * @param policy，包含如下字段：<p>
	 *  policyId
	 *            Policy标识<p>
	 *  user
	 *            用户标识<p>
	 *  objects
	 *            服务或资源对象标识<p>
	 *  operations
	 *            权限<p>
	 *  conditions
	 *            生效条件
	 * @return int 影响的记录数
	 */
	int updatePolicy(Policy policy);

	/**
	 * 读取用户权限
	 * 
	 * @param policy，包含如下字段：<p>
	 *  user
	 *            用户标识<p>
	 *  objects
	 *            服务或资源对象标识，如果为null，则仅匹配user<p>
	 *  operations
	 *            操作，如果为null，则仅匹配user和object
	 * @return List<Policy> 权限列表
	 */
	List<Policy> readPolicy(Policy policy);

	/**
	 * 查询满足条件的记录数
	 * 
	 * @param policy，包含如下字段：<p>
	 *  user
	 *            用户标识<p>
	 *  objects
	 *            服务或资源对象标识<p>
	 *  operations
	 *            权限<p>
	 *  extParams
	 *            用于condtions字段的额外参数
	 * @return int 满足条件的记录数
	 */
	int findByRegexp(Policy policy);
}
