var addPadawanModule = angular.module("coachManagement", []);

addPadawanModule.factory("DataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias
    };
});

var addPadawanControllerHandler = function ($scope, DataService, $http) {
    $scope.endPoint = "/findcoach/coach/" + DataService.loggedCoachAlias + "/padawan-management/basic-info"
    $scope.padawanData = {};
    $scope.padawanData.name = "Pertro Vasechkin";
    $scope.padawanData.email = "padawan@test.com";
    $scope.padawanData.gender = "MALE";
    $scope.padawanData.year = "1982";

    $scope.padawanMeasurement = {};
    $scope.padawanMeasurement.height = "178";
    $scope.padawanMeasurement.weight = "95";
    $scope.padawanMeasurement.fatPercentage = "35";

    $scope.padawanProgram = {};
    $scope.padawanProgram.name = "3 мес сжигание";
    $scope.padawanProgram.goal = "FAT_BURN";
    $scope.padawanProgram.notes = "УУууууу";
    $scope.padawanProgram.startDate = new Date();
    var endDate = new Date();
    $scope.padawanProgram.endDate = new Date(endDate.setMonth(endDate.getMonth() + 3));


    $scope.submitStep1 = function () {

        var addPadawanData = {};
        addPadawanData.padawanData = $scope.padawanData;
        addPadawanData.padawanMeasurement = $scope.padawanMeasurement;
        addPadawanData.padawanProgram = $scope.padawanProgram;

        $http({
            method: 'POST',
            url: $scope.endPoint,
            data: addPadawanData

        }).then(function successCallback(response) {
            alert("success");
        }, function errorCallback(response) {
            alert("error");
        });
    };
};

addPadawanModule.controller("addPadawanController", ["$scope", "DataService", "$http", addPadawanControllerHandler]);
