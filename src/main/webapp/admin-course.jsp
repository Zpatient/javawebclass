<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <title>Administrator</title>
    <link href="${pageContext.request.contextPath}/assets/node_modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/morrisjs/morris.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/c3-master/c3.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/pages/dashboard1.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/colors/default.css" id="theme" rel="stylesheet">
    <script type="text/javascript">
        function showDialog(obj){
            $('#update').modal('show');
            var tds= $(obj).parent().parent().find('td');
            $("#uid").val($(tds.eq(0)).text());
            $("#uname").val($(tds.eq(1)).text());
            $("#uteacherid").val($(tds.eq(2)).text());
            $("#ucontent").val($(tds.eq(3)).text());
            $("#uscore").val($(tds.eq(4)).text());
        }
        function update(){
            $("#updateform").submit();
        }
        function insert(){
            $("#insertform").submit();
        }
    </script>
</head>

<body class="fix-header fix-sidebar card-no-border">
    <div class="preloader">
        <div class="loader">
            <div class="loader__figure"></div>
            <p class="loader__label">在线答疑系统</p>
        </div>
    </div>
<%--标题--%>
    <div id="main-wrapper">
        <header class="topbar">
            <nav class="navbar top-navbar  navbar-light">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/course/getall">
                        <b>
                            <img src="${pageContext.request.contextPath}/assets/images/logo-icon.png" alt="homepage" class="dark-logo" />
                        </b>
                         <span>
                            <img src="${pageContext.request.contextPath}/assets/images/admin.png" alt="homepage" class="dark-logo" />
                         </span>
                    </a>
                </div>
                <div class = "pull-right" style="width: 100px;child-align: auto;margin-right: 40px;">
                    <div class="btn-group" style="display: inline-block;">
                        <p class="btn dropdown-toggle font-weight-bold" data-toggle="dropdown"><img src="${pageContext.request.contextPath}/assets/images/user.png"/>用户名</p>
                        <ul class="dropdown-menu" style="width: 100px;margin-left:12px;child-align: auto;min-width: 100px;">
                            <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/changepassword.jsp">修改密码</a></li>
                            <li style="width: 100px;text-align: center;margin-right: 0;"><a href="#">退出登录</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
<%--导航栏--%>
        <aside class="left-sidebar">
            <div class="scroll-sidebar">
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/course/getall" aria-expanded="false">
                            <i class="fa fa-tachometer"></i><span class="h5 hide-menu font-weight-bold">课程信息管理</span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/teacher/getall" aria-expanded="false">
                            <i class="fa fa-user-circle-o"></i><span class="h5 hide-menu font-weight-bold">教师信息管理</span></a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
<%--模态框--%>
        <div class="page-wrapper">
            <div class="container">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">添加课程</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
<%--此处改url--%>
                                <form id = "insertform" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/course/insert" method="post">
                                    <div class="form-group center-block">
                                        <label for="id" class="col-md-3 control-label font-weight-bold text-right">课程代码</label>
                                        <div class="col-md-8" style="display: inline-block">
                                            <input id ="id" name="id" type="text" class="form-control" placeholder="请输入课程代码"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="name" class="col-md-3 control-label font-weight-bold text-right">课程名称</label>
                                        <div class="col-md-8" style="display: inline-block">
                                            <input id ="name" name="name" type="text" class="form-control" placeholder="请输入课程名称"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="teacherid" class="col-md-3 control-label font-weight-bold text-right">教师id</label>
                                        <div class="col-md-8" style="display: inline-block">
                                            <input id ="teacherid" name="teacherid" type="text" class="form-control " placeholder="请输入授课教师id"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="content" class="col-md-3 control-label font-weight-bold text-right">课程简介</label>
                                        <div class="col-md-8 " style="display: inline-block">
                                            <input id ="content" name="content" class="form-control" placeholder="请输入课程简介"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="score" class="col-md-3 control-label font-weight-bold text-right">课程评分</label>
                                        <div class="col-md-8 " style="display: inline-block">
                                            <input id ="score" name="score" class="form-control" placeholder="请输入课程评分"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-info" onclick="insert()">确认添加</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">编辑课程</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
<%--此处改url--%>
                                <form id = "updateform" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/course/update" method="post">
                                    <div class="form-group center-block">
                                        <label for="uid" class="col-md-3 control-label font-weight-bold text-right">课程代码</label>
                                        <div class="col-md-8" style="display: inline-block">
                                            <input id ="uid" name="id" type="text" class="form-control" placeholder="请输入课程代码" disabled="disabled"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="uname" class="col-md-3 control-label font-weight-bold text-right">课程名称</label>
                                        <div class="col-md-8" style="display: inline-block">
                                            <input id ="uname" name="name" type="text" class="form-control" placeholder="请输入课程名称"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="uteacherid" class="col-md-3 control-label font-weight-bold text-right">教师id</label>
                                        <div class="col-md-8" style="display: inline-block">
                                            <input id ="uteacherid" name="teacherid" type="text" class="form-control " placeholder="请输入授课教师id"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="ucontent" class="col-md-3 control-label font-weight-bold text-right">课程简介</label>
                                        <div class="col-md-8 " style="display: inline-block">
                                            <input id ="ucontent" name="content" class="form-control" placeholder="请输入课程简介"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="uscore" class="col-md-3 control-label font-weight-bold text-right">课程评分</label>
                                        <div class="col-md-8 " style="display: inline-block">
                                            <input id ="uscore" name="score" class="form-control" placeholder="请输入课程评分"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-info" onclick="update()">提交更改</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                 <div class="row">
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <span class="h4 center-block col-md-2" style="padding-top: 5px">课程列表</span>
                                    <span class="col-md-7"></span>
                                    <span class="col-md-3 text-center" >
                                        <p class="btn btn-success" data-toggle="modal" data-target="#insert"
                                           style="margin-right:20px;margin-bottom: 15px;width: 100px">添加课程</p>
                                    </span>
                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;">
                                    <table class="table vm no-th-brd pro-of-month table-hover table-bordered">
                                        <thead>
                                            <tr>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">课程代码</td>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">课程名称</td>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">授课教师</td>
                                                <td class = "h7 col-md-5 font-weight-bold" style="text-align: center">课程简介</td>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">课程评分</td>
                                                <td class = "h7 col-md-3 font-weight-bold" style="text-align: center">操作</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="course" items="${courses}">
                                            <tr>
                                                <td class = "col-md-1" style="text-align: center"><h6>${course.id}</h6></td>
                                                <td class = "col-md-1" style="text-align: center"><h6>${course.name}</h6></td>
                                                <td class = "col-md-1" style="text-align: center">${course.teacherid}</td>
                                                <td class = "col-md-5" style="text-align: center">${course.content}</td>
                                                <td class = "col-md-1" style="text-align: center">${course.score}</td>
                                                <td class = "col-md-3" style="text-align: center">
                                                    <button type="button" class="btn btn-info btn-sm" onclick="showDialog(this)">编辑</button>
                                                    <button type="button" class="btn btn-danger btn-sm">删除</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/node_modules/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/node_modules/bootstrap/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/node_modules/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/perfect-scrollbar.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/waves.js"></script>
    <script src="${pageContext.request.contextPath}/js/sidebarmenu.js"></script>
    <script src="${pageContext.request.contextPath}/js/custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/node_modules/raphael/raphael-min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/node_modules/morrisjs/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/node_modules/d3/d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/node_modules/c3-master/c3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/dashboard1.js"></script>
</body>

</html>