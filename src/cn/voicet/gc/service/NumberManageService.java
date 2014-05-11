package cn.voicet.gc.service;

import cn.voicet.gc.util.DotSession;

public interface NumberManageService {
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.NumberManageServiceImpl";

	void getNumberManageInfo(DotSession ds);
}
