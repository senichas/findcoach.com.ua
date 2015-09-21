
$(function () {
    $("#submit").click(function(){
    var email = {email : document.getElementById("email").value};
        alert(email);
        $.ajax({
            url: "email"
            , type: 'POST'
            , data: JSON.stringify(email)
            , acync: true
            , dataType: 'json'
            , success: function (res) {
                $("#email").value = res;
                alert(res);
            }
        });
        return false;
    })
})


