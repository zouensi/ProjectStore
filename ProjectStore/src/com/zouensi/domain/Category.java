package com.zouensi.domain;

import java.io.Serializable;
/**
 * 
 * @author zouensi
 * @date 2017��7��8��
 * ����:��Ʒ�������
 */
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cid;
	private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	

}
