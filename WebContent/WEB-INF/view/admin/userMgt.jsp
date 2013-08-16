<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="kt.admin.userMgt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/resources/vendor/bootstrap.css/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="/resources/vendor/jquery.ui/themes/base/jquery.ui.all.css">
		<link rel="stylesheet" type="text/css" href="/resources/vendor/jqgrid/css/ui.jqgrid.css">
		<link rel="stylesheet" type="text/css" href="/resources/vendor/kt/css/style.css">
		<link rel="stylesheet" type="text/css" href="/resources/vendor/kt/css/style-theme-default.css">
		<link rel="stylesheet" type="text/css" href="/resources/css/sample.css">
		<script src="/resources/vendor/jquery/jquery.min.js"></script>
		<script src="/resources/vendor/angular/angular.min.js"></script>
		<script src="/resources/vendor/jqgrid/js/minified/jquery.jqGrid.min.js"></script>
		<script src="/resources/vendor/jqgrid/js/i18n/grid.locale-kr.js"></script>

		<script src="/resources/vendor/kt/build/kt3m-ui.js"></script>
		<script src="/resources/js/admin/userMgt.js"></script>
		<title>KT 표준관제 플랫폼 - 사용자관리</title>
	</head>
	<body>
		<div class="container-fluid" ng-controller="userMgtCtrl">
			<div class="row-fluid">
				<ul class="breadcrumb">
				  <li><a href="#">Admin관리</a> <span class="divider">/</span></li>
				  <li class="active">사용자 관리</li>
				</ul>
			</div>
			<div class="row-fluid">
				<form class="form-horizontal">
					<table class="table table-bordered search-box">
						<tbody>
							<tr>
								<td style="vertical-align:middle; width:30px;">검색<br/>조건</td>
								<td>
									<div class="row-fluid" style="margin-bottom: 10px;">
										<div class="control-group span6">
									    <label class="control-label" for="searchUserId">사용자ID</label>
									    <div class="controls">
									      <input type="text" name="searchUserId" ng-model="searchInfo.userId" placeholder="사용자ID">
									    </div>
									  </div>
									  <div class="control-group span6">
									    <label class="control-label" for="targetService">대상서비스</label>
									    <div class="controls">
									    	<select name="targetService" ng-model="searchInfo.svcId" ng-options="service.svcId as service.svcNm for service in serviceList">
									    		<option value="">--전체--</option>
									    	</select>
									    </div>
									  </div>
									</div>
									<div class="row-fluid">
										<div class="control-group span6">
									    <label class="control-label" for="searchName">사용자명</label>
									    <div class="controls">
									      <input type="text" name="searchName" ng-model="searchInfo.userNm" placeholder="사용자명">
									    </div>
									  </div>
									  <div class="control-group span6">
									    <label class="control-label" for="role">역할(Role)</label>
									    <div class="controls">
									      <select name="role" ng-model="searchInfo.roleId" ng-options="role.roleId as role.roleNm for role in roleList">
									    		<option value="">--전체--</option>
									    	</select>
									    </div>
									  </div>
									</div>
								</td>
								<td style="vertical-align:middle; border-left: none; width: 60px;">
									<button class="btn" ng-click="searchUser(searchInfo)">조회</button>
								</td>
							</tr>						
						</tbody> 
					</table>
				</form>
			</div>
			<div class="row-fluid">
				<button class="btn pull-right" ng-click="popup('read',selectedUser)"><i class="icon-zoom-in"></i> 상세보기</button>
			</div>
			<br/>
			<div class="row-fluid">
				<table id="grid1" kt-grid data="userList" grid-height="400px" autowidth="true" row-num="3" sortable="true" pager="true" on-page-change="changePage()">
					<thead>
						<tr>
							<th>사용자ID</th>
							<th>사용자명</th>
							<th>대상 서비스</th>
							<th>역할명</th>
							<th>이동전화</th>
							<th>이메일</th>
							<th>사용여부</th>
							<th>최종수정일시</th>
						</tr>
					</thead>
					<tbody>
						<tr on-select-row="selectUser()">
							<td align="center" sorttype="text" sortable="true">{{userId}}</td>
							<td align="center">{{userNm}}</td>
							<td align="center">{{svcNm}}</td>
							<td align="center">{{roleNm}}</td>
							<td align="center">{{mphon}}</td>
							<td align="center">{{emailAdr}}</td>
							<td align="center">{{useYn}}</td>
							<td align="center">{{chgDt}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<br/>
			<div class="row-fluid" style="text-align: center;">
				<button class="btn" ng-click="popup('insert',{})">등록</button>
				<button class="btn" ng-click="popup('update',selectedUser)">수정</button>
				<button class="btn" ng-click="deleteUser(selectedUser.userId)">삭제</button>
			</div>

			<div kt-modal="shouldBeOpen" close="close()" options="{ backdropFade: true, dialogFade:true }">
				<form class="form-horizontal" name="userDetailForm">
					<div class="modal-header clearfix">
					  <h3 class="pull-left">사용자 관리</h3>
					  <button class="btn pull-right" ng-click="close()">X</button>
					</div>
					<div class="modal-body">
						<p><상세정보></p>
					  <div class="control-group">
					    <label class="control-label" for="userId">사용자ID(*)</label>
					    <div class="controls">
					      <input type="text" name="userId" ng-model="userDetail.userId" placeholder="사용자ID" ng-required="true">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="userNm">사용자명(*)</label>
					    <div class="controls">
					      <input type="text" name="userNm" ng-model="userDetail.userNm" placeholder="사용자명" ng-required="true">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="svcId">대상 서비스(*)</label>
					    <div class="controls">
							<select name="svcId" ng-model="userDetail.svcId" ng-options="service.svcId as service.svcNm for service in serviceList" ng-required="true">
								<option value="">-- 대상 서비스를 선택하세요 --</option>
							</select>
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="roleId">역할명(*)</label>
					    <div class="controls">
					    	<select name="roleId" ng-model="userDetail.roleId" ng-options="role.roleId as role.roleNm for role in roleList" ng-required="true">
								<option value="">-- 역할명을 선택하세요 --</option>
							</select>
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="officeTelNo">사무실전화(-없이)</label>
					    <div class="controls">
					      <input type="text" name="officeTelNo" ng-model="userDetail.officeTelNo" placeholder="사무실전화">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="mphon">이동전화(-없이)</label>
					    <div class="controls">
					      <input type="text" name="mphon" ng-model="userDetail.mphon" placeholder="이동전화">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="emailAdr">이메일</label>
					    <div class="controls">
					      <input type="email" name="emailAdr" ng-model="userDetail.emailAdr" placeholder="이메일">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="useYn">사용여부</label>
					    <div class="controls">
					    	<select name="useYn" ng-model="userDetail.useYn" ng-required="true">
								<option value="">-- 사용여부를 선택하세요 --</option>
								<option value="Y">사용</option>
								<option value-"N">미사용</option>
							</select>
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="pwd">비밀번호(*)</label>
					    <div class="controls">
					      <input type="password" name="pwd" ng-model="userDetail.pwd" placeholder="비밀번호" ng-required="true">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="pwdRe">비밀번호확인(*)</label>
					    <div class="controls">
					      <input type="password" name="pwdRe" ng-model="userDetail.pwdRe" placeholder="비밀번호" data-password-verify="userDetail.pwd" ng-required="true">
					    </div>
					  </div>		

					  <div class="control-group">
					    <label class="control-label" for="cretrId">생성자</label>
					    <div class="controls">
					      <input type="text" name="cretrId" ng-model="userDetail.cretrId" placeholder="생성자" ng-disabled="true">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="cretDt">생성일시</label>
					    <div class="controls">
					      <input type="text" name="cretDt" ng-model="userDetail.cretDt" placeholder="생성일시" ng-disabled="true">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="chgrId">수정자</label>
					    <div class="controls">
					      <input type="text" name="chgrId" ng-model="userDetail.chgrId" placeholder="수정자" ng-disabled="true">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="chgDt">최종수정일시</label>
					    <div class="controls">
					      <input type="text" name="chgDt" ng-model="userDetail.chgDt" placeholder="최종수정일시" ng-disabled="true">
					    </div>
					  </div>			  
					</div>
					<div class="modal-footer">
					  <button class="btn btn-warning ok" ng-show="popupStatus != 'read'" ng-click="submit(userDetail)">저장</button>
					  <button class="btn btn-warning cancel" ng-click="close()">취소</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>