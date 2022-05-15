$(function () {
})

/**
 * 进入添加页面
 */
function goAdd() {
    popBox();
}

/**
 * 进入修改页面
 */
function goUpdate(id) {
    sys.ajax.doAjax({
        type: 'get',
        url: ctx + "depart/detail?departId=" + id,
        async: false,//同步
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                $(".popBox input[name='name']").val(result.data.name);
                $(".popBox textarea[name='departDesc']").val(result.data.departDesc);
                popBox(id);
            } else {
                sysLayer.util.alert(result.msg);
            }
        }
    });
}


/**
 * 校验弹出框表单【添加、修改】
 * @returns {*|jQuery}
 */
function validatePopBoxForm() {
    return $('.popBox form').validate({
        rules: {
            name: {
                required: true,
                maxlength: 10
            }
        },
        messages: {
            name: {
                required: "请输入院系名称",
                maxlength: "院系名称最多10个字符"
            }
        }
    })
}

/**
 * 弹出院系框
 */
function popBox(id) {
    var title = '新增院系';
    var requestUrl = ctx + "depart/add";
    if (sys.util.isEmpty(id)) {//添加
        $(".popBox input[name='id']").val("");
    } else {//修改
        title = '修改院系';
        requestUrl = ctx + "depart/update";
        $(".popBox input[name='id']").val(id);
    }
    validatePopBoxForm();
    $(".popBox").removeClass("layui-hide");
    var index = layer.open({
        title: title,
        area: ['550px', '350px'],
        btn: ['保存', '取消'],
        btnAlign: 'c',
        type: 1,
        content: $('.popBox'),
        yes: function () {
            if (!validatePopBoxForm().form()) {
                return;
            }
            var data = $(".popBox form").serialize2();
            sys.ajax.doAjax({
                type: 'post',
                url: requestUrl,
                data: data,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        sysLayer.util.alert("保存成功", {
                            ok: function () {
                                layer.close(index);
                                window.location.href = ctx + "depart/list";
                            }
                        });
                    } else {
                        sysLayer.util.alert(result.msg);
                    }
                }
            });
        },
        end: function () {
            $(".popBox form")[0].reset();
            $(".popBox").addClass("layui-hide");
            layer.close(index);
        }
    });
}
/**
 * 删除
 */
function del(id) {
    sysLayer.util.confirm('您确定要删除吗？', {
        ok: function () {
            sys.ajax.doAjax({
                type: 'post',
                url: ctx + "depart/del",
                data: {"departId": id},
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        window.location.href = ctx + "depart/list";
                    } else {
                        sysLayer.util.alert(result.msg);
                    }
                }
            });
        }
    });
}