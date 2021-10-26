var mainApp = angular.module("mainApp", []);

mainApp.value("searchCity", "type to search another city...");

mainApp.factory('WeatherApiService', function($http) {
    var factory = {};
    factory.requestWeatherByLocation = function(a,b) {
        var URL = "http://api.openweathermap.org/geo/1.0/reverse?lat="+a+"&lon="+b;

        var request = {
            method: 'GET',
            url: URL,
            params: {
                mode: 'json',
                units: 'metric',
                cnt: '7',
                appid: '1cb6ace31e50401f28b864f0b23fdc68'
            }
        };
        return $http(request);        
    }

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
    this.getWeatherByLocation = function(a, b) {
        return WeatherApiService.requestWeatherByLocation(a,b);        
    }
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
            if (response.data.list.length == 1) {
                var obj = response.data.list[0];
                console.log(obj)
                $scope.city = obj.name;
                $scope.country = obj.sys.country;
                $scope.temp = obj.main.temp;
                $scope.hum = obj.main.humidity;
                $scope.wind = obj.wind.speed;
                $scope.code = obj.weather[0].description;
            }
            else {
                $scope.city = response.data.name;
                $scope.names = response.data.list    
                for (var obj of response.data.list) {
                    WeatherService.getWeatherByLocation(obj.coord.lat, obj.coord.lon).then(function(response) {
                        console.log(response.data);
                    });
                    console.log(obj.main.temp)
                    $scope.city = obj.name;
                    $scope.country = obj.sys.country;
                    $scope.temp = obj.main.temp;
                    $scope.hum = obj.main.humidity;
                    $scope.wind = obj.wind.speed;
                    $scope.code = obj.weather[0].description;                
                }
            }
        });
    }
});

