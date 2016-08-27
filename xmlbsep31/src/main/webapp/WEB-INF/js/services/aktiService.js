app.factory("AktiService", function($http) {
	return {
		dodajAkt: function(callback) {
			$http.get('api/akti/dodaj').success(callback);
		}
    };       
});