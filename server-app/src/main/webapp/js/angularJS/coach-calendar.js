coachManagementApplication.controller('coachCalendarController', ["$scope", "$http", "$location",
    function ($scope, $http, $location) {
        $scope.init = function () {
            console.log("start to initialize padawan list");
            var path = $location.path();
            var params = path.split("/");
            var coachAlias = params[3];
            $scope.coachAlias = coachAlias;

            $('#coachCalendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                lang: "ru",
                defaultDate: '2016-06-12',
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: [
                    {
                        title: 'All Day Event',
                        start: '2016-06-01'
                    },
                    {
                        title: 'Long Event',
                        start: '2016-06-07',
                        end: '2016-06-10'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2016-06-09T16:00:00'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2016-06-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: '2016-06-11',
                        end: '2016-06-13'
                    },
                    {
                        title: 'Meeting',
                        start: '2016-06-12T10:30:00',
                        end: '2016-06-12T12:30:00'
                    },
                    {
                        title: 'Lunch',
                        start: '2016-06-12T12:00:00'
                    },
                    {
                        title: 'Meeting',
                        start: '2016-06-12T14:30:00'
                    },
                    {
                        title: 'Happy Hour',
                        start: '2016-06-12T17:30:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2016-06-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2016-06-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2016-06-28'
                    }
                ]
            });

         /*   $scope.url = $scope.calculateUrlToRetrievePadawansList(coachAlias);
            $scope.padawanUrl = $scope.calculateUrlToManagePadawan(coachAlias);
            $http.get($scope.url)
                .then(function successCallback(response) {
                    console.log("Padawans received");
                    $scope.padawans = response.data;

                }, function errorCallback(response) {
                    console.log("Ups padawans have not been received");
                });
*/
            console.log("coachAlias = " + coachAlias);

        }
    }
]);
