$(document).ready(function () {
    $('#status').change(function () {
        var statusUpdate = {statusUpdate: $("#status :selected").val()};
        $.ajax({
            url: "status"
            , type: 'POST'
            , data: JSON.stringify(statusUpdate)
            , acync: true
            , dataType: 'json'
            , success: function (res) {
            }
        });
        return false;
    })
})

