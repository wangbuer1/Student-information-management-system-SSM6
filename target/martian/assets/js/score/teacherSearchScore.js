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
    //根据班级Id搜索课程
    form.on('select(searchByClazzId)', function (data) {
        var clazzId = data.value;
        if (!sys.util.isEmpty(clazzId)) {
            sys.ajax.doAjax({
                type: 'get',
                url: ctx + "teacher/getCourse/" + clazzId,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        var options = "<option value=''>请选择</option>";
                        if (result.data && result.data.length > 0) {
                            for (var i = 0; i < result.data.length; i++) {
                                options += "<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>";
                            }
                        }
                        $(".popBox select[name='courseId']").html(options);
                        form.render("select");//重新渲染select
                    }
                }
            });
        }else {
            var options = "<option value=''>请选择</option>";
            $(".popBox select[name='courseId']").html(options);
            $(".popBox select[name='studentId']").html(options);
            $("#yearName").html("");
            $("#termName").html("");
            $("#departName").html("");
            form.render("select");//重新渲染select
        }
    });
    //根据课程Id查询未录入成绩的学生
    form.on('select(getStudentAndCourse)', function (data) {
        var courseId = data.value;
        if (!sys.util.isEmpty(courseId)) {
            sys.ajax.doAjax({
                type: 'get',
                url: ctx + "teacher/getStudentAndCourse?courseId="+courseId,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        $("#yearName").html(result.data.course.year);
                        $("#termName").html(result.data.course.termObj.label);
                        $("#departName").html(result.data.course.departName);
                        var options = "<option value=''>请选择</option>";
                        if (result.data.studentList && result.data.studentList.length > 0) {
                            for (var i = 0; i < result.data.studentList.length; i++) {
                                options += "<option value='" + result.data.studentList[i].id + "'>" + result.data.studentList[i].name + "</option>";
                            }
                        }
                        $(".popBox select[name='studentId']").html(options);
                        form.render("select");//重新渲染select
                    }
                }
            });
        }else {
            var options = "<option value=''>请选择</option>";
            $(".popBox select[name='studentId']").html(options);
            $("#yearName").html("");
            $("#termName").html("");
            $("#departName").html("");
            form.render("select");//重新渲染select
        }
    });
})

/**
 * 进入添加页面
 */
function goAdd() {
    $(".popBox").removeClass("layui-hide");
    var index = layer.open({
        title: '新增成绩',
        area: ['610px', '610px'],
        btn: ['保存', '取消'],
        btnAlign: 'c',
        type: 1,
        content: $('.popBox'),
        yes: function () {
            var clazzId = $(".popBox select[name='clazzId']").val();
            if (sys.util.isEmpty(clazzId)) {
                layer.tips("请选择班级", $(".popBox select[name='clazzId']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }
            var courseId = $(".popBox select[name='courseId']").val();
            if (sys.util.isEmpty(courseId)) {
                layer.tips("请选择课程", $(".popBox select[name='courseId']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }
            var studentId = $(".popBox select[name='studentId']").val();
            if (sys.util.isEmpty(studentId)) {
                layer.tips("请选择学生", $(".popBox select[name='studentId']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }

            var score = $(".popBox input[name='score']").val();
            if (sys.util.isEmpty(score)) {
                layer.tips("请输入成绩", $(".popBox input[name='score']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }
            var data = $(".popBox form").serialize2();
            sys.ajax.doAjax({
                type: 'post',
                url: ctx + "score/add",
                data: data,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        sysLayer.util.alert("保存成功", {
                            ok: function () {
                                layer.close(index);
                                window.location.href = ctx + "teacher/goSearchScore";
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
 * 进入修改页面
 */
function goUpdate(scoreId,score) {
    $(".popBox2").removeClass("layui-hide");
    $(".popBox2 input[name='id']").val(scoreId)
    $(".popBox2 input[name='score']").val(score)
    var index = layer.open({
        title: '修改成绩',
        area: ['400px', '300px'],
        btn: ['保存', '取消'],
        btnAlign: 'c',
        type: 1,
        content: $('.popBox2'),
        yes: function () {
            var score = $(".popBox2 input[name='score']").val();
            if (sys.util.isEmpty(score)) {
                layer.tips("请输入成绩", $(".popBox2 input[name='score']").parent(), {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                });
                return;
            }
            var data = $(".popBox2 form").serialize2();
            sys.ajax.doAjax({
                type: 'post',
                url: ctx + "score/update",
                data: data,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        sysLayer.util.alert("保存成功", {
                            ok: function () {
                                layer.close(index);
                                window.location.href = ctx + "teacher/goSearchScore";
                            }
                        });
                    } else {
                        sysLayer.util.alert(result.msg);
                    }
                }
            });
        },
        end: function () {
            $(".popBox2 form")[0].reset();
            $(".popBox2").addClass("layui-hide");
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
        url: ctx + "score/search",
        data: $("#searchForm").serializeJson(),
        callback: function (data) {
            var tr = "";
            var dataList = data.list;
            if (dataList.length > 0) {
                for (var i = 0; i < dataList.length; i++) {
                    tr += "<tr>";
                    tr += "<td class='center'>" + ((i + 1) + (data.pageNo - 1) * data.pageSize) + "</td>";
                    tr += "<td class='center'>" + dataList[i].studentName + "</td>";
                    tr += "<td class='center'>" + dataList[i].year + "</td>";
                    tr += "<td class='center'>" + dataList[i].termObj.label + "</td>";
                    tr += "<td class='center'>" + dataList[i].departName + "</td>";
                    tr += "<td class='center'>" + dataList[i].clazzName + "</td>";
                    tr += "<td class='center'>" + dataList[i].teacherName + "</td>";
                    tr += "<td class='center'>" + dataList[i].name + "</td>";
                    tr += "<td class='center'>" + dataList[i].score + "</td>";
                    tr += "<td class='center'>" + dataList[i].createTime + "</td>";
                    tr += "<td class='center'>" +
                        "<a class='layui-btn layui-btn-warm layui-btn-mini' onclick='goUpdate(\"" + dataList[i].scoreId + "\","+dataList[i].score+")'>编辑</a>" +
                        "</td>";
                    tr += "</tr>";
                }

            } else {
                tr = "<td colspan='11' class='center'>暂无数据</td>";
            }
            $("#scoreTable tbody").html(tr);
        }
    }
    //分页初始化
    sysPaging.init(pagingParams)
}