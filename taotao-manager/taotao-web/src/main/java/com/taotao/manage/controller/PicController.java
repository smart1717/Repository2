package com.taotao.manage.controller;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.manage.pojo.PicResult;
import com.taotao.manage.service.impl.PropertiyService;
import com.taotao.manage.utils.FastDFSClient;

@Controller
@RequestMapping("pic/upload")
public class PicController {

	private static final List<String> EXTENSIONS = Arrays.asList("jpg","png","bmp","jpeg");
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PicController.class);
	
	@Autowired
	private FastDFSClient fastDFSClient;
	
	@Autowired
	private PropertiyService propertiyService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public PicResult uploadPic(@RequestParam("uploadFile")MultipartFile file){
		PicResult result = new PicResult();
		result.setError(1);
		String fileName = file.getOriginalFilename();
		try{
			String extension = StringUtils.substringAfterLast(fileName, ".");
			if(!EXTENSIONS.contains(extension)){
				return result;
			}
			BufferedImage image = ImageIO.read(file.getInputStream());
			if(image == null){
				return result;
			}
			String filePath = fastDFSClient.uploadFile(file.getBytes(), fileName);
			result.setUrl(propertiyService.IMAGE_BASE_URL + filePath);
			System.out.println(filePath);
			LOGGER.info("文件上传成功！文件名：{}；文件id：{}",fileName, filePath);
			result.setError(0);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("文件上传失败！文件名：{}", fileName, e);
			return result;
		}
	}
}
