var loginManagementApplication = angular.module('loginManagement', ["ngResource"]);

var requestAuthorizationHandler = function ($scope, $window) {

    $scope.sendSimpleAuthorization = function () {
        var email = {email : document.getElementById("email").value};
        $.ajax({
            url: "email",
            type: 'POST',
            data: JSON.stringify(email),
            dataType: 'json',
            success: function (res) {
                $window.location.assign(res.redirectLink);
            }
        });
    };
};

loginManagementApplication.controller('requestAuthorizationController', ["$scope", "$window", requestAuthorizationHandler]);
