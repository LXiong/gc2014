package cn.voicet.gc.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.voicet.gc.dao.BaseDao;

public class BaseDaoImpl extends JdbcDaoSupport implements BaseDao {
	
	/** 依赖注入jdbcTemplate */
	@Resource(name="jdbcTemplate")
	public final void setDataSourceDi(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
}
