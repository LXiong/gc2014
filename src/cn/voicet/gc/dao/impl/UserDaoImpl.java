package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.UserDao;
import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.web.form.UserForm;

@Repository(UserDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/** 处理用户登录 */
	public Map<String, String> userLogin(final UserForm userForm) {
		Map<String, String> map = (Map<String, String>) this.getJdbcTemplate().execute(
				new ConnectionCallback() {
					public Object doInConnection(Connection conn)
							throws SQLException, DataAccessException {
						CallableStatement cs = conn.prepareCall("{call sys_userlogin(?,?)}");
						cs.setString(1, userForm.getAccount());
						cs.setString(2, userForm.getPassword());
						cs.execute();
						ResultSet rs = cs.getResultSet();
						Map map = null;
						if (rs != null) {
							while (rs.next()) {
								map = new HashMap();
								int i = 1;
								map.put("uid", rs.getString(i++));
								map.put("roleid", rs.getString(i++));
								map.put("username", rs.getString(i++));
								map.put("rolename", rs.getString(i++));
							}
						}
						return map;
					}
				});
		return map;
	}

	/** 修改用户密码 */
	public Integer updateUserPassword(final DotSession ds, final UserForm userForm) {
		Integer res = (Integer) this.getJdbcTemplate().execute(
				new ConnectionCallback() {
					public Object doInConnection(Connection conn)
							throws SQLException, DataAccessException {
						CallableStatement cs = conn.prepareCall("{call sys_accountchgpwd(?,?,?)}");
						cs.setString(1, ds.account);
						cs.setString(2, userForm.getOldpwd());
						cs.setString(3, userForm.getNewpwd());
						cs.execute();
						ResultSet rs = cs.getResultSet();
						Integer result = 0;
						if(null!=rs){
							while(rs.next()){
								result = rs.getInt(1);
							}
						}
						return result;
					}
				});
		return res;
	}

}
