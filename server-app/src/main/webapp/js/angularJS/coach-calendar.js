coachManagementApplication.controller('coachCalendarController', ["$scope", "$http", "$location",
    function ($scope, $http, $location) {
        $scope.init = function () {
            console.log("start to initialize calenadar");
            var path = $location.path();
            var params = path.split("/");
            var coachAlias = params[3];
            $scope.coachAlias = coachAlias;
            $scope.url = $scope.calculateUrlToRetrieveEvents(coachAlias);
            if ($location.search().padawanId) {
                $scope.padawanId = $location.search().padawanId;
            }

            $('#coachCalendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                lang: "ru",
                defaultDate: new Date(),
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                //viewRender: $scope.dateRangeChangedHandler,
                events: function (start, end, timezone, callback) {
                    $scope.dateRangeChangedHandler(start, end, timezone, callback)
                }
            });


            console.log("coachAlias = " + coachAlias);


        }

        $scope.calculateUrlToRetrieveEvents = function (coachAlias) {
            return "/findcoach/coach/" + coachAlias + "/calendar/events";
        }

        $scope.getCalendarDateRange = function () {
            var calendar = $('#coachCalendar').fullCalendar('getCalendar');
            var view = calendar.view;
            var start = view.start._d;
            var end = view.end._d;
            var dates = {start: start, end: end};
            console.log(dates);
            return dates;
        }

        $scope.dateRangeChangedHandler = function (start, end, timezone, callback) {
            console.log("Date range changed");
            var range = $scope.getCalendarDateRange();
            var params = {
                startDate: range.start,
                endDate: range.end
            };
            if ($scope.padawanId) {
                params.padawanId = $scope.padawanId;
            }
            $http({
                method: "GET",
                url: $scope.url,
                params:params
            }).then(function successCallback(response) {
                console.log("Calendars events received");
                callback(response.data.events);


            }, function errorCallback(response) {
                console.log("Ups. Calendars events have not been received");
            });
        }
    }
]);
