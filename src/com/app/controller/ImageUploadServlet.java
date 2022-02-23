package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.app.util.DateUtil;

/**
 * 上传图片
 *
 */
public class ImageUploadServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		this.doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String root = req.getSession().getServletContext().getRealPath("");//部署后的项目根路径
		try {
			List<FileItem> list = upload.parseRequest(req);
			for(FileItem fileItem:list) {
				String imageName = DateUtil.getCurrentDateStr();  //用当前时间做文件名
				String newPath = "/upload/"+imageName+"."+fileItem.getName().split("\\.")[1];
				File file = new File(root+newPath);
				fileItem.write(file);
				PrintWriter out = resp.getWriter();
				out.println("<script>parent.setImg('"+newPath+"');document.write('上传成功');</script>");
				out.flush();
			}
		} catch (FileUploadException e) {
			
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}



