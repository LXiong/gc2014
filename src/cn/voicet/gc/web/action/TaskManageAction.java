package cn.voicet.gc.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.TaskManageService;
import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.util.ExcelTemplateGenerator;
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
		taskManageService.saveTelListInfo(ds, tid, ttid, tellist, eflag);
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
	
	/** 导出任务 
	 * @throws Exception */
	public String exportTask() throws Exception{
		DotSession ds = DotSession.getVTSession(request);
		taskManageService.getTaskManageInfo(ds);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = new String("任务统计信息".getBytes("gb2312"), "ISO8859-1")+format.format(new Date())+".xls";
	    String filePath = request.getSession().getServletContext().getRealPath("excelTemplate")+"/"+"task.xls";
	    ExcelTemplateGenerator generator = new ExcelTemplateGenerator(filePath, fileName, 1, ds.list);
	    generator.setColList("tid,state,tname,workmode,maxtrys,nextcalldelay,context,cdt,m,fn,sn");
	    generator.setDrawBoard();
	    generator.setEffectColNum(11);
	    generator.exportExcelWithTemplate(response);
		return null; 
	}
	
	/** 导出号码 */
	public String exportTel() throws Exception{
		DotSession ds = DotSession.getVTSession(request);
		log.info("exportTel tid:"+tid);
		taskManageService.viewNumberWithTaskId(ds, tid);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = new String("任务号码统计信息".getBytes("gb2312"), "ISO8859-1")+format.format(new Date())+".xls";
	    String filePath = request.getSession().getServletContext().getRealPath("excelTemplate")+"/"+"task-tel.xls";
	    ExcelTemplateGenerator generator = new ExcelTemplateGenerator(filePath, fileName, 1, ds.list);
	    generator.setColList("ttid,telnum,state,calldt,talkl");
	    generator.setDrawBoard();
	    generator.setEffectColNum(5);
	    generator.exportExcelWithTemplate(response);
	    rflag = rflag + 1;
		log.info("exportTask rflag: "+rflag);
		return null; 
	}
	
	/** 删除指定号码 */
	public String deleteTel(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("deleteTel>> tid:"+tid+", ttid:"+ttid);
		taskManageService.deleteTelInfoWithTaskId(ds, tid, ttid);
		rflag = rflag + 1;
		log.info("deleteTel success");
		return viewNumber();
	}
	
	/** 清空当前任务的全部号码 */
	public String emptyTel(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("emptyTel>> tid:"+tid);
		taskManageService.emptyTelInfoWithCurTask(ds, tid);
		rflag = rflag + 1;
		log.info("emptyTel success");
		return viewNumber();
	}
	
	/** 重置所有呼叫的状态为未呼叫状态 */
	public String resetTel(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("resetTel>> tid:"+tid);
		taskManageService.resetTelInfoWithCurTask(ds, tid);
		rflag = rflag + 1;
		log.info("resetTel success");
		return viewNumber();
	}
	
	private int tid;	//任务号
	private int state;	//任务状态	0:停止,1:激活
	private String tellist;	//号码列表
	private int rflag = 1;	//return flag
	private int ttid;
	private int eflag;	//0:添加,1:修改
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
	public int getTtid() {
		return ttid;
	}
	public void setTtid(int ttid) {
		this.ttid = ttid;
	}
	public int getEflag() {
		return eflag;
	}
	public void setEflag(int eflag) {
		this.eflag = eflag;
	}
}
