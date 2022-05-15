<%@ page import="com.martian.common.cache.SessionCache" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>学生信息管理系统-<sitemesh:write property='title'/></title>
    <link rel="stylesheet" href="${ctx}/assets/css/common.css">
    <link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/assets/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <sitemesh:write property='head'/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header layui-bg-blue">
        <div class="layui-logo" style="font-size: 22px;font-weight:bold;color:#F0F9F2">学生信息管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right" style="margin-right: 10px">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <%--<img src="http://t.cn/RCzsdCq" class="layui-nav-img">--%>
                    ${sessionScope.sessionUser.user.userName}
                </a>
               <dl class="layui-nav-child">
                    <dd><a href="${ctx}/user/goUpdatePwd">修改密码</a></dd>
                    <dd><a href="${ctx}/logout">退出系统</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side" style="background-color:#f5f5f5;">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" style="background-color: #f5f5f5;">
                <c:forEach items="${sessionScope.sessionUser.menuList}" var="m1">
                    <c:if test="${m1.pid=='0'}">
                        <li class="layui-nav-item">
                            <a href="${m1.url}" style="padding-left: 50px;color: #0C0C0C!important;font-weight: 600;font-size:14px;">${m1.name} <i class="fa fa-chevron-down"></i></a>
                            <c:if test="${m1.isLeaf.getCode()=='N'}">
                                <dl class="layui-nav-child" style="background-color: #f5f5f5 !important;">
                                    <c:forEach items="${sessionScope.sessionUser.menuList}" var="m2">
                                        <c:if test="${m1.id == m2.pid}">
                                            <dd><a href="${ctx}/${m2.url}" style="padding-left: 65px;color: #0C0C0C;font-size: 14px;">${m2.name}</a></dd>
                                        </c:if>
                                    </c:forEach>
                                </dl>
                            </c:if>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- 内容主体区域 -->
    <sitemesh:write property='body'/>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
    </div>
</div>
<script src="${ctx}/assets/layui/layui.all.js" type="text/javascript"></script>
<script>
    var $ = layui.$, jQuery = layui.$;
    var ctx = '${ctx}/';
    var cur_menu = '';
</script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.custom.js"></script>
<script src="${ctx}/assets/js/global.js" type="text/javascript"></script>
<script>
    $(function () {
        //默认设置
        $.validator.setDefaults({
            errorPlacement: function (error, element) {//error为错误提示对象，element为出错的组件对象
                /*   if (element.is(":radio")){
                 error.css("display","block").appendTo(element.parent().parent()) ;
                 }else{*/
                console.log(error.html())
                layer.tips(error.html(), element, {
                    tipsMore: true,
                    tips: [2, '#ffa51a']
                    //tips:['#E84C3D']
                });
                //element.parent().after($('<div class="layui-form-mid layui-word-aux"></div>').append(error)) ;//默认是加在 输入框的后面。这个else必须写。不然其他非radio的组件 就无法显示错误信息了。
                //element.parent().after($('<div class="layui-form-mid layui-word-aux"></div>').append(error)) ;//默认是加在 输入框的后面。这个else必须写。不然其他非radio的组件 就无法显示错误信息了。
                /*}*/
            }
        });
    })
</script>
<sitemesh:write property='myJs'/>
<script>
    //左侧菜单处理
    if(sys.util.isEmpty(cur_menu)){
        cur_menu = window.location.href;
    }
    $('.layui-side .layui-nav-child dd a').each(function(index) {
        var path = $(this).attr("href");
        if (cur_menu.indexOf(path) != -1 || path.indexOf(cur_menu) != -1) {
            $(this).parent().addClass("layui-this");
            $(this).parent().parent().parent().addClass("layui-nav-itemed");
            $(this).parent().parent().prev("a").children("i").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        }
    });
    $('.layui-nav-item').click(function() {
        if($(this).hasClass("layui-nav-itemed")){
            $(this).children("a").children("i").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        }else{
            $(this).children("a").children("i").removeClass("fa-chevron-up").addClass("fa-chevron-down");
        }
    });
</script>
</body>
</html>


