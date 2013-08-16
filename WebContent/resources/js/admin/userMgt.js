/**
 * @ngdoc overview
 * @name kt.admin.userMgt
 * @description
 * 사용자관리 모듈
 */
angular.module('kt.admin.userMgt', ['kt.ui']).
	/**
	 * @ngdoc controller
	 * @name kt.admin.userMgt.controller:userMgtCtrl
	 * @requires $http
	 *
	 * @description
	 * 사용자관리 컨트롤러
	 *
	*/
	controller("userMgtCtrl",function($scope, $http) {
		/**
		 * @ngdoc property
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.searchInfo
		 * @propertyOf kt.admin.userMgt.controller:userMgtCtrl
		 * @description
		 	{object} 검색조건이다. 내부 속성으로 "userId" - 사용자아이디,
					"userNm" - 사용자이름,
					"svcId" - 서비스아이디,
					"roleId" - 권한아이디 들이 있다.
		*/
		$scope.searchInfo = {};

		/**
		 * @ngdoc property
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.userList
		 * @propertyOf kt.admin.userMgt.controller:userMgtCtrl
		 * @description
		 	{object} 사용자 목록이다. rows 프라퍼티에 list로 각 사용자 객체가 담겨있다.
		*/		
		$scope.userList = {};

		/**
		 * @ngdoc property
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.roleList
		 * @propertyOf kt.admin.userMgt.controller:userMgtCtrl
		 * @description
		 	[array] 역할 목록이다. 검색조건에서 사용된다.
		*/		
		$scope.roleList = [];

		/**
		 * @ngdoc property
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.serviceList
		 * @propertyOf kt.admin.userMgt.controller:userMgtCtrl
		 * @description
		 	[array] 서비스 목록이다. 검색조건에서 사용된다.
		*/		
		$scope.serviceList = [];

		$http.get("/admin/roles")
			.success(function(data) {
				$scope.roleList = data;
			});	

		$http.get("/standard/services")
			.success(function(data) {
				$scope.serviceList = data;
			});

		/**
		 * @ngdoc method
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.searchUser
		 * @methodOf kt.admin.userMgt.controller:userMgtCtrl
		 *
		 * @description
		 * 사용자를 검색한다.
		 * 
		 * @param {string=} searchInfo 검색 조건
		 *
		*/
		$scope.searchUser = function(searchInfo){
			getUserList(searchInfo,{});
		};

		/**
		 * @ngdoc method
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.changePage
		 * @methodOf kt.admin.userMgt.controller:userMgtCtrl
		 *
		 * @description
		 * 페이지 전환을 한다.
		 * 
		 * @param {string=} page 페이지 번호
		 * @param {string=} rowNum 행 갯수
		 *
		*/
		$scope.changePage = function(page, rowNum){
			getUserList($scope.searchInfo,{
				"page": page,
				"rowNum" : rowNum
			});
		}

		function getUserList(searchInfo, pagingInfo) {
			$http.get('/admin/users',{
				params : {
					"userId" : searchInfo.userId || "",
					"userNm" : searchInfo.userNm || "",
					"svcId" : searchInfo.svcId || "",
					"roleId" : searchInfo.roleId || "",
					//Paging Option
					"currentPage" : pagingInfo.page || 1,
					"pageSize" : pagingInfo.rowNum || 3
				}
			}).success(function (data) {
				if(data.length > 0) var bindingData = {
					rows : data,
					total : data[0].maxViewPage,
					page: data[0].curPage
				};
				$scope.userList = bindingData || { rows : []};
				$scope.selectedUser = undefined;
			});
		}

		/**
		 * @ngdoc method
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.selectUser
		 * @methodOf kt.admin.userMgt.controller:userMgtCtrl
		 *
		 * @description
		 * 그리드에서 row를 클릭시 호출되어 사용자를 선택한다.
		 * 
		 * @param {object=} selectedUser 그리드에서 선택한 row의 data
		 *
		*/
		$scope.selectUser = function(selectedUser) {
			$scope.selectedUser = selectedUser;
		};

		/**
		 * @ngdoc method
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.deleteUser
		 * @methodOf kt.admin.userMgt.controller:userMgtCtrl
		 *
		 * @description
		 * 그리드에서 선택한 사용자를 삭제한다.
		 * 
		 * @param {string=} userId 삭제할 사용자 ID
		 *
		*/
		$scope.deleteUser = function(userId) {
			if(userId === undefined )return;
			$http({method:'delete',url:'/admin/users/'+userId})
				.success(function(data) {
					$scope.searchUser({});
				});
		};

		/**
		 * @ngdoc method
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.insertUser
		 * @methodOf kt.admin.userMgt.controller:userMgtCtrl
		 *
		 * @description
		 * 새로운 사용자를 추가한다.
		 * 
		 * @param {object=} data 추가할 사용자 폼 데이터
		 *
		*/
		$scope.insertUser = function(data) {
			$http.post('/admin/users/',data,{
				headers : {
					"Content-Type" : "application/json"
				}
			})
			.success(function(data) {
				$scope.searchUser({});
				$scope.shouldBeOpen = false;
			});
		};

		/**
		 * @ngdoc method
		 * @name kt.admin.userMgt.controller:userMgtCtrl#$scope.updateUser
		 * @methodOf kt.admin.userMgt.controller:userMgtCtrl
		 *
		 * @description
		 * 사용자 정보를 업데이트 한다.
		 * 
		 * @param {object=} data 추가할 사용자 폼 데이터
		 *
		*/
		$scope.updateUser = function(data) {
			$http.put('/admin/users/',data,{
				headers : {
					"Content-Type" : "application/json"
				}
			})
			.success(function(data) {
				$scope.searchUser({});
				$scope.shouldBeOpen = false;
			});
		};

		$scope.popup = function(cmd, data) {
			$scope.userDetail = {};
			if(cmd === "insert"){
				$scope.userDetail = data;
				$scope.shouldBeOpen = true;
			}else{
				if(!!!$scope.selectedUser){
					alert("선택하여 주세요.");
					return;	
				} 
				// data.pwdRe = data.pwd;
				angular.copy(data, $scope.userDetail)
				// $scope.userDetail = data;
				$scope.userDetail.pwdRe = data.pwd;
				$scope.shouldBeOpen = true;
			}

			$scope.popupStatus = cmd;
		};

		$scope.close = function() {
			// $scope.wannaChange = false;
			$scope.shouldBeOpen = false;
			// $scope.selectedUser = null;
		};

		$scope.submit = function(data) {
			if($scope.userDetailForm.$invalid) return;

			switch($scope.popupStatus){
				case "insert":
				  $scope.insertUser(data);
				  break;

				case "update":
				  $scope.updateUser(data);
				  break;

				default:
				  break;
			}
		};

		// $scope.searchUser({});
	})
	/**
	 * @ngdoc directive
	 * @name kt.admin.userMgt.directive:passwordVerify
	 * @requires $parse
	 * @requires ngModel
	 * @restrict A
	 *
	 * @description
	 * 패스워드 확인하는 지시자.
	 * 
	 * @param {string=} passwordVerify 패스워드 확인 대상자
	 *
	*/
	.directive("passwordVerify", function($parse) {
	    return {
	        require: "ngModel",
	        link: function(scope, element, attrs, ctrl) {
	            var passwordVerify = $parse(attrs.passwordVerify)(scope);
	            scope.$watch(function() {
	                var combined;
	            	passwordVerify = $parse(attrs.passwordVerify)(scope);

	                if (passwordVerify || ctrl.$viewValue) {
	                   combined = passwordVerify + '_' + ctrl.$viewValue; 
	                }                    
	                return combined;
	            }, function(value) {
	                if (value) {
	                	passwordVerify = $parse(attrs.passwordVerify)(scope);

	                    ctrl.$parsers.unshift(function(viewValue) {
	                        var origin = passwordVerify;
	                        if (origin !== viewValue) {
	                            ctrl.$setValidity("passwordVerify", false);
	                            return undefined;
	                        } else {
	                            ctrl.$setValidity("passwordVerify", true);
	                            return viewValue;
	                        }
	                    });
	                }
	            });
	        }
	    };
	});