var app = angular.module('XmlBsep31',['ngRoute', 'ui.bootstrap'])
	.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
		
		$routeProvider
			.when('/home', {
				templateUrl: 'pages/home.html',
				controller: 'HomeController'
			})
			.when('/akti', {
				templateUrl: 'pages/akti.html',
				controller: 'AktiController'
			})
			.otherwise({
				redirectTo: '/home'
			});
		
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		
	}]);
