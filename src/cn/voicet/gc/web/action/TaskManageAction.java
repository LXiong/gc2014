package cn.voicet.gc.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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

	private static Logger log = Logger.getLogger(TaskManageAction.class);
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
	
	/** 根据任务id查看号码 */
	public String viewNumber(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("viewNumber tid:"+tid);
		taskManageService.viewNumberWithTaskId(ds, tid);
		return "show_number";
	}

	/** 为指定任务设置状态 */
	public String stateTask(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("tid:"+tid+", state:"+state);
		taskManageService.setStateTaskWithTaskId(ds, tid, state);
		log.info("Set task state success");
		return home();
	}
	
	/** 添加修改任务 */
	public String saveTask(){
		DotSession ds = DotSession.getVTSession(request);
		taskManageService.saveTaskManageInfo(ds, taskManageForm);
		return home();
	}
	
	/** 添加修改号码 */
	public String saveTel(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("saveTel tid: "+tid);
		log.info("saveTel tellist: "+tellist);
		taskManageService.saveTelListInfo(ds, tid, tellist);
		rflag = rflag + 1;
		log.info("saveTel rflag: "+rflag);
		log.info("Save telnumber success");
		return viewNumber();
	}
	
	/** 删除指定任务 */
	public String deleteTask(){
		DotSession ds = DotSession.getVTSession(request);
		taskManageService.deleteTaskInfo(ds, tid);
		return home();
	}
	
	
	private int tid;	//任务号
	private int state;	//任务状态	0:停止,1:激活
	private String tellist;	//号码列表
	private int rflag = 1;	//return flag
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTellist() {
		return tellist;
	}
	public void setTellist(String tellist) {
		this.tellist = tellist;
	}
	public int getRflag() {
		return rflag;
	}
	public void setRflag(int rflag) {
		this.rflag = rflag;
	}
}
