package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.NumberManageDao;
import cn.voicet.gc.util.DotSession;

@Repository(NumberManageDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class NumberManageDaoImpl extends BaseDaoImpl implements NumberManageDao {

	public void getNumberManageInfo(final DotSession ds) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sp_tasktel_list(?,?)}");
				cs.setInt(1, ds.uid);
				cs.setInt(2, 1);
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
}
