package cn.voicet.gc.dao;

import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.web.form.TaskManageForm;

public interface TaskManageDao extends BaseDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.TaskManageDaoImpl";
	void getTaskManageInfo(DotSession ds);
	void deleteTaskInfo(DotSession ds, int tid);
	void saveTaskManageInfo(DotSession ds, TaskManageForm taskManageForm);
}
