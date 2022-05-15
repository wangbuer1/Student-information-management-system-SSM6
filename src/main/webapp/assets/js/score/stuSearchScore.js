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
})

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
                    tr += "</tr>";
                }

            } else {
                tr = "<td colspan='10' class='center'>暂无数据</td>";
            }
            $("#scoreTable tbody").html(tr);
        }
    }
    //分页初始化
    sysPaging.init(pagingParams)
}