package com.taotao.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.pojo.ItemCatData;
import com.taotao.manage.pojo.ItemCatResult;
import com.taotao.manage.service.BaseService;

@Service
public class ItemCatServiceImpl extends BaseService<ItemCat>{

	public ItemCatResult queryAllToTree() {
		List<ItemCat> list = super.queryAll();
		//new一个map用来存放树结构 u和n其实都需要id 所以用long来代表
		Map<Long,List<ItemCat>> map = new HashMap<Long,List<ItemCat>>();
		//把所有的parentId和parentId对应的itemCat对象存放进去先存放进
		for (ItemCat itemCat : list) {
			if(!map.containsKey(itemCat.getParentId())){
				map.put(itemCat.getParentId(), new ArrayList<ItemCat>());
			}
			map.get(itemCat.getParentId()).add(itemCat);
		}
		
		//创建结果对象
		ItemCatResult result = new ItemCatResult();
		//先要创建一级类目 （0L）总共有三个类目
		List<ItemCat> level1 = map.get(0L);
		for (ItemCat itemCat : level1) {
			ItemCatData data1 = new ItemCatData();
			result.getItemCats().add(data1);
			data1.setUrl("/products/"+itemCat.getId()+".html");
			data1.setName("<a href='"+ data1.getUrl() +"'>"+ itemCat.getName() +"</a>");
			//一级类目的List<itemCat>还没创建 这些要在创建二级类目是添加进去
			List<ItemCatData> level2DataList = new ArrayList<>();
			data1.setItems(level2DataList);
			List<ItemCat> level2 = map.get(itemCat.getId());
			for (ItemCat itemCat2 : level2) {
				//添加二级类目的u和n
				ItemCatData data2 = new ItemCatData();
				level2DataList.add(data2);
				data2.setUrl("/products/"+itemCat2.getId()+".html");
				data2.setName(itemCat2.getName());
				List<String> level3DataList = new ArrayList<>();
				data2.setItems(level3DataList);
				List<ItemCat> level3 = map.get(itemCat2.getId());
				for (ItemCat itemCat3 : level3) {
					level3DataList.add("/products/" + itemCat3.getId() + ".html|"+itemCat3.getName());
				}
			}
		}
		return result;
	}
}
