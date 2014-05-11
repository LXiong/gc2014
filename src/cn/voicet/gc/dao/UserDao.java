package cn.voicet.gc.dao;

import java.util.Map;

import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.web.form.UserForm;

public interface UserDao extends BaseDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.UserDaoImpl";
	Map<String, String> userLogin(UserForm userForm);
	Integer updateUserPassword(DotSession ds, UserForm userForm);
}
