package cn.voicet.gc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.voicet.gc.dao.AppDao;
import cn.voicet.gc.util.DotAppSession;

/**
 * 呼叫结果数据获取
 */
@SuppressWarnings("serial")
public class QueryTask extends HttpServlet {
	
	private static Logger log = Logger.getLogger(QueryTask.class);
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
		JSONObject json1 = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		das.map = new HashMap();
		if(das.isLogin()){
			try {
				das.tid = Integer.parseInt((String)request.getParameter("tid"));
				log.info("tid:"+das.tid);
				appDao.findTaskInfoByTid(das);
				log.info("list1 size: "+das.list1.size());
				if(das.list1.size()>0)
				{
					for(int i=0; i<das.list1.size(); i++){
						json1.put("ttid", das.list1.get(i).get("ttid"));
						json1.put("telnum", das.list1.get(i).get("telnum"));
						json1.put("state", das.list1.get(i).get("state"));
						json1.put("calldt", das.list1.get(i).get("calldt"));
						json1.put("talkl", das.list1.get(i).get("talkl"));
						jsonArray.add(json1);
					}
					json.put("data", jsonArray);
					json.put("code", 0);
					json.put("msg", "查询任务成功");
				}
				else{
					json.put("code", 9);
					json.put("msg", "该任务不存在");
				}
			} catch (Exception e) {
				json.put("code", 2);
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
