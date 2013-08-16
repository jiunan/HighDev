package com.kt.smcp.aui.admin.userMgt.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.kt.smcp.fw.comm.vo.CommVO;

/**
 * <PRE>
 *  ClassName : UserMgtVO
 * </PRE>
 *
 * @version : 1.0
 * @date : 2013. 7. 5. 오후 1:31:57
 * @author : 관제TF
 * @brief :
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserMgtVO extends CommVO {
	private String userId, userNm, svcId, svcTgtId, roleId, mphon, officeTelNo,
			emailAdr, useYn, cretrId, cretDt, chgrId, chgDt, pwd;

	private String svcNm, roleNm;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getSvcId() {
		return svcId;
	}

	public void setSvcId(String svcId) {
		this.svcId = svcId;
	}

	public String getSvcTgtId() {
		return svcTgtId;
	}

	public void setSvcTgtId(String svcTgtId) {
		this.svcTgtId = svcTgtId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMphon() {
		return mphon;
	}

	public void setMphon(String mphon) {
		this.mphon = mphon;
	}

	public String getOfficeTelNo() {
		return officeTelNo;
	}

	public void setOfficeTelNo(String officeTelNo) {
		this.officeTelNo = officeTelNo;
	}

	public String getEmailAdr() {
		return emailAdr;
	}

	public void setEmailAdr(String emailAdr) {
		this.emailAdr = emailAdr;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCretrId() {
		return cretrId;
	}

	public void setCretrId(String cretrId) {
		this.cretrId = cretrId;
	}

	public String getCretDt() {
		return cretDt;
	}

	public void setCretDt(String cretDt) {
		this.cretDt = cretDt;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public String getChgDt() {
		return chgDt;
	}

	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSvcNm() {
		return svcNm;
	}

	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

}
