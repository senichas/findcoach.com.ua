var coachManagementApplication = angular.module('coachManagement', ["ngResource"]);
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

coachManagementApplication.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}]);


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


coachManagementApplication.factory("CycleDataService", function () {

    return {
        loggedCoachAlias: loggedCoachAlias,
        programId: programId,
        cycleName: cycleName,
        cycleStartDate: cycleStartDate,
        cycleEndDate: cycleEndDate,
        cycleNotes: cycleNotes,
        cycleId: cycleId
    };
});

var saveCycleControllerHandler = function ($scope, CycleDataService, $http) {
    $scope.endPoint = "/findcoach/coach/" + CycleDataService.loggedCoachAlias + "/program/" + CycleDataService.programId + "/cycle/";
    $scope.successRedirectUrl = "/findcoach/coach/" + CycleDataService.loggedCoachAlias + "/program/" + CycleDataService.programId + ".html";

    $scope.cycleData = {};
    $scope.cycleData.name = CycleDataService.cycleName;
    $scope.cycleData.notes = CycleDataService.cycleNotes;
    $scope.cycleData.startDate = parseDateFromString(CycleDataService.cycleStartDate);
    $scope.cycleData.endDate = parseDateFromString(CycleDataService.cycleEndDate);
    $scope.cycleData.cycleId = CycleDataService.cycleId;
    $scope.saveCycle = function () {

        var cycleData = {};
        cycleData.name = $scope.cycleData.name;
        cycleData.notes = $scope.cycleData.notes;
        cycleData.startDate = $scope.cycleData.startDate.getTime();
        cycleData.endDate = $scope.cycleData.endDate.getTime();
        cycleData.cycleId = $scope.cycleData.cycleId;

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

coachManagementApplication.controller("cycleController", ["$scope", "CycleDataService", "$http", saveCycleControllerHandler]);


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


var programDetailsHandler = function ($scope, $location, $http) {
    $scope.init = function () {
        var path = $location.path();
        var params = path.split("/");
        var coachAlias = params[3];
        var programId = params[5].split(".").shift();

        console.log("coachAlias = " + coachAlias);
        console.log("programId = " + programId);

        var url = "/findcoach/coach/" + coachAlias + "/program/" + programId;

        $http.get(url).then(function (response) {
                var responseData = response.data;
                $scope.programName = responseData.programName;
                $scope.cycles = responseData.cycles;
            },
            function (response) {
            });

    }

};
coachManagementApplication.controller("programDetailsController", ["$scope", "$location", "$http", programDetailsHandler]);


function parseDateFromString(str) {
    // str format should be yyyy/mm/dd. Separator can be anything e.g. / or -.
    if (str == null)
        return new Date();

    var yr = parseInt(str.substring(0, 4));
    var mon = parseInt(str.substring(5, 7));
    var dt = parseInt(str.substring(8, 10));
    var date = new Date(yr, mon - 1, dt);
    return date;
}
