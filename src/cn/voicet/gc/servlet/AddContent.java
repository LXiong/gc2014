package cn.voicet.gc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.voicet.gc.dao.AppDao;
import cn.voicet.gc.util.DotAppSession;

/**
 * 为任务添加内容
 */
@SuppressWarnings("serial")
public class AddContent extends HttpServlet {
	
	private static Logger log = Logger.getLogger(AddContent.class);
	private AppDao appDao;
	
	public void init() throws ServletException {
		WebApplicationContext wac =   
			   WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());   
		appDao = (AppDao) wac.getBean("appDao"); 
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		DotAppSession das = DotAppSession.getVTAppSession(request);
		//
		JSONObject json = new JSONObject();
		if(das.isLogin())
		{
			if(null!=das.telList)
			{
				try {
					das.map.put("content", request.getParameter("content"));
					json.put("code", 0);
					json.put("msg", "添加任务内容成功");
				} catch (Exception e) {
					json.put("code", 4);
					json.put("msg", "请求错误");
				}
			}
			else
			{
				json.put("code", 6);
				json.put("msg", "请先创建一个任务");
			}
		}
		else
		{
			json.put("code", 3);
			json.put("msg", "请先登录");
		}
		out.println(json);
		out.flush();
		out.close();
	}

}
