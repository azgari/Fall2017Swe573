window.user = null;
window.serviceUrl = //"http://localhost:8080/pmdb"; 
				    "http://boun-moviestore.herokuapp.com";

var pmdbApp = angular.module("PMDBApp", ["ngRoute"]);
pmdbApp.config(function($routeProvider) {
    $routeProvider
    .when("/login", {
        templateUrl : "login.html",
        controller : "LoginCtrl"
    })
    .when("/dashboard", {
        templateUrl : "dashboard.html",
        controller : "DashboardCtrl"
    })
    .when("/registration", {
        templateUrl : "user_registration.html",
        controller : "RegistrationCtrl"
    })
    .when("/movieinsert", {
        templateUrl : "movie_insert.html",
        controller : "MovieInsertCtrl"
    })
    .otherwise({
		redirectTo: '/login'
	});
    
    
    
});