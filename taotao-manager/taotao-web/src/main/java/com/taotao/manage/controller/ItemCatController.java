package com.taotao.manage.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.impl.ItemCatServiceImpl;


@Controller
@RequestMapping(value="item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatServiceImpl itemCatService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryItemCatTree(@RequestParam(value="id",defaultValue="0")Long id){
		try {
			ItemCat record = new ItemCat();
			record.setParentId(id);
			List<ItemCat> list = itemCatService.queryListByWhere(record);
			if(CollectionUtils.isEmpty(list)){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}else{
				return ResponseEntity.ok(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<ItemCat> queryById(@PathVariable("id")Long id){
		try {
			ItemCat itemCat = this.itemCatService.queryById(id);
			if(itemCat == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok().body(itemCat);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
