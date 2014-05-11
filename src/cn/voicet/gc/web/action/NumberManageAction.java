package cn.voicet.gc.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.NumberManageService;
import cn.voicet.gc.util.DotSession;

@Controller("numberManageAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class NumberManageAction extends BaseAction{

	@Resource(name=NumberManageService.SERVICE_NAME)
	private NumberManageService numberManageService;
	
	public String home(){
		DotSession ds = DotSession.getVTSession(request);
		numberManageService.getNumberManageInfo(ds);
		return "show_number";
	}
}
