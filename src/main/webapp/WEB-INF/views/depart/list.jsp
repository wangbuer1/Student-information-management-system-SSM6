<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>院系管理</title>
</head>
<body>
<div class="layui-body">
    <div class="breadcrumb">
        <span class="layui-breadcrumb">
              <a>院系管理</a>
              <a><cite>院系列表</cite></a>
        </span>
        <span class="right action">
             <button class="layui-btn layui-btn-normal" type="button" onclick="goAdd()"><i
                     class="layui-icon">&#xe608;</i>新增院系</button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <div class="table-list">
            <table class="layui-table" id="departTable">
                <colgroup>
                    <col width="80">
                    <col>
                    <col>
                    <col width="150">
                </colgroup>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>院系名称</th>
                        <th>院系描述</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${departList}" var="d" varStatus="i">
                        <tr>
                            <td class="center">${i.index+1}</td>
                            <td class="center">${d.name}</td>
                            <td class="center">${d.departDesc}</td>
                            <td class="center">
                                <a class='layui-btn layui-btn-warm layui-btn-mini' onclick="goUpdate('${d.id}')">编辑</a>
                                <a class='layui-btn layui-btn-danger layui-btn-mini' onclick="del('${d.id}')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- 院系弹出框-->
<div class="popBox layui-hide">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>院系名称：</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id" autocomplete="off" class="layui-input">
                    <input type="text" name="name" autocomplete="off" class="layui-input width250">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">院系描述：</label>
                <div class="layui-input-inline">
                    <textarea name="departDesc" class="layui-textarea width250"></textarea>
                </div>
            </div>
        </div>
    </form>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/depart/depart.js"></script>
</myJs>
</body>
</html>