coachManagementApplication.controller("managePadawanPopupController", ["$scope", "$http", "$uibModalInstance",
    function ($scope, $http, $uibModalInstance) {
        $scope.genders = [
            {
                value: "male",
                label: "Мужчина"
            },
            {
                value: "female",
                label: "Девушка"
            }
        ];

        $scope.closeModal = function () {
            $uibModalInstance.close();
        };

        $scope.initData = function (params) {
            $scope.padawan = {};
            $scope.padawan.firstName = params.firstName;
            $scope.padawan.lastName = params.lastName;
            $scope.padawan.email = params.email;
            $scope.selectGender(params.gender);
            $scope.padawan.birthday = params.birthday;
            $("#padawanBirthday").data('DateTimePicker').date(new Date(params.birthday));
            $scope.padawan.notes = $("#padawanDescriptionEditor").summernote("code", params.notes);
        }
        $scope.init = function () {
            if ($scope.padawanId != null) {
                $http.get($scope.padawanUrl + "/" + $scope.padawanId)
                    .then(function successCallback(response) {
                        $scope.initData(response.data);
                    }, function errorCallback(response) {
                        alert("error");
                    });
            }
            $scope.padawan = {};
            $('#padawanBirthday').datetimepicker({
                format: "YYYY-MM-DD",
                stepping: 15
            });

            $('#padawanDescriptionEditor').summernote({
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
        }

        $scope.selectGender = function (genderValue) {
            for (var i in $scope.genders) {
                var gender = $scope.genders[i];
                if (gender.value.toLowerCase() == genderValue.toLowerCase()) {
                    $scope.selectedGender = gender;
                }
            }
        }

        $scope.submit = function () {
            var m = $("#padawanBirthday").data('DateTimePicker').date();
            var result = moment(m).utc().add(m.utcOffset(), 'm');
            $scope.padawan.birthday = result;
            $scope.padawan.notes = $("#padawanDescriptionEditor").summernote("code");
            $scope.padawan.gender = $scope.selectedGender.value.toUpperCase();
            $scope.padawan.padawanId = $scope.padawanId;
            var method = ($scope.padawanId == null) ? "POST" : "PUT";
            $http({
                method: method,
                url: $scope.padawanUrl,
                data: $scope.padawan

            }).then(function successCallback(response) {
                window.location.reload();
            }, function errorCallback(response) {
                alert("error");
            });


        }
    }
]);
