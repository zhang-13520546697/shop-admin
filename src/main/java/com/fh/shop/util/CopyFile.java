package com.fh.shop.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CopyFile {
	
	public static String CopyFile(CommonsMultipartFile img,HttpServletRequest request,String path){
		
		//重命名：时间戳，UUID
				//项目获取UUID
				String uuid=UUID.randomUUID().toString().replace("-", "");
				//截取原图片的后缀名	
				String fileName=img.getOriginalFilename();
				String suffix=fileName.substring(fileName.lastIndexOf("."));
				//获取新的文件名
				String newName=uuid+suffix;		
				//文件的保存路径绝对路径
				String realPath=request.getServletContext().getRealPath("/");
				System.out.println("文件的保存路径绝对路径:"+realPath);
				//在指定的路径下创建一个空文件
				File file=new File(realPath+path+newName);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {	
					//开始复制文件
					img.transferTo(file);
				} catch (IllegalStateException e) {		
					e.printStackTrace();
				} catch (IOException e) {	
					e.printStackTrace();
				}
				
				return  path+newName;
		
	}

}
