coachManagementApplication.controller("managePadawanProgramPopupController", ["$scope", "$http", "$uibModalInstance",
    function ($scope, $http, $uibModalInstance) {
        $scope.goals = [
            {
                value: "FAT_BURN",
                label: "Сжигаем жирок"
            },
            {
                value: "STRENGTH",
                label: "Развиваем силушку"
            },
            {
                value: "ENDURANCE",
                label: "Развиваем выносливость"
            }
        ];

        $scope.init = function () {
            $scope.program = {};

        }


        $scope.closeModal = function () {
            $uibModalInstance.close();
        }

        $scope.selectGoal = function (goalValue) {
            for (var i in $scope.goals) {
                var goal = $scope.goals[i];
                if (goal.value.toLowerCase() == goalValue.toLowerCase()) {
                    $scope.selectedGoal = goal;
                }
            }
        }

        $scope.submit = function () {
            $scope.program.programId = $scope.programId;
            $scope.program.goal = $scope.selectedGoal.value;
            var method = ($scope.programId == null) ? "POST" : "PUT";
            var url = $scope.calculateUrlToManageProgram($scope.coachAlias, $scope.padawanId, $scope.programId)
            console.log("Url to work with program is " + url)
            $http({
                method: method,
                url: url,
                data: $scope.program

            }).then(function successCallback(response) {
                console.log("Valar Morgulis");

                //window.location.reload();
            }, function errorCallback(response) {
                alert("error");
            });

        }


    }
]);
