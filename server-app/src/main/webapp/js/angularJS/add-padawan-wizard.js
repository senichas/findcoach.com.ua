var addPadawanWizardModule = angular.module("addPadawanWizard", []);

addPadawanWizardModule.factory("DataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias
    };
});

var addPadawanWizardControllerHandler = function ($scope, DataService, $http) {
    $scope.step1EndPoint = "/findcoach/coach/" + DataService.loggedCoachAlias + "/add-padawan-wizard/step1"
    $scope.padawanData = {};
    $scope.padawanData.name = "1";
    $scope.padawanData.email = "padawan@test.com";
    $scope.padawanData.gender = "MALE";
    $scope.padawanData.year = "1982";
    $scope.padawanData.height = "178";
    $scope.padawanData.weight = "95";
    $scope.padawanData.fatPercentage = "35";
    $scope.padawanData.goal = "FAT_BURN";
    $scope.padawanData.notes = "УУууууу";

    $scope.submitStep1 = function () {
        $http({
            method: 'PUT',
            url: $scope.step1EndPoint,
            data: $scope.padawanData

        }).then(function successCallback(response) {
            alert("success");
        }, function errorCallback(response) {
            alert("success");
        });
    };
};

addPadawanWizardModule.controller("addPadawanWizardControllerStep1", ["$scope", "DataService", "$http", addPadawanWizardControllerHandler]);
