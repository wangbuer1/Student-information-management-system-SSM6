<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>系统管理</title>
</head>
<body>
<div class="layui-body">
    <div class="breadcrumb">
        <span class="layui-breadcrumb">
              <a>系统管理</a>
              <a><cite>修改个人信息</cite></a>
        </span>
        <span class="right action">
               <button class="layui-btn" type="button" style="visibility: hidden"></button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <form id="updateForm" action="${ctx}/student/updatePersonInfo" class="layui-form myForm">
            <div class="layui-form-item">
                <label class="layui-form-label">学号：</label>
                <div class="layui-input-inline">
                    <div class="layui-form-mid layui-word-aux">${student.stuNo}</div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">院系：</label>
                <div class="layui-input-inline">
                    <div class="layui-form-mid layui-word-aux">${student.departName}</div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">班级：</label>
                <div class="layui-input-inline">
                    <div class="layui-form-mid layui-word-aux">${student.clazzName}</div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>姓名：</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="id" value="${student.id}" type="hidden">
                        <input class="layui-input" name="name" placeholder="姓名" value="${student.name}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-block">
                    <c:forEach items="${sexEnum}" var="sex" varStatus="i">
                        <input type="radio" name="sex" value="${sex.getCode()}" title="${sex.getLabel()}" <c:if test="${sex.getCode()==student.sex.getCode()}"> checked=""</c:if>>
                    </c:forEach>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>生日：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birthday" class="layui-input" id="birthday" placeholder="yyyy-MM-dd" value="${student.birthday}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-normal" type="button" onclick="updatePerson()">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/student/student.js"></script>
</myJs>
</body>
</html>