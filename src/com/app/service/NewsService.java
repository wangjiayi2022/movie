package com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bean.News;
import com.app.bean.NewsExample;
import com.app.bean.NewsExample.Criteria;
import com.app.mapper.NewsMapper;
import com.app.util.StringUtilsEx;
import com.github.pagehelper.PageHelper;

/**
 * 
 * 新闻service
 *
 */
@Service
public class NewsService {
	
	@Autowired
	private NewsMapper mapper;
	
	
	/**
	 * 根据标题模糊查询新闻列表
	 */
	public List<News> list(String title){
		NewsExample ex = new NewsExample();
		Criteria cr = ex.createCriteria();
		if(StringUtilsEx.isNotEmpty(title)) {
			cr.andTitleLike("%"+title+"%");
		}
		return mapper.selectByExample(ex);
	}
	
	/**
	 * 根据主键id查询一个新闻
	 */
	public News getById(String id) {
		return mapper.selectByPrimaryKey(Integer.parseInt(id));
	}
	
	/**
	 * 新增一个新闻
	 */
	public int add(News news) {
		int i = 0;
		try {
			news.setPublishdate(new Date());
			i = mapper.insert(news);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 修改一个新闻
	 */
	public int update(News news) {
		return mapper.updateByPrimaryKeyWithBLOBs(news);
	}
	
	/**
	 * 删除一个新闻
	 */
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(Integer.parseInt(id));
	}
	
	/**
	 * 取最新的三条新闻
	 */
	public List<News> latest3News(){
		PageHelper.startPage(1, 3);
		NewsExample ex = new NewsExample();
		ex.setOrderByClause("publishdate desc");
		return mapper.selectByExample(ex);
	}
}










