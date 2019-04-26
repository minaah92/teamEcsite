package com.internousdev.venus.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.PurchaseHistoryInfoDAO;
import com.internousdev.venus.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO=new PurchaseHistoryInfoDAO();
		purchaseHistoryInfoDTOList=purchaseHistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("userId")));

		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList() {
		return purchaseHistoryInfoDTOList;
	}

	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList) {
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}


}
