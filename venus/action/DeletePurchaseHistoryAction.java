package com.internousdev.venus.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.PurchaseHistoryInfoDAO;
import com.internousdev.venus.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware{
	private Map<String,Object> session;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}

		String result=ERROR;

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO=new PurchaseHistoryInfoDAO();
		int count=purchaseHistoryInfoDAO.deleteAll(session.get("userId").toString());

		if(count>0){
			purchaseHistoryInfoDTOList=purchaseHistoryInfoDAO.getPurchaseHistoryList(session.get("userId").toString());

			return SUCCESS;
		}
		return result;
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
