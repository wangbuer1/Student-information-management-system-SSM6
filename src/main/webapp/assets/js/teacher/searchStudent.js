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
                $(".popBox input[name='userName']").val(result.data.userName).attr("readonly", "readonly");
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
 * 弹出院系框
 */
function popBox(id) {
    //移出禁用
    $(".popBox select[name='departId']").attr("disabled", false);
    var title = '查看详情';
    $(".popBox").removeClass("layui-hide");
    var index = layer.open({
        title: title,
        area: ['600px', '620px'],
        btn: ['关闭'],
        btnAlign: 'c',
        type: 1,
        content: $('.popBox'),
        end: function () {
            $(".popBox form")[0].reset();
            $(".popBox").addClass("layui-hide");
            layer.close(index);
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
                        "<a class='layui-btn layui-btn-warm layui-btn-mini' onclick='goUpdate(\"" + dataList[i].id + "\")'>详情</a>" +
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