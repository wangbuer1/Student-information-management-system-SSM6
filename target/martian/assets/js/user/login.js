/**
 * 登录
 */
function login() {
    var userName = $("input[name='userName']").val();
    var password = $("input[name='password']").val();
    if (sys.util.isEmpty(userName)) {
        $("#error").html("请输入用户名")
        return;
    }
    if (sys.util.isEmpty(password)) {
        $("#error").html("请输入密码")
        return;
    }
    sys.ajax.doAjax({
        type: 'post',
        url: ctx + 'login',
        data: $(".login form").serialize2(),
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                window.location.href = ctx + "user/goUpdatePwd";
            } else {
                $("#error").html(result.msg)
            }
        }
    });
}