package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.bean.Movies;
import com.app.bean.Orders;
import com.app.bean.Ticket;
import com.app.core.ReturnVoCommon;
import com.app.service.MoviesService;
import com.app.util.StringUtilsEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 电影Controller
 * 
 */

@Controller
@RequestMapping("/movies")
public class MoviesController {
	
	@Autowired
	private MoviesService moviesService;

	/**
	 * 电影列表，带翻页功能
	 * page:当前第几页
	 * rows:每页大小
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(String movieName,Integer page,Integer rows){
		Map<String, Object> map = new HashMap<>();
		if(StringUtilsEx.isEmpty(page)) {
			page = 1;
		}
		if(StringUtilsEx.isEmpty(rows)) {
			rows = 5;
		}
		PageHelper.startPage(page, rows);
		List<Movies> list = moviesService.list(movieName);
		map.put("rows", list);
		PageInfo<Movies> pageInfo = new PageInfo<Movies>(list);
		map.put("total", pageInfo.getTotal());
		
		return map;
	}
	
	/**
	 * 根据主键查询一个电影
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public Movies getById(String id) {
		return moviesService.getById(id);
	}
	
	/**
	 * 增加或修改一个电影
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ReturnVoCommon add(Movies movie) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		try {
			if(StringUtilsEx.isEmpty(movie.getId())) {
				moviesService.add(movie);
			}else {
				moviesService.update(movie);
			}
			
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("操作失败！");
		}
		return vo;
	}
	
	
	/**
	 * 删除一个电影
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnVoCommon delete(String id) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		try {
			moviesService.delete(id);
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("删除失败！");
		}
		return vo;
	}
	
	/**
	 * 设置一个电影为最受期待、好评如潮或者今日票房
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnVoCommon update(String id,String column) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		try {
			moviesService.update(id,column);
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("设置失败！");
		}
		return vo;
	}
	
	/**
	 * 创建某个电影的所有电影票
	 */
	@RequestMapping("/createTickets")
	@ResponseBody
	public ReturnVoCommon createTickets(String movieid) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		//先检查这个电影的电影票是否已生成
		List<Ticket> list = moviesService.getTicketsByMovieId(movieid);
		if(list!=null && list.size()>0) {
			vo.setCode(2);
			return vo;
		}
		try {
			moviesService.createTickets(movieid);
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("生成失败！");
			return vo;
		}
		return vo;
		
	}
	
	/**
	 * 根据电影票ID生成订单
	 * id:电影票的id--ticketId
	 * 
	 */
	@RequestMapping("/addOrder")
	@ResponseBody
	public ReturnVoCommon addOrder(String id) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		try {
			moviesService.addOrder(id);
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("生成失败！");
			return vo;
		}
		return vo;
		
	}
	
	/**
	 * 订单列表，带翻页功能
	 * page:当前第几页
	 * rows:每页大小
	 */
	@RequestMapping("/listOrders")
	@ResponseBody
	public Map<String,Object> listOrders(Integer page,Integer rows){
		Map<String, Object> map = new HashMap<>();
		if(StringUtilsEx.isEmpty(page)) {
			page = 1;
		}
		if(StringUtilsEx.isEmpty(rows)) {
			rows = 5;
		}
		PageHelper.startPage(page, rows);
		List<Orders> list = moviesService.listOrders();
		map.put("rows", list);
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
		map.put("total", pageInfo.getTotal());
		
		return map;
	}
	

	
}







