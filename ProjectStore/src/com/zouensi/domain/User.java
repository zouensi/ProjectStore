package com.zouensi.domain;

import java.io.Serializable;

/**
 * 
 * @author zouensi
 * @date 2017年7月6日 
 * 描述:用户实体类
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String uid; // 用户主键
	private String username; // 用户名
	private String password; // 密码
	private String name; // 姓名
	private String email; // 邮件地址
	private String telephone; // 电话号码
	private String birthday; // 生日
	private String sex; // 性别
	private Integer state; // 激活状态
	private String code; // 确认码（认证id）
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
