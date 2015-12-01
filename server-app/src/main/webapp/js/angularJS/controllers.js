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

var profileController = angular.module('profileController', []);
    profileController.controller('profileController', function ($scope, $resource) {
    $scope.profileAttributes = $resource('/findcoach/coach/profile/coachProfileAttributes').get();
    $scope.submit = function () {
        $resource('/findcoach/coach/profile/cv').save($scope.profileAttributes, function(){
            $scope.status = "attributes have been updated"
        });
    }
})

var padawansListController = angular.module('padawansListController', []);
padawansListController.controller('padawansList', function ($scope, $resource) {
    $scope.click = function () {
        $scope.data = $resource("/findcoach/coach/:coachAlias/padawan", {coachAlias: '@alias'}).query({coachAlias: '${coachAlias}'});;
    };
})