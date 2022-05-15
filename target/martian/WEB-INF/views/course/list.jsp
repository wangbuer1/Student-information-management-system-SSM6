<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<%@ include file="/include/base_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>课程管理</title>
</head>
<body>
<div class="layui-body">
    <div class="breadcrumb">
        <span class="layui-breadcrumb">
              <a>课程管理</a>
              <a><cite>课程列表</cite></a>
        </span>
        <span class="right action">
             <button class="layui-btn layui-btn-normal" type="button" onclick="goAdd()"><i
                     class="layui-icon">&#xe608;</i>新增课程</button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <div class="search">
            <blockquote class="layui-elem-quote">
                <form id="searchForm" class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程名称：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" name="courseName" placeholder="请输入课程名称">
                        </div>
                    </div>
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
            <table class="layui-table" id="courseTable">
                <colgroup>
                    <col width="80">
                    <col>
                    <col>
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
                        <th>课程名称</th>
                        <th>年份</th>
                        <th>学期</th>
                        <th>院系</th>
                        <th>班级</th>
                        <th>教师</th>
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
<!-- 课程弹出框-->
<div class="popBox layui-hide">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>年份：</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id" autocomplete="off" class="layui-input">
                    <input type="text" name="year" class="layui-input" id="year" placeholder="请选择年份" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>学期：</label>
                <div class="layui-input-inline">
                    <select name="term">
                        <option value="">请选择</option>
                        <c:forEach items="${termEnum}" var="t">
                            <option value="${t.code}">${t.label}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>院系：</label>
                <div class="layui-input-inline">
                    <select name="departId" lay-filter="searchByDepartId">
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
                <label class="layui-form-label width120"><span class="tip">*</span>班级：</label>
                <div class="layui-input-inline">
                    <select name="clazzId">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>教师：</label>
                <div class="layui-input-inline">
                    <select name="teacherId">
                        <option value="">请选择</option>
                        <c:forEach items="${teacherList}" var="t">
                            <option value="${t.id}">${t.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>课程名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">课程描述：</label>
                <div class="layui-input-inline">
                    <textarea name="courseDesc" class="layui-textarea width250"></textarea>
                </div>
            </div>
        </div>
    </form>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/course/course.js"></script>
</myJs>
</body>
</html>