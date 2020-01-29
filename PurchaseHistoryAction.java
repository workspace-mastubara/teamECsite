package com.internousdev.latte.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.latte.dao.PurchaseHistoryInfoDAO;
import com.internousdev.latte.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware{
	private String message;
	private int totalPrice;
	private Map<String, Object>session;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();

	public String execute() {
		String res = SUCCESS;

		String userId = String.valueOf(session.get("userId"));
		String tempLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		if(logined != 1) {
			res =  "loginError";
		}

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		purchaseHistoryInfoDTOList = purchaseHistoryInfoDAO.getPurchaseHistoryInfoList(userId);



		if(purchaseHistoryInfoDTOList.size() == 0){
			setMessage("商品購入履歴情報はありません。");
		}

		return res;
	}

	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotalPrice() {
		return this.totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<String,Object> getSession() {
		return session;
	}
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList(){
		return this.purchaseHistoryInfoDTOList;
	}
	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList){
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}
}
