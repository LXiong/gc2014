package cn.voicet.gc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * 为任务添加号码
 */
@SuppressWarnings("serial")
public class AddTel extends HttpServlet {
	
	private static Logger log = Logger.getLogger(AddTel.class);
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
		if (das.isLogin())
		{
			try 
			{
				if(null!=das.telList)
				{
					String tellist = request.getParameter("tellist");
					String[] tla=tellist.split(",");
					for(int i=0;i<tla.length;i++)
					{
						if (tla[i].length()>0)
						{
							das.telList.add(tla[i]);
						}
					}
					json.put("code", 0);
					json.put("msg", "添加号码成功");
					json.put("Total_number", tla.length);
				}
				else
				{
					json.put("code", 6);
					json.put("msg", "请先创建一个任务");
				}
				
			} 
			catch (Exception e) 
			{
				json.put("code", 4);
				json.put("msg", "请求错误");
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
