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
 * 提交任务
 */
@SuppressWarnings("serial")
public class CommitTask extends HttpServlet {
	
	private static Logger log = Logger.getLogger(CommitTask.class);
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
		JSONObject json = new JSONObject();
		
		//
		if (das.isLogin())
		{
			if(null!=das.telList)
			{
				String tContent=(String)das.map.get("content");
				if(tContent.length()>0)
				{
					if(das.telList.size()>0)
					{
						try {
							String str = request.getParameter("active");
							if(null!=str && (str.equals("true")||str.equals("1")))
								das.iTaskState=1;
							else
								das.iTaskState=0;
							appDao.addNewTaskInfo(das);
							appDao.addTelInfo(das);
							das.map = null;
							das.telList=null;
							json.put("code", 0);
							json.put("tid", das.tid);
							json.put("msg", "任务提交成功");
						} catch (Exception e) {
							json.put("code", 4);
							json.put("msg", "请求错误");
						}
					}
					else
					{
						json.put("code", 7);
						json.put("msg", "该任务没有有效外呼号码");
					}
				}
				else
				{
					json.put("code", 8);
					json.put("msg", "任务内容为空");
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
