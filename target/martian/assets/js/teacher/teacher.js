$(function () {
    //搜索
    search();
    layui.laydate.render({
        elem: '#birthday',
        theme: '#1E9FFF'
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
        url: ctx + "teacher/detail?teacherId=" + id,
        async: false,//同步
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                console.log(result.data)
                $(".popBox input[name='userName']").val(result.data.userName);
                $(".popBox input[name='name']").val(result.data.name);
                $(".popBox input[name='sex']").each(function(i,item){
                    if($(item).val()==result.data.sexObj.code){
                        $(item).prop('checked',true);
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
                maxlength: "教师名称最多20个字符"
            },
            name: {
                required: "请输入教师名称",
                maxlength: "教师名称最多20个字符"
            }
        }
    })
}

/**
 * 弹出院系框
 */
function popBox(id) {
    //移出禁用
    $(".popBox select[name='departId']").attr("disabled",false);
    var title = '新增教师';
    var requestUrl = ctx + "teacher/add";
    if (sys.util.isEmpty(id)) {//添加
        $(".popBox input[name='id']").val("");
        $(".popBox input[name='sex']:first").attr("checked",true);
        layui.form.render();//重新渲染表单
    } else {//修改
        title = '修改教师';
        requestUrl = ctx + "teacher/update";
        $(".popBox input[name='id']").val(id);
    }
    validatePopBoxForm();
    $(".popBox").removeClass("layui-hide");
    var index = layer.open({
        title: title,
        area: ['550px', '450px'],
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
                                window.location.href = ctx + "teacher/list";
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
                required: "姓名",
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
    goSaveOrUpdate("#updateForm", ctx + "teacher/goUpdatePersonInfo", true)
}
/**
 * 删除
 */
function del(teacherId) {
    sysLayer.util.confirm('您确定要删除吗？', {
        ok: function () {
            sys.ajax.doAjax({
                type: 'post',
                url: ctx + "teacher/del",
                data: {"teacherId": teacherId},
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
        url: ctx + "teacher/search",
        data: $("#searchForm").serializeJson(),
        callback: function (data) {
            var tr = "";
            var dataList = data.list;
            if (dataList.length > 0) {
                for (var i = 0; i < dataList.length; i++) {
                    tr += "<tr>";
                    tr += "<td class='center'>" + ((i + 1) + (data.pageNo - 1) * data.pageSize) + "</td>";
                    tr += "<td class='center'>" + dataList[i].userName + "</td>";
                    tr += "<td class='center'>" + dataList[i].name + "</td>";
                    tr += "<td class='center'>" + dataList[i].sexObj.label + "</td>";
                    tr += "<td class='center'>" + dataList[i].birthday + "</td>";
                    tr += "<td class='center'>" + dataList[i].createTime + "</td>";
                    tr += "<td class='center'>" +
                        "<a class='layui-btn layui-btn-warm layui-btn-mini' onclick='goUpdate(\"" + dataList[i].id + "\")'>编辑</a>" +
                        "<a class='layui-btn layui-btn-danger layui-btn-mini' onclick='del(\"" + dataList[i].id + "\")'>删除</a>" +
                        "</td>";
                    tr += "</tr>";
                }

            } else {
                tr = "<td colspan='7' class='center'>暂无数据</td>";
            }
            $("#teacherTable tbody").html(tr);
        }
    }
    //分页初始化
    sysPaging.init(pagingParams)
}