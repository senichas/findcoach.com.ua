var coachManagementApplication = angular.module('coachManagement', ["ngResource"]);

var statusesModuleHandler = function ($scope, $resource, $http) {
    $scope.data = $resource('/findcoach/coach/profile/statuses').get();
    $http.get('/findcoach/coach/profile/status')
        .success(function (data) {
            $scope.currentStatus = data;
        });
    $scope.updateStatus = function () {
        $resource('/findcoach/coach/profile/status').save($scope.currentStatus);
    }
};
coachManagementApplication.controller('statusListController', ["$scope", "$resource", "$http", statusesModuleHandler]);

var padawansListControllerHandler = function ($scope, $resource) {
    $scope.click = function () {
        $scope.data = $resource("/findcoach/coach/:coachAlias/padawan", {coachAlias: '@alias'}).query({coachAlias: '${coachAlias}'});
        ;
    };
};
coachManagementApplication.controller('padawansListController',  ["$scope", "$resource", padawansListControllerHandler])

var profileControllerHandler = function ($scope, $resource) {
    $scope.profileAttributes = $resource('/findcoach/coach/profile/coachProfileAttributes').get();
    $scope.submit = function () {
        $resource('/findcoach/coach/profile/cv').save($scope.profileAttributes, function () {
            $scope.status = "attributes have been updated"
        });
    }
};
coachManagementApplication.controller('profileController',  ["$scope", "$resource", profileControllerHandler])


coachManagementApplication.factory("DataService", function () {

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

coachManagementApplication.controller("cycleController", ["$scope", "DataService", "$http", saveCycleControllerHandler]);
