var mainApp = angular.module("mainApp", []);

mainApp.value("searchCity", "type to search another city...");

mainApp.factory('WeatherApiService', function($http) {
    var factory = {};
    factory.requestWeatherByCity = function(town){
        var URL = 'http://api.openweathermap.org/data/2.5/find?';
  
        var request = {
            method: 'GET',
            url: URL,
            params: {
                q: town,
                mode: 'json',
                units: 'metric',
                cnt: '7',
                appid: '1cb6ace31e50401f28b864f0b23fdc68'
            }
        };
        return $http(request);
    }

    return factory;
});

mainApp.service('WeatherService', function(WeatherApiService){
    this.getWeatherByCity = function(town){
        return WeatherApiService.requestWeatherByCity(town);
    }
});

mainApp.controller('WeatherController', function($scope, WeatherService, searchCity) {
  $scope.searchCity = searchCity;
    $scope.square = function() {
        WeatherService.getWeatherByCity($scope.searchCity).then(function(response) {
            console.log(response.data);// has more fields!!!

            //check and prompt unique city

            $scope.city = response.data.name;
            $scope.names = response.data.list
            $scope.country = "GB"

            for (var obj of response.data.list) {
                if ($scope.country == obj.sys.country){
                    console.log(obj.main.temp)
                    $scope.city = obj.name;
                    $scope.temp = obj.main.temp;
                    $scope.hum = obj.main.humidity;
                    $scope.wind = obj.wind.speed;
                    $scope.code = obj.weather[0].description;
                }
                
            }
            //to do: later add more fields to weather widget!!!           
        });
    }
});