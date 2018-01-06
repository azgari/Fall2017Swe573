
pmdbApp.controller('LoginCtrl', ['$scope', '$rootScope', '$location', '$http', function($scope, $rootScope, $location, $http) {
    
	$scope.userName = "";
	$scope.password = "";
	$scope.saveStatus = false;

	$scope.login = function(path) {
		
		var authenticationInfo = {
				'userName': $scope.userName,
				'passWord': $scope.password
		}
		
		$http.post(window.serviceUrl + "/rest/pmdb/v1/login/user", authenticationInfo)
		 .then(function(response){
			 window.user = response.data;
			 $scope.saveStatus = true;
			 
			 $location.path(path);
	     });
	}
}]);