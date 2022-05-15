<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>教师管理</title>
</head>
<body>
<div class="layui-body">
    <div class="breadcrumb">
        <span class="layui-breadcrumb">
              <a>教师管理</a>
              <a><cite>教师列表</cite></a>
        </span>
        <span class="right action">
             <button class="layui-btn layui-btn-normal" type="button" onclick="goAdd()"><i
                     class="layui-icon">&#xe608;</i>新增教师</button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <div class="search">
            <blockquote class="layui-elem-quote">
                <form id="searchForm" class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">教师名称：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" name="teacherName" placeholder="请输入教师名称">
                        </div>
                    </div>
                    <button class="layui-btn layui-btn-normal" type="button" onclick="search()"><i class="layui-icon">&#xe615;</i>搜索
                    </button>
                </form>
            </blockquote>
        </div>
        <div class="table-list">
            <table class="layui-table" id="teacherTable">
                <colgroup>
                    <col width="80">
                    <col>
                    <col>
                    <col>
                    <col>
                    <col>
                    <col width="150">
                </colgroup>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>用户名</th>
                        <th>教师名称</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>添加时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div id="pageId" style="text-align: right"></div>
        </div>
    </div>
</div>
<!-- 教师弹出框-->
<div class="popBox layui-hide">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>用户名：</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id" autocomplete="off" class="layui-input">
                    <input type="text" name="userName" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">默认123456</div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>教师名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">性别：</label>
                <c:forEach items="${sexEnum}" var="sex" varStatus="i">
                    <input type="radio" name="sex" value="${sex.getCode()}" title="${sex.getLabel()}">
                </c:forEach>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">生日：</label>
                <div class="layui-input-inline">
                    <input type="text" name="birthday" class="layui-input" id="birthday" placeholder="yyyy-MM-dd">
                </div>
            </div>
        </div>
    </form>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/teacher/teacher.js"></script>
</myJs>
</body>
</html>