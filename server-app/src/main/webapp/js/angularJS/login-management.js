var loginManagementApplication = angular.module('loginManagement', ["ngResource", "facebook"]);

loginManagementApplication.constant("appConfig", {
    "applicationId": "1691660047783905"
})

loginManagementApplication.config(function(FacebookProvider, appConfig) {
    FacebookProvider.init(appConfig.applicationId);
    FacebookProvider.setSdkVersion('v2.6');
})

var authorizationHandler = function ($scope, Facebook, $window, appConfig) {
    $scope.loginStatus = 'disconnected';
    $scope.facebookIsReady = false;
    $scope.user = null;
    $scope.loggedInUserId = null;

    $scope.login = function () {
        Facebook.login(function(response) {
            $scope.loginStatus = response.status;
            $scope.loggedInUserId = response.authResponse.userID;
            $scope.loggedInUserShortLivedToken = response.authResponse.accessToken;
            $scope.loadAdditionalUserInfoForLogin(function(response) {
                $scope.user = response;
                $scope.processFacebookAuthorizationRequest();
            });
        }, {scope: permissions});
    };

    $scope.logout = function () {
        Facebook.logout(function(response) {
            $scope.loginStatus = 'disconnected';
            $scope.user = null;
            $scope.loggedInUserId = null;
        });
    };

    $scope.loadAdditionalUserInfoForLogin = function (callBackFunc) {
        Facebook.api('/' + $scope.loggedInUserId, {fields: fields}, function(response) {
            callBackFunc(response);
        });
    };

    $scope.$watch(function() {
            return Facebook.isReady();
        }, function(newVal) {
            if (newVal) {
                $scope.facebookIsReady = true;
            }
        }
    );

    $scope.processFacebookAuthorizationRequest = function () {
        $scope.endPoint = "facebook";

        var facebookAuthData = {};
        facebookAuthData.userId = $scope.loggedInUserId;
        facebookAuthData.email = $scope.user.email;
        facebookAuthData.shortLivedToken = $scope.loggedInUserShortLivedToken;
        facebookAuthData.applicationId = appConfig.applicationId;

        $.ajax({
            url: $scope.endPoint,
            type: 'POST',
            data: JSON.stringify(facebookAuthData),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                $window.location.assign(res.redirectLink);
            }
        });
    };

    $scope.sendSimpleAuthorization = function () {
        var email = {email : document.getElementById("email").value};
        $scope.sendAuthorizationRequest(email);
    };

    $scope.sendAuthorizationRequest = function (email) {
        $scope.endPoint = "email";
        $.ajax({
            url: $scope.endPoint,
            type: 'POST',
            data: JSON.stringify(email),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                $window.location.assign(res.redirectLink);
            }
        });
    };
};

var permissions = [
    'email'
].join(',');

var fields = [
    'id',
    'name',
    'email'
].join(',');

loginManagementApplication.controller('authorizationController', ["$scope", "Facebook", "$window", "appConfig", authorizationHandler]);


loginManagementApplication.controller('settingsController', ["$scope", "$http",
    function ($scope, $http) {
        $scope.init = function () {
            console.log("load settings from server");
            var url = "/findcoach/settings";

            $http.get(url).then(function (response) {
                var responseData = response.data;
                $scope.devMode = responseData.developmentMode;
            },
            function (response) {
            });

            console.log("settings loaded");
        }

        $scope.init();
    }
]);



