var statusesControllers = angular.module('statusesControllers', []);
statusesControllers.controller('statusListController', function ($scope, $resource, $http) {
    $scope.data = $resource('/findcoach/coach/profile/statuses').get();
    $http.get('/findcoach/coach/profile/status')
        .success(function (data) {
            $scope.currentStatus = data;
        });
    $scope.updateStatus = function(){
        $resource('/findcoach/coach/profile/status').save($scope.currentStatus);
    }
})