package cn.voicet.gc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.voicet.gc.dao.SystemLogDao;
import cn.voicet.gc.service.SystemLogService;
import cn.voicet.gc.util.DotSession;

@Transactional(readOnly=true)
@Service(SystemLogService.SERVICE_NAME)
public class SystemLogServiceImpl implements SystemLogService {
	
	@Resource(name=SystemLogDao.SERVICE_NAME)
	private SystemLogDao systemLogDao;

	public void findLogInfo(DotSession ds, String startdate,
			String enddate, String msgtype, String sender, int curPage,
			int pageSize) {
		systemLogDao.findLogInfo(ds, startdate, enddate, msgtype, sender, curPage, pageSize);
	}

	public Integer findLogTotalPage(DotSession ds, String startdate,
			String enddate, String msgtype, String sender, int pageSize) {
		return systemLogDao.findLogTotalPage(ds, startdate, enddate, msgtype, sender, pageSize);
	}

}
