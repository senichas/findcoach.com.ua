coachManagementApplication.controller('padawansListController', ["$scope", "$http", "$location", "$uibModal",
    function ($scope, $http, $location, $uibModal) {
        $scope.init = function () {
            console.log("start to initialize padawan list");
            var path = $location.path();
            var params = path.split("/");
            var coachAlias = params[3];
            $scope.coachAlias = coachAlias;

            $scope.url = $scope.calculateUrlToRetrievePadawansList(coachAlias);
            $scope.padawanUrl = $scope.calculateUrlToManagePadawan(coachAlias);
            $http.get($scope.url)
                .then(function successCallback(response) {
                    console.log("Padawans received");
                    $scope.padawans = response.data;

                }, function errorCallback(response) {
                    console.log("Ups padawans have not been received");
                });

            console.log("coachAlias = " + coachAlias);

        }

        $scope.openPadawanPopup = function (padawanId) {
            $scope.padawanId = padawanId;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/js/popup-templates/manage-padawan-popup.html',
                controller: 'managePadawanPopupController',
                backdrop: 'static',
                scope: $scope
            });
        }

        $scope.openPadawanCalendar = function (padawanId) {
            var url = "/findcoach/coach/vasa_petrovich/calendar/dashboard.html";
            url += "?"
            url += "padawanId=" + padawanId;
            window.location = url;
        }

        $scope.openProgramPopup = function (padawanId, programId) {
            $scope.padawanId = padawanId;
            $scope.programId = programId;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/js/popup-templates/manage-padawan-program-popup.html',
                controller: 'managePadawanProgramPopupController',
                backdrop: 'static',
                scope: $scope
            });
        }

        $scope.openProgramTrainings = function (padawanId, programId) {
            var url = $scope.calculateUrlToManageProgram($scope.coachAlias, padawanId, programId)
            window.location.href = url + ".html";
        }

        $scope.calculateUrlToRetrievePadawansList = function (coachAlias) {
            var url = "/findcoach/coach/" + coachAlias + "/padawans";
            return url;
        }
        $scope.calculateUrlToManagePadawan = function (coachAlias) {
            var url = "/findcoach/coach/" + coachAlias + "/padawan";
            return url;
        }
        $scope.calculateUrlToManageProgram = function (coachAlias, padawanId, programId) {
            var programIdCasted = (programId == null) ? "" : programId;
            var url = "/findcoach/coach/" + coachAlias + "/padawan/" + padawanId + "/program/" + programIdCasted;
            return url;
        }
    }
]);
