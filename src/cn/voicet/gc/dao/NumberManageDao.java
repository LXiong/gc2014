package cn.voicet.gc.dao;

import cn.voicet.gc.util.DotSession;

public interface NumberManageDao extends BaseDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.NumberManageDaoImpl";

	void getNumberManageInfo(DotSession ds);
}
