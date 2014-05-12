function login(){
	var account = $("#account").val();
	var password = $("#password").val();
	if(account=="")
	{
		alert("账号不能为空");
		return false;
	}
	if(password=="")
	{
		alert("密码不能为空");
		return false;
	}
	var datajson = {"account":account, "password":password};
	var url = 'ajaxlogin.action';
	$.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: datajson,
        success: responseLogin,
        error: function () {
            //alert(RES.REQUESTWRONG);
        }
    });
}

var responseLogin = function(data, textStatus, jqXHR)
{
	if(data.status=="ok")
	{
		if($("#rememberPass").attr("checked")==true)
		{
			Cookie.setCookie("account2",$("#account").val());
			Cookie.setCookie("password2",$("#password").val());
		}
		else
		{
			Cookie.clearCookie("account2");	
			Cookie.clearCookie("password2");	
		}
		window.location.href="userAction_home.action";
	}
	else
	{
		alert("账号或密码错误");
	}
}