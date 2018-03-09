package com.taotao.manage.pojo;

import java.util.List;

public class ResultUI {

	private Integer total;
	
	private List<?> rows;

	public ResultUI(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public ResultUI(Long total, List<?> rows){
		this.total = total.intValue();
		this.rows = rows;
	}

	public ResultUI() {
	}

	@Override
	public String toString() {
		return "ResultUI [total=" + total + ", rows=" + rows + "]";
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
