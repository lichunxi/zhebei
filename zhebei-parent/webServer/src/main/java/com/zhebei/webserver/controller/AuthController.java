/**
 * 
 */
package com.zhebei.webserver.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zhebei.auth.domain.Policy;
import com.zhebei.auth.service.AuthService;
import com.zhebei.webserver.ErrorResult;

/**
 * @author lichunxi
 * 
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

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
	 * @return Object 该记录的主键
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	Object createPolicy(String user, String objects, String operations,
			String conditions) {
		if (!StringUtils.hasLength(user)) {
			throw new IllegalArgumentException("user is empty or null.");
		}
		if (!StringUtils.hasLength(objects)) {
			throw new IllegalArgumentException("objects is empty or null.");
		}
		if (!StringUtils.hasLength(operations)) {
			throw new IllegalArgumentException("operations is empty or null.");
		}
		return authService.createPolicy(user, objects, operations, conditions);
	}

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
	@RequestMapping(value = "/delete")
	@ResponseBody
	int deletePolicy(String user, String objects, String operations) {
		if (!StringUtils.hasLength(user)) {
			throw new IllegalArgumentException("user is empty or null.");
		}
		return authService.deletePolicy(user, objects, operations);
	}

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
	@RequestMapping(value = "/update")
	@ResponseBody
	int updatePolicy(Long policyId, String user, String objects,
			String operations, String conditions) {
		if (policyId == null) {
			throw new IllegalArgumentException("policyId is empty or null.");
		}
		if (!StringUtils.hasLength(user)) {
			throw new IllegalArgumentException("user is empty or null.");
		}
		if (!StringUtils.hasLength(objects)) {
			throw new IllegalArgumentException("objects is empty or null.");
		}
		if (!StringUtils.hasLength(operations)) {
			throw new IllegalArgumentException("operations is empty or null.");
		}
		return authService.updatePolicy(policyId, user, objects, operations,
				conditions);
	}

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
	@RequestMapping(value = "/query")
	@ResponseBody
	List<Policy> readPolicy(String user, String objects, String operations) {
		if (!StringUtils.hasLength(user)) {
			throw new IllegalArgumentException("user is empty or null.");
		}
		return authService.readPolicy(user, objects, operations);
	}

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
	@RequestMapping(value = "/check")
	@ResponseBody
	boolean isAllowed(String user, String objects, String operations,
			Map<String, String> extParams) {
		if (!StringUtils.hasLength(user)) {
			throw new IllegalArgumentException("user is empty or null.");
		}
		if (!StringUtils.hasLength(objects)) {
			throw new IllegalArgumentException("objects is empty or null.");
		}
		if (!StringUtils.hasLength(operations)) {
			throw new IllegalArgumentException("operations is empty or null.");
		}
		return authService.isAllowed(user, objects, operations, extParams);
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object ExceptionHandler(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ErrorResult errorResult = new ErrorResult();
		if (ex instanceof IllegalArgumentException) {
			// 参数错误
			// InvalidParameterValue 400
			errorResult.setCode("InvalidParameterValue");
			errorResult.setMessage(ex.getMessage());
			response.setStatus(400);
		}
		else if (ex instanceof IllegalStateException){
			// 错误状态
			// InvalidParameterValue 400
			errorResult.setCode("InvalidState");
			errorResult.setMessage(ex.getMessage());
			response.setStatus(400);
		}
		return errorResult;

	}

}
