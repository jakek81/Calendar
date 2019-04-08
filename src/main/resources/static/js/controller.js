app.controller('controller', function ($scope, $http) {
    $http.get("/monthNotes")
            .then(function (response) {
                if (isValidResponse(response)) {
                    $scope = createCalendar($scope, response);
                    $scope.isVisible = "true";
                }
            });

    $scope.nextClicked = function () {
        var year = $scope.date.year;
        var month = $scope.date.month;

        if (month == 12) {
            year = year + 1;
            month = 1;
        } else {
            month = month + 1;
        }

        getMonthNotes($http, $scope, year, month);
    }

    $scope.prevClicked = function () {
        var year = $scope.date.year;
        var month = $scope.date.month;

        if (month == 1) {
            year = year - 1;
            month = 12;
        } else {
            month = month - 1;
        }

        getMonthNotes($http, $scope, year, month);
    }

});

function getMonthNotes($http, $scope, year, month) {
    $http.get("/monthNotes?year=" + year + "&month=" + month)
            .then(function (response) {
                if (isValidResponse(response)) {
                    $scope = createCalendar($scope, response);
                }
            });
}

function isValidResponse(response) {
    if (typeof response.data.year === 'undefined')
        return false
    if (typeof response.data.month === 'undefined')
        return false
    if (typeof response.data.monthName === 'undefined')
        return false
    return true;
}


function getNote(notes, day) {
    for (var i = 0; i < notes.length; i++) {
        if (notes[i].noteDate.substring(8, 10) == day)
            return notes[i].note;
    }
    return ''
}

function createCalendar($scope, response) {
    $scope.date = {monthName: response.data.monthName, month: response.data.month, year: response.data.year};

    var calendar = [];
    var firstDay = response.data.firstDay;
    var numberOfDays = response.data.numberOfDays;
    var weeks = Math.ceil((firstDay + numberOfDays) / 7);

    for (var i = 0; i < weeks; i++) {
        var weekNumber = response.data.weeks[i];
        var days = Array(7);
        var notes = Array(7);

        for (var j = 0; j < 7; j++) {
            var day = i * 7 + j - firstDay + 1;

            if (day > 0 && day <= numberOfDays) {
                days[j] = day;
                notes[j] = getNote(response.data.notes, day);
            } else {
                days[j] = '';
                notes[j] = '';
            }
        }

        var week = {week: weekNumber, notes, days};
        calendar.push(week);
    }

    $scope.calendar = calendar;
    return $scope;
}