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
coachManagementApplication.controller('padawansListController', ["$scope", "$resource", padawansListControllerHandler])

var profileControllerHandler = function ($scope, $resource) {
    $scope.profileAttributes = $resource('/findcoach/coach/profile/coachProfileAttributes').get();
    $scope.submit = function () {
        $resource('/findcoach/coach/profile/cv').save($scope.profileAttributes, function () {
            $scope.status = "attributes have been updated"
        });
    }
};
coachManagementApplication.controller('profileController', ["$scope", "$resource", profileControllerHandler])


coachManagementApplication.factory("DataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias,
        programId: programId
    };
});

var saveCycleControllerHandler = function ($scope, DataService, $http) {
    $scope.endPoint = "/findcoach/coach/" + DataService.loggedCoachAlias + "/program/" + DataService.programId + "/cycle/";
    $scope.successRedirectUrl = "/findcoach/coach/" + DataService.loggedCoachAlias + "/program/" + DataService.programId + ".html";

    $scope.cycleData = {};
    $scope.cycleData.name = "Вводный цикл";
    $scope.cycleData.notes = "УУууууу";
    $scope.cycleData.startDate = new Date();
    var endDate = new Date();
    $scope.cycleData.endDate = new Date(endDate.setMonth(endDate.getMonth() + 3));

    $scope.saveCycle = function () {

        var cycleData = {};
        cycleData.name = $scope.cycleData.name;
        cycleData.notes = $scope.cycleData.notes;
        cycleData.startDate = $scope.cycleData.startDate.getTime();
        cycleData.endDate = $scope.cycleData.endDate.getTime();

        $http({
            method: 'POST',
            url: $scope.endPoint,
            data: cycleData

        }).then(function successCallback(response) {
            window.location.href = $scope.successRedirectUrl;
        }, function errorCallback(response) {
            alert("error");
        });
    };
};

coachManagementApplication.controller("cycleController", ["$scope", "DataService", "$http", saveCycleControllerHandler]);


coachManagementApplication.factory("AddPadawanDataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias
    };
});


var addPadawanControllerHandler = function ($scope, AddPadawanDataService, $http) {
    $scope.endPoint = "/findcoach/coach/" + AddPadawanDataService.loggedCoachAlias + "/padawan-management/basic-info";
    $scope.successRedirectUrl = "/findcoach/coach/" + AddPadawanDataService.loggedCoachAlias + "/padawans.html";
    $scope.padawanData = {};
    $scope.padawanData.name = "Pertro Vasechkin";
    $scope.padawanData.email = "padawan@test.com";
    $scope.padawanData.gender = "MALE";
    $scope.padawanData.year = "1982";

    $scope.padawanMeasurement = {};
    $scope.padawanMeasurement.height = "178";
    $scope.padawanMeasurement.weight = "95";
    $scope.padawanMeasurement.fatPercentage = "35";

    $scope.padawanProgram = {};
    $scope.padawanProgram.name = "3 мес сжигание";
    $scope.padawanProgram.goal = "FAT_BURN";
    $scope.padawanProgram.notes = "УУууууу";
    $scope.padawanProgram.startDate = new Date();
    var endDate = new Date();
    $scope.padawanProgram.endDate = new Date(endDate.setMonth(endDate.getMonth() + 3));


    $scope.submitStep1 = function () {

        var addPadawanData = {};
        addPadawanData.padawanData = $scope.padawanData;
        addPadawanData.padawanMeasurement = $scope.padawanMeasurement;
        addPadawanData.padawanProgram = $scope.padawanProgram;

        $http({
            method: 'POST',
            url: $scope.endPoint,
            data: addPadawanData

        }).then(function successCallback(response) {
            window.location.href = $scope.successRedirectUrl;
        }, function errorCallback(response) {
            alert("error");
        });
    };
};
coachManagementApplication.controller("addPadawanController", ["$scope", "AddPadawanDataService", "$http", addPadawanControllerHandler]);

coachManagementApplication.factory("EditPadawanDataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias,
        loggedPadawanId: loggedPadawanId,
        dateBirthDay: dateBirthDay,
        padawanFirstName: padawanFirstName,
        padawanLastName: padawanLastName,
        padawanEmail: padawanEmail,
        padawanGender: padawanGender,
        padawanActive: padawanActive
    };
})
;

var editPadawanControllerHandler = function ($scope, EditPadawanDataService, $http) {
    $scope.padawanData = {};
    $scope.birthday = "";
    $scope.endPoint = "/findcoach/coach/" + EditPadawanDataService.loggedCoachAlias + "/padawan-management/" + EditPadawanDataService.loggedPadawanId + "/edit-padawan.html";
    $scope.successRedirectUrl = "/findcoach/coach/" + EditPadawanDataService.loggedCoachAlias + "/padawans.html";
    var padawanBirthday = new Date(EditPadawanDataService.dateBirthDay);
    $scope.padawanData.birthday = padawanBirthday;
    $scope.padawanData.firstName = EditPadawanDataService.padawanFirstName;
    $scope.padawanData.lastName = EditPadawanDataService.padawanLastName;
    $scope.padawanData.email = EditPadawanDataService.padawanEmail;
    $scope.padawanData.gender = EditPadawanDataService.padawanGender;
    $scope.padawanData.active = EditPadawanDataService.padawanActive;


    $scope.submitEditPadawan = function () {

        $http({
            method: 'POST',
            url: $scope.endPoint,
            data: $scope.padawanData

        }).then(function successCallback(response) {
            window.location.href = $scope.successRedirectUrl;
        }, function errorCallback(response) {
            alert("error");
        });
    };
};


coachManagementApplication.controller("editPadawanController", ["$scope", "EditPadawanDataService", "$http", editPadawanControllerHandler]);

coachManagementApplication.factory("EditProgramDataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias,
        loggedProgramId: loggedProgramId,
        programGoal: programGoal,
        programStartDate: programStartDate,
        programFinishDate: programFinishDate,
        programName: programName
    };
})
;

var editProgramControllerHandler = function ($scope, EditProgramDataService, $http) {
    $scope.programData = {};
    $scope.endPoint = "/findcoach/coach/" + EditProgramDataService.loggedCoachAlias + "/program/" + EditProgramDataService.loggedProgramId + "/edit-program.html";
    $scope.successRedirectUrl = "/findcoach/coach/" + EditProgramDataService.loggedCoachAlias + "/padawans.html";
    $scope.programData.name = EditProgramDataService.programName;
    $scope.programData.goal = EditProgramDataService.programGoal;
    var startDate = new Date(EditProgramDataService.programStartDate);
    var endDate = new Date(EditProgramDataService.programFinishDate);
    $scope.programData.start = startDate;
    $scope.programData.finish = endDate;

    $scope.submitEditProgram = function () {

        $http({
            method: 'POST',
            url: $scope.endPoint,
            data: $scope.programData

        }).then(function successCallback(response) {
            window.location.href = $scope.successRedirectUrl;
        }, function errorCallback(response) {
            alert("error");
        });
    };
};


coachManagementApplication.controller("editProgramController", ["$scope", "EditProgramDataService", "$http", editProgramControllerHandler]);


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

coachManagementApplication.controller("trainingController", ["$scope", "TrainingDataService", "$http", trainingControllerHandler]);
