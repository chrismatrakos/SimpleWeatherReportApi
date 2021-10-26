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

var countries = [];
var temp_cities = [];
var index = 0;

mainApp.controller('WeatherController', function($scope, WeatherService, searchCity) {
    $scope.searchCity = searchCity;
    $scope.square = function() {
        countries = [];
        temp_cities = [];
        $scope.areMultipleCities = false;
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
            //multiple cities with same name, need to Filter
            else {
                $scope.areMultipleCities = true;
                $scope.city = response.data.name;
                console.log("all cities and temp" + response.data.list)
                temp_cities = response.data.list;
                for (var obj of response.data.list) {
                   WeatherService.getWeatherByLocation(obj.coord.lat, obj.coord.lon).then(function(response) {
                        console.log(response.data[0]);
                        countries[index] = response.data[0];
                        index = index + 1;
                    });           
                }
                console.log(countries);
                index = 0;
                $scope.names = countries;
            }
        });
    }

    $scope.selectedItemChanged = function() {
        if ($scope.selectedItem == "") {
            console.log("Select one of the cities" );        

        }
        else{        
            var selectedCity = JSON.parse($scope.selectedItem);
            console.log("object parsed " + selectedCity.lat + " " + selectedCity.lon);
            for (var obj of temp_cities) {
                if(selectedCity.lat == obj.coord.lat && selectedCity.lon == obj.coord.lon)
                $scope.city = obj.name;
                $scope.country = obj.country;
                $scope.temp = obj.main.temp;
                $scope.hum = obj.main.humidity;
                $scope.wind = obj.wind.speed;
                $scope.code = obj.weather[0].description;
                temp_cities = [];
                $scope.areMultipleCities = false;
                return 0;
            };           
        }


    }
});

