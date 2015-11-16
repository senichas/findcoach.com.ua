var addPadawanWizardModule = angular.module("addPadawanWizard", []);

addPadawanWizardModule.factory("DataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias
    };
});

var addPadawanWizardControllerHandler = function ($scope, DataService, $http) {
    $scope.step1EndPoint = "/findcoach/coach/" + DataService.loggedCoachAlias + "/add-padawan-wizard/step1"
    $scope.padawanData = {};
    $scope.padawanData.padawanName = "1";
    $scope.padawanData.padawanGender = "male";
    $scope.padawanData.padawanYear = "1982";
    $scope.padawanData.padawanHeight = "178";
    $scope.padawanData.padawanWeight = "95";
    $scope.padawanData.padawanFatPercentage = "35";
    $scope.padawanData.padawanGoal = "2";
    $scope.padawanData.padawanNotes = "УУууууу";
    $scope.padawanData.padawanStartProgram = true;

    $scope.submitStep1 = function () {
        $http({
            method: 'PUT',
            url: $scope.step1EndPoint,

        }).then(function successCallback(response) {
            alert("success");
        }, function errorCallback(response) {
            alert("success");
        });
    };
};

addPadawanWizardModule.controller("addPadawanWizardController", ["$scope", "DataService", "$http", addPadawanWizardControllerHandler]);
