/**
 * 
 */
package com.zhebei.auth.domain;

import java.io.Serializable;

/**
 * 权限对象模型
 * 
 * @author lichunxi
 * 
 */
public class Policy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1386065446995190119L;

	/**
	 * 唯一标识
	 */
	private Long id;

	/**
	 * 用户
	 */
	private String user;

	/**
	 * 权限对象，可能是资源或者服务接口
	 * <p>
	 * 建议使用uri格式来保证每个对象ID的唯一性，并且使用正则表达式来表示匹配规则
	 * <p>
	 * 例如"/aliyun/thirdpart/mail|/aliyun/thirdpart/fetchrul"，或者等价的
	 * "/aliyun/thirdpart/*"
	 */
	private String objects;

	/**
	 * 操作类型，由业务自行定义
	 * <p>
	 * 使用正则表达式来表示匹配规则
	 * <p>
	 * 例如"apply|cancel"，"listUser|listAddress"或等价的"list*"
	 */
	private String operations;

	/**
	 * 权限生效的条件，支持表达式，语法待定，默认为"*"
	 */
	private String conditions;

	public Policy() {
		super();
	}

	public Policy(Long id, String user, String objects, String operations,
			String conditions) {
		super();
		this.id = id;
		this.user = user;
		this.objects = objects;
		this.operations = operations;
		this.conditions = conditions;
	}

	public Policy(String user, String objects, String operations,
			String conditions) {
		super();
		this.user = user;
		this.objects = objects;
		this.operations = operations;
		this.conditions = conditions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getObjects() {
		return objects;
	}

	public void setObjects(String objects) {
		this.objects = objects;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("policy:{\n");
		buf.append("    id:" + id + ",\n");
		buf.append("    user:" + user + ",\n");
		buf.append("    objects:" + objects + ",\n");
		buf.append("    operations:" + operations + ",\n");
		buf.append("    conditions:" + conditions);
		buf.append("}\n");
		return buf.toString();
	}
}
