package cn.voicet.gc.dao;

import java.util.Map;

import cn.voicet.gc.util.DotAppSession;

public interface AppDao extends BaseDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.AppDaoImpl";
	Map userLoginPost(String account, String password);
	void addNewTaskInfo(DotAppSession das);
	void addTelInfo(DotAppSession das);
	void findTaskInfoByTid(DotAppSession das);
}
