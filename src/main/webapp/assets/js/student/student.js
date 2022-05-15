$(function () {
    //搜索
    search();
    layui.laydate.render({
        elem: '#birthday',
        theme: '#1E9FFF'
    });
    var form = layui.form;
    //根据院系Id搜索班级
    form.on('select(searchByDepartId)', function (data) {
        var departId = data.value;
        if (!sys.util.isEmpty(departId)) {
            sys.ajax.doAjax({
                type: 'get',
                url: ctx + "clazz/search/" + departId,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        var options = "<option value=''>请选择</option>";
                        if (result.data && result.data.length > 0) {
                            for (var i = 0; i < result.data.length; i++) {
                                options += "<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>";
                            }
                        }
                        $(".popBox select[name='clazzId']").html(options);
                        layui.form.render("select");//重新渲染select
                    }
                }
            });
        }
    });
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
        url: ctx + "student/detail?studentId=" + id,
        async: false,//同步
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                $(".popBox input[name='stuNo']").val(result.data.stuNo);
                $(".popBox input[name='userName']").val(result.data.userName).attr("readonly","readonly");
                $(".popBox input[name='name']").val(result.data.name);
                $(".popBox select[name='departId']").val(result.data.departId);
                var clazzId = result.data.clazzId;
                if (!sys.util.isEmpty(result.data.departId)) {
                    sys.ajax.doAjax({
                        type: 'get',
                        url: ctx + "clazz/search/" + result.data.departId,
                        dataType: 'json',
                        success: function (result) {
                            if (result.code == 0) {
                                var options = "<option value=''>请选择</option>";
                                if (result.data && result.data.length > 0) {
                                    for (var i = 0; i < result.data.length; i++) {
                                        options += "<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>";
                                    }
                                }
                                $(".popBox select[name='clazzId']").html(options);
                                $(".popBox select[name='clazzId']").val(clazzId);
                                layui.form.render("select");//重新渲染select
                            }
                        }
                    });
                }

                $(".popBox input[name='sex']").each(function (i, item) {
                    if ($(item).val() == result.data.sexObj.code) {
                        $(item).prop('checked', true);
                    }
                });
                $(".popBox input[name='birthday']").val(result.data.birthday);
                layui.form.render();//重新渲染表单
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
            userName: {
                required: true,
                maxlength: 20
            },
            name: {
                required: true,
                maxlength: 20
            }
        },
        messages: {
            userName: {
                required: "请输入用户名",
                maxlength: "学生名称最多20个字符"
            },
            name: {
                required: "请输入学生名称",
                maxlength: "学生名称最多20个字符"
            }
        }
    })
}

/**
 * 弹出院系框
 */
function popBox(id) {
    //移出禁用
    $(".popBox select[name='departId']").attr("disabled", false);
    var title = '新增学生';
    var requestUrl = ctx + "student/add";
    if (sys.util.isEmpty(id)) {//添加
        $(".popBox input[name='id']").val("");
        $(".popBox input[name='sex']:first").attr("checked", true);
        //获取学号
        sys.ajax.doAjax({
            type: 'get',
            url: ctx + "student/getStuNo",
            async: false,//同步
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    $(".popBox input[name='stuNo']").val(result.data);
                } else {
                    sysLayer.util.alert(result.msg);
                }
            }
        });
        layui.form.render();//重新渲染表单
    } else {//修改
        title = '修改学生';
        requestUrl = ctx + "student/update";
        $(".popBox input[name='id']").val(id);
    }
    validatePopBoxForm();

    $(".popBox").removeClass("layui-hide");
    var index = layer.open({
        title: title,
        area: ['600px', '620px'],
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
                                window.location.href = ctx + "student/list";
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
 * 校验弹出框表单【修改个人信息】
 * @returns {*|jQuery}
 */
function validateUpdateForm() {
    return $('#updateForm').validate({
        rules: {
            name: {
                required: true,
                maxlength: 20
            }
        },
        messages: {
            name: {
                required: "请输入姓名",
                maxlength: "姓名最多20个字符"
            }
        }
    })
}
/**
 * 【修改个人信息】
 */
function updatePerson() {
    if (!validateUpdateForm().form()) {
        return;
    }
    goSaveOrUpdate("#updateForm", ctx + "student/goUpdatePersonInfo", true)
}
/**
 * 删除
 */
function del(studentId) {
    sysLayer.util.confirm('您确定要删除吗？', {
        ok: function () {
            sys.ajax.doAjax({
                type: 'post',
                url: ctx + "student/del",
                data: {"studentId": studentId},
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        search();
                    } else {
                        sysLayer.util.alert(result.msg);
                    }
                }
            });
        }
    });
}
/**
 * 分页搜索
 */
function search() {
    //分页参数
    var pagingParams = {
        url: ctx + "student/search",
        data: $("#searchForm").serializeJson(),
        callback: function (data) {
            var tr = "";
            var dataList = data.list;
            if (dataList.length > 0) {
                for (var i = 0; i < dataList.length; i++) {
                    tr += "<tr>";
                    tr += "<td class='center'>" + ((i + 1) + (data.pageNo - 1) * data.pageSize) + "</td>";
                    tr += "<td class='center'>" + dataList[i].stuNo + "</td>";
                    tr += "<td class='center'>" + dataList[i].userName + "</td>";
                    tr += "<td class='center'>" + dataList[i].name + "</td>";
                    tr += "<td class='center'>" + dataList[i].sexObj.label + "</td>";
                    tr += "<td class='center'>" + dataList[i].birthday + "</td>";
                    tr += "<td class='center'>" + dataList[i].departName + "</td>";
                    tr += "<td class='center'>" + dataList[i].clazzName + "</td>";
                    tr += "<td class='center'>" + dataList[i].createTime + "</td>";
                    tr += "<td class='center'>" +
                        "<a class='layui-btn layui-btn-warm layui-btn-mini' onclick='goUpdate(\"" + dataList[i].id + "\")'>编辑</a>" +
                        "<a class='layui-btn layui-btn-danger layui-btn-mini' onclick='del(\"" + dataList[i].id + "\")'>删除</a>" +
                        "</td>";
                    tr += "</tr>";
                }

            } else {
                tr = "<td colspan='10' class='center'>暂无数据</td>";
            }
            $("#studentTable tbody").html(tr);
        }
    }
    //分页初始化
    sysPaging.init(pagingParams)
}