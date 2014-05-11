package cn.voicet.gc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

@SuppressWarnings("serial")
public class UserLogin extends HttpServlet {
	
	private static Logger log = Logger.getLogger(UserLogin.class);
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
		//
		if (request.getSession().getAttribute("vtas")==null) {
			DotAppSession das = new DotAppSession();
			request.getSession().setAttribute("vtas", das);
		}
		DotAppSession das = DotAppSession.getVTAppSession(request);
		//
		String account = null;
		String password = null;
		JSONObject json = new JSONObject();
		try {
			account = request.getParameter("account");
			password = request.getParameter("pwd");
			Map map = appDao.userLoginPost(account, password);
			log.info("map: "+map);
			//
			if(null!=map && map.size()>0 && !map.get("roleid").equals("0")){
				json.put("uid", map.get("uid"));
				das.uid = Integer.parseInt((String)map.get("uid"));
				json.put("roleid", map.get("roleid"));
				json.put("username", map.get("username"));
				json.put("rolename", map.get("rolename"));
				json.put("code", 0);
				json.put("msg", "success");
			}else{
				json.put("code", 1);
				json.put("msg", "Account or password error");
			}
		} catch (Exception e) {
			json.put("code", 2);
			json.put("msg", "request error");
		}
		out.println(json);
		out.flush();
		out.close();
	}

}
