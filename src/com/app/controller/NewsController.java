package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.bean.News;
import com.app.core.ReturnVoCommon;
import com.app.service.NewsService;
import com.app.util.StringUtilsEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 新闻Controller
 * 
 */

@Controller
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	/**
	 * 新闻列表，带翻页功能
	 * page:当前第几页
	 * rows:每页大小
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(String title,Integer page,Integer rows){
		Map<String, Object> map = new HashMap<>();
		if(StringUtilsEx.isEmpty(page)) {
			page = 1;
		}
		if(StringUtilsEx.isEmpty(rows)) {
			rows = 5;
		}
		PageHelper.startPage(page, rows);
		List<News> list = newsService.list(title);
		map.put("rows", list);
		PageInfo<News> pageInfo = new PageInfo<News>(list);
		map.put("total", pageInfo.getTotal());
		
		return map;
	}
	
	/**
	 * 根据主键查询一个新闻
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public News getById(String id) {
		return newsService.getById(id);
	}
	
	/**
	 * 增加或修改一个新闻
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ReturnVoCommon add(News news) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		try {
			if(StringUtilsEx.isEmpty(news.getId())) {
				newsService.add(news);
			}else {
				News oldNews = newsService.getById(String.valueOf(news.getId()));
				news.setPublishdate(oldNews.getPublishdate());
				newsService.update(news);
				
			}
			
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("操作失败！");
		}
		return vo;
	}
	
	
	/**
	 * 删除一个新闻
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnVoCommon delete(String id) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		try {
			newsService.delete(id);
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("删除失败！");
		}
		return vo;
	}
	
}







