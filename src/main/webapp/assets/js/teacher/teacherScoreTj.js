$(function () {
    //搜索
    search();
    var form = layui.form;
    //根据班级Id搜索课程
    form.on('select(searchByClazzId)', function (data) {
        var clazzId = data.value;
        if (!sys.util.isEmpty(clazzId)) {
            sys.ajax.doAjax({
                type: 'get',
                url: ctx + "teacher/getCourseList?clazzId=" + clazzId,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        var options = "<option value=''>请选择</option>";
                        if (result.data && result.data.length > 0) {
                            for (var i = 0; i < result.data.length; i++) {
                                options += "<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>";
                            }
                        }
                        $("select[name='courseId']").html(options);
                        form.render("select");//重新渲染select
                    }
                }
            });
        }else {
            var options = "<option value=''>请选择</option>";
            $("select[name='courseId']").html(options);
            form.render("select");//重新渲染select
        }
    });
})

/**
 * 分页搜索
 */
function search() {
    //分页参数
    var pagingParams = {
        url: ctx + "teacher/scoreTjSearch",
        data: $("#searchForm").serializeJson(),
        callback: function (data) {
            var tr = "";
            var dataList = data.list;
            if (dataList.length > 0) {
                for (var i = 0; i < dataList.length; i++) {
                    tr += "<tr>";
                    tr += "<td class='center'>" + ((i + 1) + (data.pageNo - 1) * data.pageSize) + "</td>";
                    tr += "<td class='center'>" + dataList[i].clazzName + "</td>";
                    tr += "<td class='center'>" + dataList[i].courseName + "</td>";
                    tr += "<td class='center'>" + dataList[i].avgScore + "</td>";
                    tr += "<td class='center'>" + dataList[i].num60 + "人</td>";
                    tr += "<td class='center'>" + dataList[i].num6080 + "人</td>";
                    tr += "<td class='center'>" + dataList[i].num80100 + "人</td>";
                    tr += "</tr>";
                }

            } else {
                tr = "<td colspan='7' class='center'>暂无数据</td>";
            }
            $("#scoreTable tbody").html(tr);
        }
    }
    //分页初始化
    sysPaging.init(pagingParams)
}