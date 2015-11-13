var padawanManagementModule = angular.module("offerPadawanManagement", []);
var addNewPadawanControllerHandler = function($scope, $http) {
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
};

padawanManagementModule.controller("addPadawanController", ["$scope", "$http", addNewPadawanControllerHandler]);
