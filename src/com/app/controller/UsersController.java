package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.bean.Users;
import com.app.bean.UsersExample;
import com.app.bean.UsersExample.Criteria;
import com.app.core.ReturnVoCommon;
import com.app.mapper.UsersMapper;
import com.app.util.Consts;
import com.app.util.StringUtilsEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户Controller
 * 
 */

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersMapper usersMapper;
	
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ReturnVoCommon login(Users users,HttpServletRequest request) {
		ReturnVoCommon vo = new ReturnVoCommon();
		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(users.getName());
		criteria.andPasswordEqualTo(users.getPassword());
		criteria.andAuthEqualTo(users.getAuth());
		List<Users> list = usersMapper.selectByExample(example);
		if(list == null || list.size() == 0 ) {
			vo.setCode(-1);
			vo.setErrMsg("用户名和密码错误！");
			return vo;
		}
		if(users.getAuth().equals("注册用户")) {
			vo.setCode(1);
			request.getSession().setAttribute(Consts.CURRENT_USER, list.get(0));
		}else {
			//管理员
			vo.setCode(2);
			request.getSession().setAttribute(Consts.ADMIN_USER, list.get(0));
		}
		return vo;
	}
	
	/**
	 * 注册
	 * @param users
	 * @param request
	 * @return
	 */
	@RequestMapping("/reg")
	@ResponseBody
	public ReturnVoCommon reg(Users users,HttpServletRequest request) {
		ReturnVoCommon vo = new ReturnVoCommon();
		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(users.getName());
		List<Users> list = usersMapper.selectByExample(example);
		if(list != null && list.size() > 0 ) {
			vo.setCode(-1);
			vo.setErrMsg("用户名已存在！");
			return vo;
		}
		users.setAuth("注册用户");
		usersMapper.insert(users);
		return vo;
	}
	
	
	/**
	 * 管理员退出
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		request.getSession().removeAttribute(Consts.ADMIN_USER);
		mav.setViewName("front/login.jsp");
		return mav;
	}
	
	/**
	 * 注册用户列表，带翻页功能
	 * page:当前第几页
	 * rows:每页大小
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer rows){
		Map<String, Object> map = new HashMap<>();
		if(StringUtilsEx.isEmpty(page)) {
			page = 1;
		}
		if(StringUtilsEx.isEmpty(rows)) {
			rows = 5;
		}
		PageHelper.startPage(page, rows);
		//如果没有任何信息，就返回所有用户信息
		List<Users> list = usersMapper.selectByExample(new UsersExample());
		map.put("rows", list);
		PageInfo<Users> pageInfo = new PageInfo<Users>(list);
		map.put("total", pageInfo.getTotal());
		
		return map;
	}
	
	/**
	 * 删除一个用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnVoCommon delete(String id) {
		ReturnVoCommon vo = new ReturnVoCommon();
		vo.setCode(0);
		try {
			usersMapper.deleteByPrimaryKey(Integer.parseInt(id));
		} catch (Exception e) {
			vo.setCode(-1);
			vo.setErrMsg("删除失败！");
		}
		return vo;
	}
	
	
}







