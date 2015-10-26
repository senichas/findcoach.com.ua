/**
 * Created by Daemon on 20.10.2015.
 */
$(document).ready(function () {
    $('#status').change(function () {
        var statusUpdate = {
            status: $("#status :selected").val()
        };
        $.ajax({
            url: "padawan/status"
            , type: 'POST'
            , data: statusUpdate
            , acync: true
            , dataType: 'json'
            , success: function (res) {
                $("#statusResponseHolder").text(res);
                setTimeout(hiddenStatusIndicator, 5000);
            }
            ,error: function(res){
                var errString = JSON.parse(res.responseText);
                $("#statusResponseHolder").text(errString.errorMessege+"; "+ errString.exceptionMessege);
                setTimeout(hiddenStatusIndicator, 5000);
            }
        });
        return false;
    })
})

function hiddenStatusIndicator(){
    $("#statusResponseHolder").text("");
}

