package cn.voicet.gc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unchecked")
public class DotAppSession {
	
	public String keyb;
	public String dsn;
	public int uid;	//用户ID号
	public int tid;	//任务号
	public int oid;
	public int iid;
	public List<Map> list1;
	public List<Map> list2;
	public List<Map> list3;
	public List<Map> list4;
	public List<Map> list5;
	public List<Map> list6;
	
	public Map map;
	
	public DotAppSession() {
		keyb = null;
		dsn = null;
		uid = 0;
		oid = 0;
		iid = 0;
		tid = 0;
		map = null;
	}
	
	public void initData() {
		list1 = new ArrayList();
		list2 = new ArrayList();
		list3 = new ArrayList();
		list4 = new ArrayList();
		list5 = new ArrayList();
		list6 = new ArrayList();
	}
	
	public static DotAppSession getVTAppSession(HttpServletRequest request){
		DotAppSession das = (DotAppSession) request.getSession().getAttribute("vtas");
		return das;
	}
	
	/** 检测是否已经ShakeHand */
	public static boolean checkHasUid(HttpServletRequest request){
		String shake = (String) request.getSession().getAttribute("shakeHand");
		if(null!=shake && shake.equals("yes")){
			return true;
		}
		return false;
	}
	
	/** 将结果集数据放进Map */
	public static void putMapDataByColName(Map map,ResultSet rs) {
		String sColName;
		try {
			//获取列数
			ResultSetMetaData rsm =rs.getMetaData();
			int col = rsm.getColumnCount();
			for(int i=1; i<=col; i++) {
				sColName=rsm.getColumnName(i);
				if(null!=rs.getString(i) && !rs.getString(i).equals("")){
					map.put(sColName, rs.getString(i));
				}else{
					map.put(sColName, "");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}
	}
}
