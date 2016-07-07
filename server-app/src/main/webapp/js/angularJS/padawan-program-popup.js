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
            console.log("Valar Morgulis");
        }

        $scope.closeModal = function() {
            $uibModalInstance.close();
        }

        $scope.selectGoal = function(goalValue) {
            for (var i in $scope.goals) {
                var goal = $scope.goals[i];
                if (goal.value.toLowerCase() == goalValue.toLowerCase()) {
                    $scope.selectedGoal = goal;
                }
            }
        }


    }
]);
