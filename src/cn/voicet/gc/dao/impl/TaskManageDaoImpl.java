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

import cn.voicet.gc.dao.TaskManageDao;
import cn.voicet.gc.util.DotSession;
import cn.voicet.gc.web.form.TaskManageForm;

@Repository(TaskManageDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class TaskManageDaoImpl extends BaseDaoImpl implements TaskManageDao {

	public void getTaskManageInfo(final DotSession ds) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_task_list(?)}");
				cs.setInt(1, ds.uid);
				cs.execute();
				ResultSet rs = cs.getResultSet();
				Map map;
				ds.initData();
				ds.list = new ArrayList();
				if(null!=rs){
					while(rs.next()){
						map = new HashMap();
						ds.putMapData(map, rs);
		        		ds.list.add(map);
					}
				}
				return null;
			}
		});
	}

	public void saveTaskManageInfo(final DotSession ds, final TaskManageForm taskManageForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null; 
				if(taskManageForm.getTasktxt()[0].equals("0")){
					cs = conn.prepareCall("{call sp_task_add(?,?,?,?)}");
					cs.setInt(1, ds.uid);
					cs.setString(2, taskManageForm.getTasktxt()[2]);
					cs.setString(3, taskManageForm.getTasktxt()[3]);
					cs.registerOutParameter(4, Types.INTEGER);
					cs.execute();
					return cs.getInt(4);
				}else{
					cs = conn.prepareCall("{call sp_task_update(?,?,?,?)}");
					cs.setInt(1, ds.uid);
					cs.setString(2, taskManageForm.getTasktxt()[1]);
					cs.setString(3, taskManageForm.getTasktxt()[2]);
					cs.setString(4, taskManageForm.getTasktxt()[3]);
					cs.execute();
					return null;
				}
			}
		});
	}
	
	public void deleteTaskInfo(final DotSession ds, final int tid) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_task_delete(?,?)}");
				cs.setInt(1, ds.uid);
				cs.setInt(2, tid);
				cs.execute();
				return null;
			}
		});
	}

}
