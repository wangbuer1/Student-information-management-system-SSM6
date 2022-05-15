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
        <form id="updateForm" action="${ctx}/reader/update" class="layui-form myForm">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>用户名：</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="id" value="${reader.id}" type="hidden">
                        <input class="layui-input" name="userName" placeholder="用户名" value="${reader.userName}" readonly>
                    </div>
                </div>
            </div>
           <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>借阅证编号：</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="cardNo" placeholder="借阅证编号" value="${reader.cardNo}" readonly>
                    </div>
                    <div class="layui-form-mid layui-word-aux">借阅证编号由系统自动生成</div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>类型：</label>
                    <div class="layui-input-inline">
                        <select name="typeId">
                            <c:forEach items="${readerTypeList}" var="type">
                                <option value="${type.id}" <c:if test="${type.id==reader.typeId}"></c:if>>${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>读者名称：</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="name" placeholder="读者名称" value="${reader.name}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-block">
                    <c:forEach items="${sexEnum}" var="sex" varStatus="i">
                        <input type="radio" name="sex" value="${sex.getCode()}" title="${sex.getLabel()}" <c:if test="${sex.getCode()==reader.sex.getCode()}"> checked=""</c:if>>
                    </c:forEach>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>手机号：</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="mobile" placeholder="手机号" maxlength="11" value="${reader.mobile}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>身份证号：</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" type="text" name="idNo" placeholder="身份证号" value="${reader.idNo}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱：</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" type="text" name="email" placeholder="邮箱" value="${reader.email}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-inline">
                        <button class="layui-btn" type="button" onclick="update(1)">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/reader/update.js"></script>
</myJs>
</body>
</html>