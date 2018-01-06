
pmdbApp.controller('RegistrationCtrl', ['$scope', '$http', function($scope, $http) {
    
	$scope.firstName = "";
	$scope.lastName = "";
	$scope.userName = "";
	$scope.phone = "";
	$scope.username = "";
	$scope.password = "";
	$scope.email = "";
	$scope.registrationStatus = false;

	$scope.registerUser = function() {
		
		 var registrationData = {

			"firstName": $scope.firstName,
			"lastName": $scope.lastName,
			"email": $scope.email,
			"password": $scope.password,
			"userName": $scope.userName,
			"phone":  $scope.phone,
			"userRoleId": 1
		 }
		
		 $http.post(window.serviceUrl + "/rest/pmdb/v1/user", registrationData)
		 .then(function(response){
	         $scope.registrationStatus = response.data;
			 $location.path(path);
	     });
	}
	
}]);