package com.taotao.manage.pojo;

public class ItemCatTree extends ItemCat{

	public String getText() {
		return this.getName();
	}

	public String getState() {
		return this.getIsParent() ? "closed":"open";
	}
	
}
