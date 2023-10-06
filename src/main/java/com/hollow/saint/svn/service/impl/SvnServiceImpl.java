package com.hollow.saint.svn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hollow.saint.svn.dao.SvnDao;
import com.hollow.saint.svn.entity.SmsLogVO;
import com.hollow.saint.svn.service.SvnSevice;
import com.hollow.saint.util.OkHttpUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Repository
public class SvnServiceImpl implements SvnSevice {
//	@Resource
//	SvnDao svnDao;
//	@Override
//	public boolean openSvn() throws IOException {
//		String url = "http://222.76.208.7:8134/SvnCoinController/openSvn";
//		Map<String, String> map = new HashMap<>();
//		map.put("data", "");
//		if (!OkHttpUtil.doPost(url, map)) {
//			return false;
//		}
//		int count = svnDao.deleteLatestSvnLog();
//		return count > 0;
//	}
//
//	@Override
//	public boolean closeSvn() throws IOException {
//		String url = "http://222.76.208.7:8134/SvnCoinController/closeSvn";
//		Map<String, String> map = new HashMap<>();
//		map.put("data", "");
//		if (!OkHttpUtil.doPost(url, map)) {
//			return false;
//		}
//		int count = svnDao.deleteLatestSvnLog();
//		return count > 0;
//	}
//
//	@Override
//	public boolean add(String num) throws IOException {
//		String url = "http://110.80.137.19:8134/SvnCoinController/save";
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("p_user_id", "220059066");
//		jsonObject.put("p_coin_count", num);
//		Map<String, String> map = new HashMap<>();
//		map.put("data", jsonObject.toJSONString());
//		if (!OkHttpUtil.doPost(url, map)) {
//			return false;
//		}
//		int count = svnDao.deleteLatestSvnLog();
//		return count > 0;
//	}
//
//	private boolean login() throws IOException {
//		String url = "http://222.76.208.7:8134/auth";
//		Map<String, String> map = new HashMap<>();
//		map.put("USERNAME", "shenfu");
//		map.put("PASSWORD", "qweewq123");
//		map.put("yzm", "1234");
//		return OkHttpUtil.doPost(url, map);
//	}
//
//	@Override
//	public List<Map<String, Object>> getSalaryListByYearAndMonth(String yearAndMonth) {
//		List<Map<String, Object>> list = svnDao.getSalaryListByYearAndMonth(yearAndMonth);
//		return svnDao.getSalaryListByYearAndMonth(yearAndMonth);
//	}
//
//	@Override
//	public List<SmsLogVO> getLogList() {
//		return svnDao.getSmsLogList().stream().map(m -> {
//			SmsLogVO smsLogVO = new SmsLogVO();
//			smsLogVO.setSms_type(String.valueOf(m.get("sms_type")));
//			smsLogVO.setDevice_id(String.valueOf(m.get("device_id")));
//			smsLogVO.setPhone_num(String.valueOf(m.get("phone_num")));
//			smsLogVO.setUser_name(String.valueOf(m.get("user_name")));
//			smsLogVO.setSend_at(String.valueOf(m.get("send_at")));
//			return smsLogVO;
//		}).collect(Collectors.toList());
//	}
}
