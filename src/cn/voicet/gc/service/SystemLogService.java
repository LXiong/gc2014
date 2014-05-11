package cn.voicet.gc.service;

import cn.voicet.gc.util.DotSession;

public interface SystemLogService {

	
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.SystemLogServiceImpl";

	Integer findLogTotalPage(DotSession ds, String startdate, String enddate,
			String msgtype, String sender, int pageSize);

	void findLogInfo(DotSession ds, String startdate, String enddate,
			String msgtype, String sender, int curPage, int pageSize);

}
