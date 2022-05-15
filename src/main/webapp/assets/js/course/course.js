$(function () {
    //搜索
    search();
    layui.laydate.render({
        elem: '#year',
        theme: '#1E9FFF',
        type:'year'
    });
    layui.laydate.render({
        elem: '#searchYear',
        theme: '#1E9FFF',
        type:'year'
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
                        form.render("select");//重新渲染select
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
        url: ctx + "course/detail?courseId=" + id,
        async: false,//同步
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                console.log(result.data)
                $(".popBox input[name='year']").val(result.data.year).attr("disabled", true);
                $(".popBox input[name='name']").val(result.data.name);
                $(".popBox select[name='term']").val(result.data.termObj.code).attr("disabled", true);
                $(".popBox select[name='departId']").val(result.data.departId);
                $(".popBox select[name='teacherId']").val(result.data.teacherId).attr("disabled", true);
                var clazzId  = result.data.clazzId;
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
                            $(".popBox select[name='clazzId']").attr("disabled", true);
                            $(".popBox select[name='departId']").attr("disabled", true);
                            layui.form.render("select");//重新渲染select
                        }
                    }
                });
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
    return  $('.popBox form').validate({
        rules: {
            year: {
                required: true
            },
            name: {
                required: true,
                maxlength: 20
            }
        },
        messages: {
            year: {
                required: "请选择年份"
            },
            term: {
                minlength: "请选择学期"
            },
            name: {
                required: "请输入课程名称",
                maxlength: "课程名称最多20个字符"
            }
        }
    })
}

/**
 * 弹出院系框
 */
function popBox(id) {
    //移出禁用
    var title = '新增课程';
    var requestUrl = ctx + "course/add";
    if (sys.util.isEmpty(id)) {//添加
        $(".popBox input[name='year']").attr("disabled",false);
        $(".popBox select[name='term']").attr("disabled",false);
        $(".popBox select[name='departId']").attr("disabled",false);
        $(".popBox select[name='clazzId']").attr("disabled",false);
        $(".popBox select[name='teacherId']").attr("disabled",false);

        $(".popBox input[name='id']").val("");
        $(".popBox input[name='sex']:first").attr("checked",true);
        layui.form.render();//重新渲染表单
    } else {//修改
        title = '修改课程';
        requestUrl = ctx + "course/update";
        $(".popBox input[name='id']").val(id);
    }
    validatePopBoxForm();
    $(".popBox").removeClass("layui-hide");
    var index = layer.open({
        title: title,
        area: ['610px', '610px'],
        btn: ['保存', '取消'],
        btnAlign: 'c',
        type: 1,
        content: $('.popBox'),
        yes: function () {
            if (!validatePopBoxForm().form()) {
                return;
            }
            var term = $(".popBox select[name='term']").val();
            if (sys.util.isEmpty(term)) {
                layer.tips("请选择学期", $(".popBox select[name='term']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }
            var departId = $(".popBox select[name='departId']").val();
            if (sys.util.isEmpty(departId)) {
                layer.tips("请选择院系", $(".popBox select[name='departId']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }
            var clazzId = $(".popBox select[name='clazzId']").val();
            if (sys.util.isEmpty(clazzId)) {
                layer.tips("请选择班级", $(".popBox select[name='clazzId']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }
            var teacherId = $(".popBox select[name='teacherId']").val();
            if (sys.util.isEmpty(teacherId)) {
                layer.tips("请选择教师", $(".popBox select[name='teacherId']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
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
                                window.location.href = ctx + "course/list";
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
function del(courseId) {
    sysLayer.util.confirm('您确定要删除吗？', {
        ok: function () {
            sys.ajax.doAjax({
                type: 'post',
                url: ctx + "course/del",
                data: {"courseId": courseId},
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
        url: ctx + "course/search",
        data: $("#searchForm").serializeJson(),
        callback: function (data) {
            var tr = "";
            var dataList = data.list;
            if (dataList.length > 0) {
                for (var i = 0; i < dataList.length; i++) {
                    tr += "<tr>";
                    tr += "<td class='center'>" + ((i + 1) + (data.pageNo - 1) * data.pageSize) + "</td>";
                    tr += "<td class='center'>" + dataList[i].name + "</td>";
                    tr += "<td class='center'>" + dataList[i].year + "</td>";
                    tr += "<td class='center'>" + dataList[i].termObj.label + "</td>";
                    tr += "<td class='center'>" + dataList[i].departName + "</td>";
                    tr += "<td class='center'>" + dataList[i].clazzName + "</td>";
                    tr += "<td class='center'>" + dataList[i].teacherName + "</td>";
                    tr += "<td class='center'>" + dataList[i].createTime + "</td>";
                    tr += "<td class='center'>" +
                        "<a class='layui-btn layui-btn-warm layui-btn-mini' onclick='goUpdate(\"" + dataList[i].id + "\")'>编辑</a>" +
                        "<a class='layui-btn layui-btn-danger layui-btn-mini' onclick='del(\"" + dataList[i].id + "\")'>删除</a>" +
                        "</td>";
                    tr += "</tr>";
                }

            } else {
                tr = "<td colspan='9' class='center'>暂无数据</td>";
            }
            $("#courseTable tbody").html(tr);
        }
    }
    //分页初始化
    sysPaging.init(pagingParams)
}