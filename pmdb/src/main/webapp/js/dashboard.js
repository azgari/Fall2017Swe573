//var PMDbApp =  angular.module('PMDbApp', ["ngRoute"]);

pmdbApp.controller('DashboardCtrl', ['$scope', '$rootScope', '$http', '$location', function($scope, $rootScope, $http, $location) {
    
	$scope.loggedUser = window.user;
	
	$scope.searchInIMDB = function() {
		console.log('clicked!');
		$location.path('/movieinsert');
	}
	
}]);