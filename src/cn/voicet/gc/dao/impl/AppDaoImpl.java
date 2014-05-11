package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.AppDao;
import cn.voicet.gc.util.DotAppSession;

@Repository(AppDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class AppDaoImpl extends BaseDaoImpl implements AppDao {

	public Map userLoginPost(final String account, final String password) {
		Map map = (Map) this.getJdbcTemplate().execute(
				new ConnectionCallback() {
					public Object doInConnection(Connection conn)
							throws SQLException, DataAccessException {
						CallableStatement cs = conn.prepareCall("{call sys_userlogin(?,?)}");
						cs.setString(1, account);
						cs.setString(2, password);
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

	
	/** 添加作业  */
	public void addNewTaskInfo(final DotAppSession das) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_task_add(?,?,?,?)}");
				cs.setInt(1, das.uid);
				cs.setString(2, (String)das.map.get("tname"));
				cs.setString(3, (String)das.map.get("tcontent"));
				cs.registerOutParameter(4, Types.INTEGER);
				cs.execute();
				das.tid = cs.getInt(4);
				return null;
			}
		});
	}
	
	/** 任务添加号码  */
	public void addTelInfo(final DotAppSession das) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_tasktel_add(?,?,?)}");
				cs.setInt(1, das.uid);
				cs.setInt(2, das.tid);
				cs.setString(3, (String)das.map.get("tellist"));
				cs.execute();
				return null;
			}
		});
	}

	/** 呼叫结果数据获取 */
	public void findTaskInfoByTid(final DotAppSession das) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_task_list(?)}");
				cs.setInt(1, das.uid);
				cs.execute();
				Map map;
				ResultSet rs = cs.getResultSet();
				das.initData();
				das.list1 = new ArrayList();
				if(null!=rs){
					while(rs.next()){
						map = new HashMap();
						das.putMapDataByColName(map, rs);
		        		das.list1.add(map);
					}
				}
				return null;
			}
		});
	}

}
