package cn.voicet.gc.service;

import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.web.form.TaskManageForm;

public interface TaskManageService {
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.TaskManageServiceImpl";
	void getTaskManageInfo(DotSession ds);
	void deleteTaskInfo(DotSession ds, int tid);
	void saveTaskManageInfo(DotSession ds, TaskManageForm taskManageForm);
	void setStateTaskWithTaskId(DotSession ds, int tid, int state);
	void viewNumberWithTaskId(DotSession ds, int tid);
	void saveTelListInfo(DotSession ds, int tid, int ttid, String tellist, int eflag);
	void deleteTelInfoWithTaskId(DotSession ds, int tid, int ttid);
	void emptyTelInfoWithCurTask(DotSession ds, int tid);
	void resetTelInfoWithCurTask(DotSession ds, int tid);
}
