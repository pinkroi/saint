package com.hollow.saint.svn.entity;

public class SmsLogVO {
	String sms_type;
	String device_id;
	String phone_num;
	String user_name;
	String send_at;

	public String getSms_type() {
		return sms_type;
	}

	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSend_at() {
		return send_at;
	}

	public void setSend_at(String send_at) {
		this.send_at = send_at;
	}
}
