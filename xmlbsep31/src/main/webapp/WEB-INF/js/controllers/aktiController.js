app.controller("AktiController", function($scope, $http, $location, AktiService) {
	
	$scope.dodajAkt = function() {
		AktiService.dodajAkt();
	};
	
});