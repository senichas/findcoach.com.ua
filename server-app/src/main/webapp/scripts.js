
$(function () {
    $("#submit").click(function(){
    var email = {email : document.getElementById("email").value};
        $.ajax({
            url: "email"
            , type: 'POST'
            , data: JSON.stringify(email)
            , acync: true
            , dataType: 'json'
            , success: function (res) {
                window.location = res.redirectLink;
            }
        });
        return false;
    })
})


