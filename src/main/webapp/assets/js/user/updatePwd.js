/**
 * 修改密码
 */
function updatePwd() {
    if (!validateForm().form()) {
        return;
    }
    sys.ajax.doAjax({
        type: 'POST',
        url: ctx + 'user/updatePwd',
        data: $(".myForm").serialize2(),
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                sysLayer.util.alert("修改成功");
            } else {
                sysLayer.util.alert(result.msg);
            }
        }
    });
}
/**
 * 校验表单【修改密码】
 * @returns {*|jQuery}
 */
function validateForm() {
    return $('.myForm').validate({
        rules: {
            newPwd: {
                required: true,
                maxlength: 16
            },
            reNewPwd: {
                required: true,
                equalTo: "#newPwd"
            },
        },
        messages: {
            newPwd: {
                required: "请输入密码",
                maxlength: "密码最多16个字符"
            },
            reNewPwd: {
                required: "请输入确认密码",
                equalTo: "两次输入的密码不一致，请重新输入"
            }
        }
    })
}