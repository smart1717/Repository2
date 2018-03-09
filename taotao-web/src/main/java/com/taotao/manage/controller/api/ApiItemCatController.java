package com.taotao.manage.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.pojo.ItemCatResult;
import com.taotao.manage.service.impl.ItemCatServiceImpl;

@CrossOrigin
@Controller
@RequestMapping("api/item/cat")
public class ApiItemCatController {
	
	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private ItemCatServiceImpl itemCatService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<String> queryItemCat(){
		try {
			ItemCatResult result = itemCatService.queryAllToTree();
			String jsonResult = mapper.writeValueAsString(result);
			return ResponseEntity.ok("category.getDataService("+jsonResult+")");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
