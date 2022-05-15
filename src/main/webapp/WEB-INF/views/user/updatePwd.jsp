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
              <a><cite>修改密码</cite></a>
        </span>
        <span class="right action">
               <button class="layui-btn" type="button" style="visibility: hidden"></button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <form class="layui-form myForm">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>新密码：</label>
                    <div class="layui-input-inline">
                        <input type="password" id="newPwd" class="layui-input" maxlength="16" name="newPwd" placeholder="新密码">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="tip">*</span>确认密码：</label>
                    <div class="layui-input-inline">
                        <input type="password" class="layui-input" maxlength="16" name="reNewPwd" placeholder="确认密码">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-normal" type="button" onclick="updatePwd()">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/user/updatePwd.js"></script>
</myJs>
</body>
</html>