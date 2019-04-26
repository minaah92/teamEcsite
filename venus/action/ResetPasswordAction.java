package com.internousdev.venus.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;


public class ResetPasswordAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private int backFlag;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}

		if(backFlag !=1){
			session.remove("userIdForResetPassword");
		}


		return SUCCESS;

	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(int backFlag) {
		this.backFlag = backFlag;
	}

}
