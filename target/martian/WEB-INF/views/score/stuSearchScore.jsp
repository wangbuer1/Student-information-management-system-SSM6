<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>成绩管理</title>
</head>
<body>
<div class="layui-body">
    <div class="breadcrumb">
        <span class="layui-breadcrumb">
              <a>成绩管理</a>
              <a><cite>成绩列表</cite></a>
        </span>
        <span class="right action">
               <button class="layui-btn" type="button" style="visibility: hidden"></button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <div class="search">
            <blockquote class="layui-elem-quote">
                <form id="searchForm" class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">年份：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="year" class="layui-input" id="searchYear" placeholder="请选择年份" readonly>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学期：</label>
                        <div class="layui-input-inline">
                            <select name="term">
                                <option value="">请选择</option>
                                <c:forEach items="${termEnum}" var="t">
                                    <option value="${t.code}">${t.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">教师：</label>
                        <div class="layui-input-inline">
                            <select name="teacherId">
                                <option value="">请选择</option>
                                <c:forEach items="${teacherList}" var="t">
                                    <option value="${t.id}">${t.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button class="layui-btn layui-btn-normal" type="button" onclick="search()"><i class="layui-icon">&#xe615;</i>搜索
                    </button>
                </form>
            </blockquote>
        </div>
        <div class="table-list">
            <table class="layui-table" id="scoreTable">
                <colgroup>
                    <col width="80">
                    <col>
                    <col>
                    <col>
                    <col>
                    <col>
                    <col>
                    <col>
                    <col>
                </colgroup>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>学生名称</th>
                        <th>年份</th>
                        <th>学期</th>
                        <th>院系</th>
                        <th>班级</th>
                        <th>教师</th>
                        <th>课程</th>
                        <th>成绩</th>
                        <th>添加时间</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div id="pageId" style="text-align: right"></div>
        </div>
    </div>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/score/stuSearchScore.js"></script>
</myJs>
</body>
</html>