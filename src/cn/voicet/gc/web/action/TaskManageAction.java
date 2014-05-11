package cn.voicet.gc.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.TaskManageService;
import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.web.form.TaskManageForm;

import com.opensymphony.xwork2.ModelDriven;

@Controller("taskManageAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class TaskManageAction extends BaseAction implements ModelDriven<TaskManageForm>{

	@Resource(name=TaskManageService.SERVICE_NAME)
	private TaskManageService taskManageService;
	private TaskManageForm taskManageForm = new TaskManageForm();
	
	public TaskManageForm getModel() {
		return taskManageForm;
	}
	
	public String home(){
		DotSession ds = DotSession.getVTSession(request);
		taskManageService.getTaskManageInfo(ds);
		return "show_task";
	}
	
	public String saveTask(){
		DotSession ds = DotSession.getVTSession(request);
		taskManageService.saveTaskManageInfo(ds, taskManageForm);
		return home();
	}
	
	public String deleteTask(){
		DotSession ds = DotSession.getVTSession(request);
		taskManageService.deleteTaskInfo(ds, tid);
		return home();
	}
	
	
	private int tid;	//任务号
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
}
