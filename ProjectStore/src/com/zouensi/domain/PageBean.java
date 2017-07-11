package com.zouensi.domain;


import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author zouensi
 * @date 2017年7月9日
 * 描述:分页信息对象
 */
public class PageBean<T> implements Serializable
{
	private static final long serialVersionUID = 1L;
	// 总条数
	private int totalCount;
	// 总页数
	private int totalPage;
	// 当前页
	private int pageNumber;
	// 每页显示的条数
	private int pageSize;
	// 每页显示的数据
	private List<T> list;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "PageBean [totalCount=" + totalCount + ", totalPage="
				+ totalPage + ", pageNumber=" + pageNumber + ", pageSize="
				+ pageSize + ", list=" + list + "]";
	}
	
}
