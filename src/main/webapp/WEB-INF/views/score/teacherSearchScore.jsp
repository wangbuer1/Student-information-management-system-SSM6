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
             <button class="layui-btn layui-btn-normal" type="button" onclick="goAdd()"><i
                     class="layui-icon">&#xe608;</i>新增成绩</button>
        </span>
    </div>

    <!-- 内容主体区域 -->
    <div class="content">
        <div class="search">
            <blockquote class="layui-elem-quote">
                <form id="searchForm" class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">学生姓名：</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" name="studentName" placeholder="请输入学生姓名">
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
                        <label class="layui-form-label">班级：</label>
                        <div class="layui-input-inline">
                            <select name="clazzId">
                                <option value="">请选择</option>
                                <c:forEach items="${clazzList}" var="c">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-top: 10px">
                        <label class="layui-form-label">教授课程：</label>
                        <div class="layui-input-inline">
                            <select name="courseId">
                                <option value="">请选择</option>
                                <c:forEach items="${courseList}" var="c">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button class="layui-btn layui-btn-normal" type="button" onclick="search()" style="margin-top: 10px"><i class="layui-icon">&#xe615;</i>搜索
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
                    <col width="100">
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
<!-- 成绩弹出框-->
<div class="popBox layui-hide">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>班级：</label>
                <div class="layui-input-inline">
                    <select name="clazzId" lay-filter="searchByClazzId">
                        <option value="">请选择</option>
                        <c:forEach items="${clazzList}" var="c">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>课程：</label>
                <div class="layui-input-inline">
                    <select name="courseId" lay-filter="getStudentAndCourse">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">年份：</label>
                <div class="layui-input-inline">
                    <div class="layui-form-mid layui-word-aux" id="yearName"></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120">学期：</label>
                <div class="layui-input-inline">
                    <div class="layui-form-mid layui-word-aux" id="termName"></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120" >院系：</label>
                <div class="layui-input-inline">
                    <div class="layui-form-mid layui-word-aux" id="departName"></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>学生：</label>
                <div class="layui-input-inline">
                    <select name="studentId">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label width120"><span class="tip">*</span>成绩：</label>
                <div class="layui-input-inline">
                    <input type="number" name="score" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 修改成绩弹出框-->
<div class="popBox2 layui-hide">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline" style="margin-top: 20px">
                <label class="layui-form-label width120"><span class="tip">*</span>成绩：</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id" autocomplete="off" class="layui-input">
                    <input type="number" name="score" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </form>
</div>
<myJs>
    <script type="text/javascript" src="${ctx}/assets/js/score/teacherSearchScore.js"></script>
</myJs>
</body>
</html>