package com.taotao.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ResultUI;
import com.taotao.manage.service.impl.ItemService;

@Controller
@RequestMapping("item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	//
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item, @RequestParam("desc")String desc){
		if(StringUtils.isEmpty(item.getTitle())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try{
			this.itemService.save(item,desc);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<ResultUI> list(
			@RequestParam(value="page", defaultValue = "1")Integer page, 
			@RequestParam(value="rows", defaultValue = "1")Integer rows){
		try{
			PageInfo<Item> pageInfo = this.itemService.queryPageListBySort(page, rows, "updated DESC");
			if(pageInfo == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			ResultUI result = new ResultUI(pageInfo.getTotal(), pageInfo.getList());
			return ResponseEntity.ok(result);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> updateItem(Item item, @RequestParam("desc")String desc){
		try {
			if(StringUtils.isEmpty(item.getTitle())){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			this.itemService.updateItem(item,desc);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		
	}
	
}
