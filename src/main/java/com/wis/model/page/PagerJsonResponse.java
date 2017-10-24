package com.wis.model.page;

import java.util.List;

public class PagerJsonResponse<T extends Object> {
	/**
	 * Current page
	 */
	private int page;

	/**
	 * Total pages
	 */
	private int total;

	/**
	 * Total number of records
	 */
	private int records;
	private int pagecount;

	/**
	 * Contains the actual data
	 */
	private List<T> rows;

	public PagerJsonResponse() {

	}

	public PagerJsonResponse(List<T> rows, Pager pager) {
		this.rows = rows;
		this.total = pager.getTotalResult();
		this.records = pager.getTotalResult();
		this.page = pager.getPage();
		this.pagecount = pager.getPageCount();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

}
