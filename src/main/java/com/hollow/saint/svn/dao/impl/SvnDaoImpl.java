package com.hollow.saint.svn.dao.impl;

import com.hollow.saint.svn.dao.SvnDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;
import java.util.Map;

//@Repository
public class SvnDaoImpl implements SvnDao {
//	@Resource
//	@Qualifier("basJdbcTemplate")
//	JdbcTemplate basJdbcTemplate;
//	@Resource
//	@Qualifier("rlJdbcTemplate")
//	JdbcTemplate rlJdbcTemplate;
//
//	@Override
//	public List<Map<String, Object>> getSvnLogList() {
//		String sql = "select * from pub_svn_add_coin_log order by p_coin_date desc";
//		return basJdbcTemplate.queryForList(sql);
//	}
//
//	@Override
//	public int deleteLatestSvnLog() {
//		String sql = "delete from PUB_SVN_ADD_COIN_LOG where ID_KEY = (select max(ID_KEY) from PUB_SVN_ADD_COIN_LOG)";
//		return basJdbcTemplate.update(sql);
//	}
//
//	@Override
//	public List<Map<String, Object>> getSalaryListByYearAndMonth(String yearAndMonth) {
//		String sql = "select * from gzt_grant_summary where org_code = 32468407 and year_month = ?";
//		Object[] args = {yearAndMonth};
//		int[] argTypes = {Types.VARCHAR};
//		return rlJdbcTemplate.queryForList(sql, args, argTypes);
//	}
//
//	@Override
//	public List<Map<String, Object>> getSmsLogList() {
//		String sql = "select * from bas_sms_log order by id_key desc";
//		return basJdbcTemplate.queryForList(sql);
//	}
}
