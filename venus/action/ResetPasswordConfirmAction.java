package com.internousdev.venus.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.UserInfoDAO;
import com.internousdev.venus.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware{
	private String userId;
	private String password;
	private String newPassword;
	private String reConfirmationPassword;
	private Map<String,Object> session;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private List<String> newPasswordErrorMessageList;
	private List<String> reConfirmationPasswordErrorMessageList;
	private String userInfoIncorrectMessage;
	private String newPasswordIncorrectMessage;
	private String concealedPassword;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}

		String result=ERROR;

//		戻るボタンでresetPassword.jspに行ったときに値を保有するため
//		ResetPasswordCompleteActionで引き数で利用
		session.put("userIdForResetPassword", userId);

		InputChecker inputChecker=new InputChecker();

		userIdErrorMessageList=inputChecker.doCheck("ユーザーID", userId, 1, 8, true, false, false, true, false, false, false);
		passwordErrorMessageList=inputChecker.doCheck("現在のパスワード", password, 1, 16, true, false, false, true, false, false, false);
		newPasswordErrorMessageList=inputChecker.doCheck("新しいパスワード", newPassword, 1, 16, true, false, false, true, false, false, false);
		reConfirmationPasswordErrorMessageList=inputChecker.doCheck("新しいパスワード（再確認）", reConfirmationPassword, 1, 16, true, false, false, true, false, false, false);

			if(userIdErrorMessageList.size()>0
					|| passwordErrorMessageList.size()>0
					|| newPasswordErrorMessageList.size()>0
					|| reConfirmationPasswordErrorMessageList.size()>0){
				return result;
			}

//		ユーザーIDとパスワードがデータベースにあるか確認
		UserInfoDAO userInfoDAO=new UserInfoDAO();
			if(!userInfoDAO.isExistsUserIdAndPassword(userId,password)){
				userInfoIncorrectMessage="ユーザーIDまたは現在のパスワードが異なります。";
				return result;
			}
//		入力された新しいパスワードと再確認用のパスワードが一致しているか確認
		newPasswordIncorrectMessage=inputChecker.doPasswordCheck(newPassword, reConfirmationPassword);
			if(newPasswordIncorrectMessage==null){
				concealedPassword=concealPassword(newPassword);
//		ResetPasswordCompleteActionでデータベースに入れるため
				session.put("newPassword", newPassword);
				result=SUCCESS;
			}
			return result;

		}

	private String concealPassword(String password){
		int beginIndex=0;
		int endIndex=1;

		StringBuilder stringBuilder=new StringBuilder("**************");
		String concealPassword=stringBuilder.replace(beginIndex, endIndex, password.substring(beginIndex,endIndex)).toString();

		return concealPassword;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReConfirmationPassword() {
		return reConfirmationPassword;
	}

	public void setReConfirmationPassword(String reConfirmationPassword) {
		this.reConfirmationPassword = reConfirmationPassword;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<String> getUserIdErrorMessageList() {
		return userIdErrorMessageList;
	}

	public void setUserIdErrorMessageList(List<String> userIdErrorMessageList) {
		this.userIdErrorMessageList = userIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public List<String> getNewPasswordErrorMessageList() {
		return newPasswordErrorMessageList;
	}

	public void setNewPasswordErrorMessageList(List<String> newPasswordErrorMessageList) {
		this.newPasswordErrorMessageList = newPasswordErrorMessageList;
	}

	public List<String> getReConfirmationPasswordErrorMessageList() {
		return reConfirmationPasswordErrorMessageList;
	}

	public void setReConfirmationPasswordErrorMessageList(List<String> reConfirmationPasswordErrorMessageList) {
		this.reConfirmationPasswordErrorMessageList = reConfirmationPasswordErrorMessageList;
	}

	public String getUserInfoIncorrectMessage() {
		return userInfoIncorrectMessage;
	}

	public void setUserInfoIncorrectMessage(String userInfoIncorrectMessage) {
		this.userInfoIncorrectMessage = userInfoIncorrectMessage;
	}

	public String getNewPasswordIncorrectMessage() {
		return newPasswordIncorrectMessage;
	}

	public void setNewPasswordIncorrectMessage(String newPasswordIncorrectMessage) {
		this.newPasswordIncorrectMessage = newPasswordIncorrectMessage;
	}

	public String getConcealedPassword() {
		return concealedPassword;
	}

	public void setConcealedPassword(String concealedPassword) {
		this.concealedPassword = concealedPassword;
	}

}
