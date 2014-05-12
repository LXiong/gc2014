package cn.voicet.gc.web.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.UserService;
import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.util.LogonUtils;
import cn.voicet.gc.web.form.UserForm;

import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class UserAction extends BaseAction implements ModelDriven<UserForm>{
	private static Logger log = Logger.getLogger(UserAction.class);
	@Resource(name=UserService.SERVICE_NAME)
	private UserService userService;
	private UserForm userForm = new UserForm();
	
	public UserForm getModel() {
		return userForm;
	}
	
	public String ajaxlogin(){
		log.info("account:"+userForm.getAccount()+", password:"+userForm.getPassword());
		JSONObject json = new JSONObject();
		if (request.getSession().getAttribute("vts")==null) {
			DotSession ds = new DotSession();
			request.getSession().setAttribute("vts", ds);
		}
		DotSession ds=DotSession.getVTSession(request);
		Map<String, String> map = userService.userLogin(userForm);
		log.info("user login: "+map);
		ds.uid = Integer.parseInt(map.get("uid"));
		ds.roleID = map.get("roleid");
		ds.username = map.get("username");
		ds.roleName = map.get("rolename");
		ds.password = userForm.getPassword();
		ds.account = userForm.getAccount();
		
		if(!ds.roleID.equals("0")){
			json.put("status", "ok");
		}else{
			json.put("status", "error");
		}
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			log.error(e);
		}
		
		return null;
	}

	public String home(){
		log.info("has login");
		return "show_home";
	}
	
	/** 修改密码 */
	public String updatePassword() {
		DotSession ds=DotSession.getVTSession(request);
		JSONObject jsonObj = new JSONObject();
		log.info("oldPwd:"+userForm.getOldpwd());
		log.info("newPwd:"+userForm.getNewpwd());
		Integer res = userService.updateUserPassword(ds, userForm);
		log.info("update pwd code:"+res);
		if(res==1){
			jsonObj.put("status", "1");
			ds.password = userForm.getNewpwd();
		}else if(res==0){
			jsonObj.put("status", "0");
		}
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonObj.toString());
			response.getWriter().flush();
		} catch (IOException e) {
		}
		return null;
	}
	
	/** 注销登录 */
	public String logout() {
		DotSession ds=DotSession.getVTSession(request);
		ds.clear();
		request.getSession().invalidate();
		return "show_logout";
	}
	
}
