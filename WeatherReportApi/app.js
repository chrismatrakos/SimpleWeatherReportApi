var mainApp = angular.module("mainApp", []);
var myStorage = window.localStorage;
const appId = "1cb6ace31e50401f28b864f0b23fdc68";

mainApp.value("searchCity", "Type to search another city...");

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
                appid: appId
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
                appid: appId
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
var tempCities = [];
var index = 0;
var storage = {};

mainApp.controller('WeatherController', function($scope, WeatherService, searchCity) {
    $scope.searchCity = searchCity;    
    $scope.init = function () {
        storage = JSON.parse(myStorage.getItem('city'));
        console.log(storage);
        if(storage != null){
            $scope.celsiousClicked = true;
            $scope.showTempMetric = true;
            loadData(storage);
        }
    };
    
    $scope.square = function() {
        countries = [];
        tempCities = [];
        $scope.selectedItem = "";
        $scope.areMultipleCities = false;
        WeatherService.getWeatherByCity($scope.searchCity).then(function(response) {
            console.log("get request response" + response.data);// has more fields!!!

            //check and prompt unique city
            if (response.data.list.length <= 1) {
                var obj = response.data.list[0];
                obj['state'] = "";
                console.log(obj)
                setValues(obj);
            }
            //multiple cities with same name, need to Filter
            else {
                $scope.areMultipleCities = true;
                // cleanup();
                console.log("all cities and temp" + response.data.list)
                tempCities = response.data.list;
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
            for (var obj of tempCities) {
                if(selectedCity.lat == obj.coord.lat && selectedCity.lon == obj.coord.lon){
                    obj['state'] = selectedCity.state;
                    setValues(obj);
                    return 0;     
                }
            };
        }
    }
    
    $scope.convertToFahrenheit = function() {
        var fahr = $scope.temp / 5 * 9 + 32;
        $scope.fahrClicked=true;
        $scope.celsiousClicked=false;
        $scope.temp = (Math.round(fahr * 100) / 100).toFixed(2);
    }
    
    $scope.convertToCelsious = function() {
        var cels = ($scope.temp -32 ) * 5 / 9;
        $scope.celsiousClicked=true;
        $scope.fahrClicked=false;
        $scope.temp = (Math.round(cels * 100) / 100).toFixed(2);
    }

    $scope.clearData = function () {
        cleanup();
    };
    
    var loadData = function(storageObj){
        $scope.searchCity = storageObj.name;
        WeatherService.getWeatherByCity($scope.searchCity).then(function(response) {
            for (var obj of response.data.list) {
                if(obj.id == storageObj.id){
                    obj['state'] = storageObj.state;
                    setValues(obj);
                    return 0;
                }          
            }
             
        });
    }
    
    var setValues = function(obj){
        console.log("set values" + JSON.stringify(obj));
        // $scope.searchCity = obj.name;
        $scope.city = obj.name;
        $scope.country = obj.sys.country;
        $scope.temp = obj.main.temp;
        $scope.hum = obj.main.humidity;
        $scope.wind = obj.wind.speed;
        $scope.code = obj.weather[0].description;
        $scope.stateUS = obj.state;
        $scope.showTempMetric = true;
        $scope.celsiousClicked=true;
        $scope.fahrClicked=false;
        myStorage.setItem('city', JSON.stringify(obj));
    }

    var cleanup = function(){
        $scope.city = "";
        $scope.stateUS = "";
        $scope.country = "";
        $scope.temp = "";
        $scope.hum = "";
        $scope.wind = "";
        $scope.code = "";
        $scope.searchCity = searchCity;
        $scope.showTempMetric = false;
        myStorage.clear();
        return 0;
    }
});

