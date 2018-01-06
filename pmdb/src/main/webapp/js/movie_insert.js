
pmdbApp.controller('MovieInsertCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {
    
	$scope.saveStatus = false;

	$scope.title;
	
	$scope.year;
	
	$scope.runtime;
	
	$scope.genre;
	
	$scope.director;
	
	$scope.actors;
	
	$scope.plot;
	
	$scope.imdbRating;
	
	$scope.insertMovie = function() {
		
		 var movie = {
				title:$scope.title,
				year:$scope.year,
				runtime:$scope.runtime,
					
					genre:$scope.genre,
					
					director:$scope.director,
					
					actors:$scope.actors,
					
					plot:$scope.plot,
					
					imdbRating:$scope.imdbRating,
		 }
		 
		 $http.post(window.serviceUrl + "/rest/pmdb/v1/movie?userId=" + window.user.userId, movie)
		 .then(function(response){
	        $scope.saveStatus = true;
	        window.user=response.data;
	        $location.path('/dashboard');
	     });
	}
	
	$scope.loadBookFromMovieApi = function() {
		
		if($scope.title.length > 0) {
			
			$http.get("http://www.omdbapi.com/?t=" + $scope.title +"&apikey=6e0a08")
			 .then(function(response){
				 
				 var movieItem = response.data;
				 
				 if(movieItem.Title) {
					 $scope.title = movieItem.Title;
						
						$scope.year= movieItem.Year;
						
						$scope.runtime= movieItem.Runtime;
						
						$scope.genre= movieItem.Genre;
						
						$scope.director= movieItem.Director;
						
						$scope.actors= movieItem.Actors;
						
						$scope.plot= movieItem.Plot;
						
						$scope.imdbRating= movieItem.Ratings[0].Value;
				 }
				 
		     });
		}
	}
	
	
}]);