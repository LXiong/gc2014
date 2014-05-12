package cn.voicet.gc.util;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogonUtils {

	/**
	 * @throws Exception 
	 * @see 首页登录中添加验证码的功能
	 */
	public static void rememberPassByCookie(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 获取页面中的登录名和密码
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		// 创建2个Cookie, 分别用来存放登录名和密码
		// 处理Cookie中存在的中文字符
		if(null!=account && null!=password){
			String codeName = URLEncoder.encode(account, "UTF-8");
			Cookie accountCookie = new Cookie("account", codeName);
			Cookie passwordCookie = new Cookie("password", password);
			
			// 设置Cookie的有效路径, 有效路径定义为项目的根路径
			accountCookie.setPath(request.getContextPath() + "/");
			passwordCookie.setPath(request.getContextPath() + "/");
		
			/*
			 *  从页面中获取记住我的复选框的值 
			 *  	如果有值, 设置Cookie的有效时长
			 *  	如果没有值, 清空Cookie的有效时长
			 */
			String rememberPass = request.getParameter("rememberPass");
			if (rememberPass!=null && rememberPass.equals("yes")) {
				// 设置Cookie的有效时长
				accountCookie.setMaxAge(7*24*60*60);
				passwordCookie.setMaxAge(7*24*60*60);
			} else {
				// 清空Cookie的有效时长
				accountCookie.setMaxAge(0);
				passwordCookie.setMaxAge(0);
			}
			response.addCookie(accountCookie);
			response.addCookie(passwordCookie);
		}
	}
}
