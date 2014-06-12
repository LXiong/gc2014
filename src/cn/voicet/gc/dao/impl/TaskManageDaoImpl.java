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
@SuppressWarnings({"unchecked","static-access"})
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
						ds.putMapDataByColName(map, rs);
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
					cs = conn.prepareCall("{call sp_task_add(?,?,?,?,?,?,?)}");
					cs.setInt(1, ds.uid);
					cs.setString(2, taskManageForm.getTasktxt()[2]);	//name
					cs.setString(3, taskManageForm.getTasktxt()[3]);	//workmode
					cs.setString(4, taskManageForm.getTasktxt()[4]);	//maxtrys
					cs.setString(5, taskManageForm.getTasktxt()[5]);	//trydelay
					cs.setString(6, taskManageForm.getTasktxt()[6]);	//context
					cs.registerOutParameter(7, Types.INTEGER);
					cs.execute();
					return cs.getInt(7);
				}else{
					cs = conn.prepareCall("{call sp_task_update(?,?,?,?,?,?,?)}");
					cs.setInt(1, ds.uid);
					cs.setString(2, taskManageForm.getTasktxt()[1]);	//tid
					cs.setString(3, taskManageForm.getTasktxt()[2]);	//name
					cs.setString(4, taskManageForm.getTasktxt()[3]);	//workmode
					cs.setString(5, taskManageForm.getTasktxt()[4]);	//maxtrys
					cs.setString(6, taskManageForm.getTasktxt()[5]);	//trydelay
					cs.setString(7, taskManageForm.getTasktxt()[6]);	//context
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
	
	public void setStateTaskWithTaskId(final DotSession ds, final int tid, final int state) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_task_setstate(?,?,?)}");
				cs.setInt(1, ds.uid);
				cs.setInt(2, tid);
				cs.setInt(3, state);
				cs.execute();
				return null;
			}
		});
	}

	public void viewNumberWithTaskId(final DotSession ds, final int tid) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_tasktel_list(?,?)}");
				cs.setInt(1, ds.uid);
				cs.setInt(2, tid);
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

	public void saveTelListInfo(final DotSession ds, final int tid, final int ttid, final String tellist, final int eflag) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
					CallableStatement cs = null; 
					if(eflag==0){
						cs = conn.prepareCall("{call sp_tasktel_add(?,?,?)}");
						cs.setInt(1, ds.uid);
						cs.setInt(2, tid);
						cs.setString(3, tellist);
					}else{
						cs = conn.prepareCall("{call sp_tasktel_update(?,?,?,?)}");
						cs.setInt(1, ds.uid);
						cs.setInt(2, tid);
						cs.setInt(3, ttid);
						cs.setString(4, tellist);
					}
					cs.execute();
					return null;
				}
		});
	}

	public void deleteTelInfoWithTaskId(final DotSession ds, final int tid, final int ttid) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_tasktel_delete(?,?,?)}");
				cs.setInt(1, ds.uid);
				cs.setInt(2, tid);
				cs.setInt(3, ttid);
				cs.execute();
				return null;
			}
		});
	}

	public void emptyTelInfoWithCurTask(final DotSession ds, final int tid) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_tasktel_empty(?,?)}");
				cs.setInt(1, ds.uid);
				cs.setInt(2, tid);
				cs.execute();
				return null;
			}
		});
	}

	public void resetTelInfoWithCurTask(final DotSession ds, final int tid) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_tasktel_reset(?,?)}");
				cs.setInt(1, ds.uid);
				cs.setInt(2, tid);
				cs.execute();
				return null;
			}
		});
	}

}
