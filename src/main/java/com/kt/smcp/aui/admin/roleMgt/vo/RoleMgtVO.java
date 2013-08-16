
package com.kt.smcp.aui.admin.roleMgt.vo;

import com.kt.smcp.fw.comm.vo.CommVO;

/**
 * <PRE>
 *  ClassName : RoleMgtVO 
 * </PRE>
 * @version : 1.0
 * @date    : 2013. 7. 5. 오후 1:31:57
 * @author  : 관제TF
 * @brief   : 
 */ 
public class RoleMgtVO extends CommVO {
	private String roleId, roleNm, explain, cretDt, cretrId, chgDt, chgrId;

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleNm
	 */
	public String getRoleNm() {
		return roleNm;
	}

	/**
	 * @param roleNm the roleNm to set
	 */
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

	/**
	 * @return the explain
	 */
	public String getExplain() {
		return explain;
	}

	/**
	 * @param explain the explain to set
	 */
	public void setExplain(String explain) {
		this.explain = explain;
	}

	/**
	 * @return the cretDt
	 */
	public String getCretDt() {
		return cretDt;
	}

	/**
	 * @param cretDt the cretDt to set
	 */
	public void setCretDt(String cretDt) {
		this.cretDt = cretDt;
	}

	/**
	 * @return the cretrId
	 */
	public String getCretrId() {
		return cretrId;
	}

	/**
	 * @param cretrId the cretrId to set
	 */
	public void setCretrId(String cretrId) {
		this.cretrId = cretrId;
	}

	/**
	 * @return the chgDt
	 */
	public String getChgDt() {
		return chgDt;
	}

	/**
	 * @param chgDt the chgDt to set
	 */
	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}

	/**
	 * @return the chgrId
	 */
	public String getChgrId() {
		return chgrId;
	}

	/**
	 * @param chgrId the chgrId to set
	 */
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

}
