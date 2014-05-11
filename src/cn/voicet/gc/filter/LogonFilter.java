package cn.voicet.gc.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.voicet.gc.util.DotSession;

public class LogonFilter implements Filter {
	private static Logger log = Logger.getLogger(LogonFilter.class);
	private List<String> list = new ArrayList<String>();
	/**
	 *  需要定义系统页面访问中可放行的连接
	 */
	public void init(FilterConfig arg0) throws ServletException {
		list.add("/index.action");
		list.add("/userAction_login.action");
		list.add("/error.jsp");
		list.add("/userAction_home.action");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 2.从session(vts)对象中获取当前登录的用户
		DotSession ds = (DotSession)request.getSession().getAttribute("vts");
		// 如果session中存在用户信息, 则表示正常访问, 此时需要放行
		if (ds != null) {
			// 如果从session中获取的用户对象不为空, 则放行
			chain.doFilter(request, response);
			return;
		}
		// 1.获取页面中的访问的路径连接
		String path = request.getServletPath();
		log.info("access path: " + path);
		if (list!=null && list.contains(path)) {
			// 如果页面中获取的访问连接与定义的可放行的连接一致, 则放行
			chain.doFilter(request, response);
			return;
		}
		// 如果如果不满足条件1和2, 则不能放行, 回到系统的登录页面
		//response.sendRedirect(request.getContextPath() + "/");
		// 使用error.jsp实现倒计时功能每隔5秒钟, 自动跳转到登录页面
		response.sendRedirect(request.getContextPath() + "/error.jsp");
	}

	public void destroy() {
		
	}

}
