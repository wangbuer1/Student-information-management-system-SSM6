<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>学生信息管理系统-登录</title>
    <link rel="stylesheet" href="${ctx}/assets/css/common.css">
    <link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css">
</head>
<body>

<div class="top">
    <div class="center layui-text layui-bg-blue">学生信息管理系统</div>
</div>
<div class="layui-container login">
    <div class="login-box">
        <form class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label layui-text" style="font-size: 15px;">帐号：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userName" autocomplete="off" class="layui-input width300"
                               placeholder="">
                    </div>
                </div>
            </div>

            <div class="layui-form-item" style="margin-top:35px;">
                <div class="layui-inline">
                    <label class="layui-form-label layui-text" style="font-size: 15px;">密码：</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" autocomplete="off" class="layui-input width300"
                               placeholder="">
                    </div>
                </div>
            </div>

            <div class="layui-form-item"  style="margin-top:50px;">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-radius layui-btn-normal loginBtn"
                            onclick="login()">立即登录
                    </button>
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 30px;">
                <div class="layui-input-block layui-text" id="error"></div>
            </div>
        </form>
    </div>
</div>
<script src="${ctx}/assets/layui/layui.all.js" type="text/javascript"></script>
<script>
    var $ = layui.$, jQuery = layui.$;
    var ctx = '${ctx}/';
</script>
<script src="${ctx}/assets/js/global.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/user/login.js" type="text/javascript"></script>
</body>
</html>