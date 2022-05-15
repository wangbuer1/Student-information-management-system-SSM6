<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>班级管理</title>
</head>
<body>
<div class="layui-body">
    <div class="breadcrumb">
        <span class="layui-breadcrumb">
              <a>班级管理</a>
              <a><cite>班级列表</cite></a>
        </span>
        <span class="right action">
             <button class="layui-btn layui-btn-normal" type="button" onclick="goAdd()"><i
                     class="layui-icon">&#xe608;</i>新增班级</button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <div class="search">
            <blockquote class="layui-elem-quote">
                <form id="searchForm" class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">班级名称：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" name="clazzName" placeholder="班级名称">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">院系：</label>
                        <div class="layui-input-inline">
                            <select name="departId">
                                <option value="">请选择</option>
                                <c:forEach items="${departList}" var="d">
                                    <option value="${d.id}">${d.name}</option>
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
            <table class="layui-table" id="clazzTable">
                <colgroup>
                    <col width="80">
                    <col>
                    <col>
                    <col width="150">
                </colgroup>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>班级名称</th>
                        <th>院系</th>
                        <th>班级描述</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div id="pageId" style="text-align: right;"></div>
        </div>
    </div>
</div>
<!-- 班级弹出框-->
<div class="popBox layui-hide">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>院系：</label>
                <div class="layui-input-inline">
                    <select id="t" name="departId">
                        <option value="">请选择</option>
                        <c:forEach items="${departList}" var="d">
                            <option value="${d.id}">${d.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>班级名称：</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id" autocomplete="off" class="layui-input">
                    <input type="text" name="name" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">班级描述：</label>
                <div class="layui-input-inline">
                    <textarea name="clazzDesc" class="layui-textarea width250"></textarea>
                </div>
            </div>
        </div>
    </form>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/clazz/clazz.js"></script>
</myJs>
</body>
</html>