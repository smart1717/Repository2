package com.taotao.manage.service;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;

@SuppressWarnings("unchecked")
public abstract class BaseService<T extends BasePojo>{
	
	@Autowired
	private Mapper<T> mapper;
	
	private Class<T> clazz;
	{
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>)type.getActualTypeArguments()[0];
	}
	
	// 1、	queryById
	public T queryById(Long id){
		T t = this.mapper.selectByPrimaryKey(id);
		return t;
	}
	
	//2、	queryAll
	public List<T> queryAll(){
		List<T> list = this.mapper.select(null);
		return list;
	}
	
	//3、	queryOne
	public T queryOne(T record){
		T t = this.mapper.selectOne(record);
		return t;
	}
	
	//4、	queryListByWhere
	public List<T> queryListByWhere(T record){
		List<T> list = this.mapper.select(record);
		return list;
	}
	
	//5、	queryPageListByWhere
	public PageInfo<T> queryPageLIstByWhere(T record, Integer pageNum, Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = this.mapper.select(record);
		return new PageInfo<>(list);
	}
	
	public PageInfo<T> queryPageListBySort(Integer pageNum, Integer pageSize, String order){
		PageHelper.startPage(pageNum, pageSize);
		Example example = new Example(clazz);
		example.setOrderByClause(order);
		List<T> list = this.mapper.selectByExample(example);
		return new PageInfo<>(list);
	}
	
	//6、	save
	public Integer insert(T record){
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());
		Integer i = this.mapper.insert(record);
		return i;
	}
	
	public Integer insertSelective(T record){
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());
		Integer i = this.mapper.insertSelective(record);
		return i;
	}
	
	//7、	update
	public Integer update(T record){
		record.setCreated(null);
		record.setUpdated(new Date());
		Integer i = this.mapper.updateByPrimaryKey(record);
		return i;
	}
	
	public Integer updateSelective(T record){
		record.setCreated(null);
		record.setUpdated(new Date());
		Integer i = this.mapper.updateByPrimaryKeySelective(record);
		return i;
	}
	
	//8、	deleteById
	public Integer deleteById(Long id){
		Integer i = this.mapper.deleteByPrimaryKey(id);
		return i;
	}
	
	public Integer deleteByWhere(T record){
		Integer i = this.mapper.delete(record);
		return i;
	}
	
	//9、	deleteByIds
	public Integer deleteByIds(String idName, List<Object> ids){
		Example example = new Example(clazz);
		example.createCriteria().andIn(idName, ids);
		Integer i = this.mapper.deleteByExample(example);
		return i;
	}
	
}
