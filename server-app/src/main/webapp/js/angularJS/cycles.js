var cycleModule = angular.module("coachManagement", []);

cycleModule.factory("DataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias,
        programId: programId
    };
});

var saveCycleControllerHandler = function ($scope, DataService, $http) {
    $scope.endPoint = "/findcoach/coach/" + DataService.loggedCoachAlias + "/program/" + DataService.programId + "/cycle/";

    $scope.cycleData = {};
    $scope.cycleData.name = "Вводный цикл";
    $scope.cycleData.notes = "УУууууу";
    $scope.cycleData.startDate = new Date();
    var endDate = new Date();
    $scope.cycleData.endDate = new Date(endDate.setMonth(endDate.getMonth() + 3));


    $scope.saveCycle = function () {

        var cycleData = {};
        cycleData.name = $scope.name;
        cycleData.notes = $scope.notes;
        cycleData.startDate = $scope.startDate;
        cycleData.endDate = $scope.endDate;
        cycleData.notes = $scope.notes;

        $http({
            method: 'POST',
            url: $scope.endPoint,
            data: cycleData

        }).then(function successCallback(response) {
            alert("success");
        }, function errorCallback(response) {
            alert("error");
        });
    };
};

cycleModule.controller("cycleController", ["$scope", "DataService", "$http", saveCycleControllerHandler]);
