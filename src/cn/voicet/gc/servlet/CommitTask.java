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
 * 为作业添加号码
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
		try {
			appDao.addNewTaskInfo(das);
			appDao.addTelInfo(das);
			das.map = null;
			json.put("code", 0);
			json.put("msg", "success");
		} catch (Exception e) {
			json.put("code", 2);
			json.put("msg", "request error");
		}
		out.println(json);
		out.flush();
		out.close();
	}

}
