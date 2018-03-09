package com.taotao.manage.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
* @ClassName: PropertiyService 
* @Description: TODO(解决spring父子容器perperties参数调用) 
* @author 朱允为
* @date 2018年1月22日 下午11:31:48 
*
 */
@Service
public class PropertiyService {

	@Value("${IMAGE_BASE_URL}")
	public String IMAGE_BASE_URL;
}
