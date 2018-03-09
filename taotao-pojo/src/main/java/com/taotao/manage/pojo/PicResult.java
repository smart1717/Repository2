package com.taotao.manage.pojo;

public class PicResult {

	private Integer error;
	
	private String url;

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PicResult [error=" + error + ", url=" + url + "]";
	}
	
	
}
