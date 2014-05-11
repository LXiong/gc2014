package cn.voicet.gc.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.SystemLogService;
import cn.voicet.gc.util.DotSession;

@Controller("systemLogAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class SystemLogAction extends BaseAction{

	@Resource(name=SystemLogService.SERVICE_NAME)
	private SystemLogService systemLogService;
	
	//获取当前系统时间, 并格式化
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date());
	private String startdate = date;
	private String enddate = date;
	private String msgtype;
	private String sender;
	private Integer totalPage;	//总页数
	private int pageSize = 27;	//每页行数
	private int curPage = 1;	//当前页, 默认为第一页, 为0时,则不做分页处理
	
	public String home() {
		DotSession ds = DotSession.getVTSession(request);
		//如果开始日期 或 结束日期为空, 查询结果为当前日期的系统日志
		if (request.getParameter("startdate") == "" || request.getParameter("enddate") == ""){
			startdate = date;
			enddate = date;
		}
		//判断, 上一页小于1时, 上一页等于首页
		if(curPage<1){
			curPage=1;
		}
		totalPage = systemLogService.findLogTotalPage(ds, startdate, enddate, msgtype, sender, pageSize);
		//判断,下一页大于总页数时, 下一页等于最后一页
		if(curPage>totalPage){
			curPage=totalPage;
		}
		systemLogService.findLogInfo(ds, startdate, enddate, msgtype, sender, curPage, pageSize);
		return "show_log";
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}
