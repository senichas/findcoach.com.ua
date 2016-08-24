var coachManagementApplication = angular.module('coachManagement', ["ngResource", "ui.bootstrap"]);
coachManagementApplication.constant('CONFIG', {
    dateTimeFormat: ''
});

coachManagementApplication.filter("dateTimeConversionFilter", function ($filter) {
    var re = /[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}/;
    return function (input) {
        if (input == null)
            return null;
        if (re.test(input))
            return $filter('date')(new Date(input), 'yyyy-MM-dd HH:mm');
        else
            return null;
    };
});

coachManagementApplication.filter("dateConversionFilter", function ($filter) {
    var re = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
    return function (input) {
        if (input == null)
            return null;
        if (re.test(input))
            return $filter('date')(new Date(input), 'yyyy-MM-dd');
        else
            return null;
    };
});

coachManagementApplication.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}]);

coachManagementApplication.factory('requestRejector', ['$q', function ($q) {
    var requestRejector = {
        responseError: function (rejectReason) {
            return $q.reject(rejectReason);
        }
    };
    return requestRejector;
}]);

coachManagementApplication.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('requestRejector');
}]);

var profileControllerHandler = function ($scope, $resource) {
    $scope.profileAttributes = $resource('/findcoach/coach/profile/coachProfileAttributes').get();
    $scope.submit = function () {
        $resource('/findcoach/coach/profile/cv').save($scope.profileAttributes, function () {
            $scope.status = "attributes have been updated"
        });
    }
};
coachManagementApplication.controller('profileController', ["$scope", "$resource", profileControllerHandler])

coachManagementApplication.factory("AddPadawanDataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias
    };
});

coachManagementApplication.factory("TrainingDataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias,
        programId: programId,
        cycleId: cycleId
    };
});

var trainingControllerHandler = function ($scope, TrainingDataService, $http) {
    $scope.endPoint = "/findcoach/coach/" + TrainingDataService.loggedCoachAlias + "/program/" + TrainingDataService.programId + "/cycle/" + TrainingDataService.cycleId + "/training";
    $scope.successRedirectUrl = "/findcoach/coach/" + TrainingDataService.loggedCoachAlias + "/program/" + TrainingDataService.programId + ".html";
    $scope.trainingData = {};
    var currentDate = new Date();
    $scope.trainingData.startDateTime = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate(), currentDate.getHours(), currentDate.getMinutes());
    $scope.trainingData.duration = "60";
    $scope.trainingData.content = "qqq";

    $scope.saveTraining = function () {

        var trainingData = {};
        var trainingStartDateTime = $scope.trainingData.startDateTime;
        trainingData.startDateTime = trainingStartDateTime.getFullYear() + "-" + ("0" + (trainingStartDateTime.getMonth() + 1)).slice(-2) + "-" + ("0" + trainingStartDateTime.getDate()).slice(-2) + " " +
            ("0" + trainingStartDateTime.getHours()).slice(-2) + ":" + ("0" + trainingStartDateTime.getMinutes()).slice(-2);
        trainingData.duration = $scope.trainingData.duration;
        trainingData.content = $scope.trainingData.content;

        $http({
            method: 'POST',
            url: $scope.endPoint,
            data: trainingData

        }).then(function successCallback(response) {
            window.location.href = $scope.successRedirectUrl;
        }, function errorCallback(response) {
            alert("error");
        });
    };
};

coachManagementApplication.controller("trainingController", ["$scope", "TrainingDataService", "$http", "CONFIG",
    "dateTimeConversionFilter", trainingControllerHandler]);


var programDetailsHandler = function ($scope, $location, $http, $uibModal) {
    $scope.init = function () {
        var path = $location.path();
        var params = path.split("/");
        var coachAlias = params[3];
        var programId = params[5].split(".").shift();

        console.log("coachAlias = " + coachAlias);
        console.log("programId = " + programId);
        $scope.coachAlias = coachAlias;
        $scope.programId = programId;

        var url = "/findcoach/coach/" + coachAlias + "/program/" + programId;

        $http.get(url).then(function (response) {
                var responseData = response.data;
                $scope.programName = responseData.programName;
                $scope.cycles = responseData.cycles;
            },
            function (response) {
            });

        $scope.open = function (cycleId) {
            console.info("Open modal to add training for cycle id = " + cycleId)
            $scope.cycleId = cycleId;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/js/popup-templates/manage-training-popup.html',
                controller: 'manageTrainingPopupController',
                backdrop: 'static',
                scope: $scope,
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.openCyclePopup = function (cycleId) {
            $scope.cycleId = cycleId;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/js/popup-templates/manage-cycle-popup.html',
                controller: 'manageCyclePopupController',
                backdrop: 'static',
                scope: $scope
            });
        }
    }

};
coachManagementApplication.controller("programDetailsController", ["$scope", "$location", "$http", '$uibModal', programDetailsHandler]);

coachManagementApplication.controller("manageTrainingPopupController", ["$scope", "$http", "$uibModalInstance",
    function ($scope, $http, $uibModalInstance) {

        $scope.reload = function () {
            window.location.reload();
        };
        $scope.init = function () {
            console.log("Modal controller coachAlias = " + $scope.coachAlias +
                " programId = " + $scope.programId + " cycleId = " + $scope.cycleId);

            $scope.url = $scope.composeUrlForSavingTraining();

            $('#trainingStartDate').datetimepicker({
                format: "YYYY-MM-DD HH:mm",
                stepping: 15
            });

            $('#trainingDescriptionEditor').summernote({
                toolbar: [
                    // [groupName, [list of button]]
                    ['style', ['bold', 'italic', 'underline', 'clear']],
                    ['font', ['strikethrough', 'superscript', 'subscript']],
                    ['fontsize', ['fontsize']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['height', ['height']]
                ]
            });
            $("#trainingStartDate").data('DateTimePicker').date(new Date());
            $scope.formIsValid = true;

            $scope.training = {
                duration: "60",
                title: "",
                description: "",
                repeat: true,
                repeatOnDays: ["monday", "wednesday", "friday"],
                repeatTerm: "3week"
            };

        }

        $scope.isDayInSelected = function (day) {
            return $scope.training.repeatOnDays.indexOf(day) > -1;
        };

        $scope.toggleRepeatDay = function (day) {
            var idx = $scope.training.repeatOnDays.indexOf(day);

            if (idx > -1) {
                $scope.training.repeatOnDays.splice(idx, 1);
            } else {
                $scope.training.repeatOnDays.push(day);
            }
        };

        $scope.closeModal = function () {
            $uibModalInstance.close();
        };

        $scope.composeUrlForSavingTraining = function () {
            console.log("Composing URL for add training coachAlias = " + $scope.coachAlias +
                " programId = " + $scope.programId + " cycleId = " + $scope.cycleId);
            var url = "/findcoach/coach/" + $scope.coachAlias + "/program/" + $scope.programId + "/cycle/" + $scope.cycleId + "/training";
            console.log("URL for saving train" + url);
            return url;
        }

        $scope.submitNewTraining = function () {
            console.log("Submit new training");
            var trainingStartDate = $("#trainingStartDate").data('DateTimePicker').date();
            console.log("Training start date = " + JSON.stringify(trainingStartDate));
            console.log("Training start date = " + trainingStartDate.toISOString());
            console.log("Training duration = " + $scope.training.duration);
            console.log("Training title = " + $scope.training.title);
            var trainingDescription = $("#trainingDescriptionEditor").summernote("code");
            console.log("Training description = " + trainingDescription);
            console.log("Training repeat term = " + $scope.training.repeatTerm);

            var trainingToAdd = $scope.training;
            trainingToAdd.startDate = trainingStartDate;
            trainingToAdd.description = trainingDescription;
            $http.post(
                $scope.url,
                trainingToAdd
            ).then(function successCallback(response) {
                $scope.closeModal();
                $scope.reload();
            }, function errorCallback(response) {
                // TODO Process validation errors carefully and display appropriate message
                console.log("Response error");
                if (response.data.hasOwnProperty("errorMessage")) {
                    $scope.formIsValid = false;
                }
            });

        }
    }])
;
coachManagementApplication.controller("manageCyclePopupController", ["$scope", "$http", "$uibModalInstance",
    function ($scope, $http, $uibModalInstance) {

        $scope.reload = function () {
            window.location.reload();
        };
        $scope.init = function () {
            console.log("Modal controller coachAlias = " + $scope.coachAlias +
                " programId = " + $scope.programId + " cycleId = " + $scope.cycleId);

            $scope.url = $scope.composeCycleUrl();

            if ($scope.cycleId != null) {
                $http.get($scope.url)
                    .then(function successCallback(response) {
                        console.log("Cycle received");
                        $scope.updateModel(response.data.name,
                            response.data.description, true);
                    }, function errorCallback(response) {
                        console.log("Ups cycle has not been received");
                    });

            }

            $('#cycleDescriptionEditor').summernote({
                toolbar: [
                    // [groupName, [list of button]]
                    ['style', ['bold', 'italic', 'underline', 'clear']],
                    ['font', ['strikethrough', 'superscript', 'subscript']],
                    ['fontsize', ['fontsize']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['height', ['height']]
                ]
            });

            $scope.updateModel("", "", true);

        }

        $scope.updateModel = function (cycleName, cycleDescription, formValid) {
            $scope.formIsValid = formValid;
            $('#cycleDescriptionEditor').summernote("code", cycleDescription);
            $scope.cycle = {
                name: cycleName
            };
        }

        $scope.closeModal = function () {
            $uibModalInstance.close();
        };

        $scope.composeCycleUrl = function () {
            console.log("Composing URL for add training coachAlias = " + $scope.coachAlias +
                " programId = " + $scope.programId + " cycleId = " + $scope.cycleId);
            var url = "/findcoach/coach/" + $scope.coachAlias + "/program/" + $scope.programId + "/cycle";
            if ($scope.cycleId != null) {
                url += "/" + $scope.cycleId;
            }
            console.log("URL for saving train" + url);
            return url;
        }


        $scope.submitCycle = function () {
            console.log("Submit cycle");
            console.log("Cycle name = " + $scope.cycle.name);
            var description = $("#cycleDescriptionEditor").summernote("code");
            console.log("Cycle description = " + description);

            var cycle = $scope.cycle;
            cycle.description = description;
            var requestMethod = ($scope.cycleId == null) ? 'PUT' : 'POST';

            $http({
                method: requestMethod,
                url: $scope.url,
                data: cycle
            }).then(function successCallback(response) {
                $scope.closeModal();
                $scope.reload();
            }, function errorCallback(response) {
                // TODO Process validation errors carefully and display appropriate message
                console.log("Response error");
                if (response.data.hasOwnProperty("errorMessage")) {
                    $scope.formIsValid = false;
                }
            });

        }
    }]);

