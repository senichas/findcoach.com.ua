var statusesControllers = angular.module('statusesControllers', []);
statusesControllers.controller('statusListController', function ($scope, $resource) {
    var stat = $resource('/findcoach/coach/profile/statuses');
    $scope.data = stat.get();
})