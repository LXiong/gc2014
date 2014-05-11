package cn.voicet.gc.dao;

import cn.voicet.gc.util.DotSession;

public interface SystemLogDao {

	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.SystemLogDaoImpl";

	void findLogInfo(DotSession ds, String startdate, String enddate,
			String msgtype, String sender, int curPage, int pageSize);

	Integer findLogTotalPage(DotSession ds, String startdate, String enddate,
			String msgtype, String sender, int pageSize);
	
}
