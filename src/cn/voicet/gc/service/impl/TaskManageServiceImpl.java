package cn.voicet.gc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.voicet.gc.dao.TaskManageDao;
import cn.voicet.gc.service.TaskManageService;
import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.web.form.TaskManageForm;

@Transactional(readOnly=true)
@Service(TaskManageService.SERVICE_NAME)
public class TaskManageServiceImpl implements TaskManageService{
	
	@Resource(name=TaskManageDao.SERVICE_NAME)
	private TaskManageDao taskManageDao;

	public void getTaskManageInfo(DotSession ds) {
		taskManageDao.getTaskManageInfo(ds);
	}

	public void deleteTaskInfo(DotSession ds, int tid) {
		taskManageDao.deleteTaskInfo(ds, tid);
	}

	public void saveTaskManageInfo(DotSession ds, TaskManageForm taskManageForm) {
		taskManageDao.saveTaskManageInfo(ds, taskManageForm);
	}

}
