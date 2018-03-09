package com.taotao.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.BaseService;

@Service
public class ItemService extends BaseService<Item>{
	
	@Autowired
	private ItemDescService itemDescService;

	public void save(Item item, String desc) {
		item.setStatus(1);
		item.setId(null);
		super.insert(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.insert(itemDesc);
	}

	public void updateItem(Item item, String desc) {
		item.setStatus(null);
		super.updateSelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDescService.updateSelective(itemDesc);
		
	}

}
