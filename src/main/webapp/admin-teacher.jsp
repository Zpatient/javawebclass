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
            $("#upsw").val($(tds.eq(1)).text());
            $("#uname").val($(tds.eq(2)).text());
            $("#utitle").val($(tds.eq(3)).text());
            $("#ubrief").val($(tds.eq(4)).text());
        }
        function update(){
            $("#updateform").submit();
        }
        function insert(){
            $("#insertform").submit();
        }
        function removeTeacher(obj){
            var tds= $(obj).parent().parent().find('td');
            var tid = tds.eq(0).text();
            $.ajax({
                url: '${pageContext.request.contextPath}/teacher/remove',
                data: {id:tid},
                type: 'get',
                dataType: 'text',
                success: function (data) {
                    if(data=='true'){
                        var tr = $(obj).parent().parent();
                        tr.html("");
                        $('#tabele').load("http://localhost:8080/teacher/getall #table");
                    }
                }
            })
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
    <div id="main-wrapper">
        <header class="topbar">
            <nav class="navbar top-navbar  navbar-light">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/teacher/getall">
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
                        <div class="btn-group" style="display: inline-block;">
                            <p class="btn dropdown-toggle font-weight-bold" data-toggle="dropdown"><img src="${pageContext.request.contextPath}/assets/images/user.png"/>Admin</p>
                            <ul class="dropdown-menu" style="width: 100px;margin-left:12px;child-align: auto;min-width: 100px;">
                                <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/changepassword.jsp">修改密码</a></li>
                                <c:if test="${sessionScope.type=='admin'}">
                                    <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/admin/login">退出登录</a></li>
                                </c:if>
                                <c:if test="${sessionScope.type=='student'}">
                                    <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/student/login">退出登录</a></li>
                                </c:if>
                                <c:if test="${sessionScope.type=='teacher'}">
                                    <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/teacher/login">退出登录</a></li>
                                </c:if>
                            </ul>
                        </div>
                </div>
            </nav>
        </header>
        <aside class="left-sidebar">
            <div class="scroll-sidebar">
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
<%--此处修改url--%>
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/course/getall" aria-expanded="false">
                            <i class="fa fa-tachometer"></i><span class="h5 hide-menu font-weight-bold">课程信息管理</span></a>
                        </li>
<%--此处修改url--%>
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/teacher/getall" aria-expanded="false">
                            <i class="fa active fa-user-circle-o"></i><span class="h5 hide-menu font-weight-bold">教师信息管理</span></a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>

        <div class="page-wrapper">
            <div class="container">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">添加教师</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form id = "insertform" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/teacher/insert" method="post">
                                    <div class="form-group center-block">
                                        <label for="id" class="col-md-2 control-label font-weight-bold text-right">工号</label>
                                        <div class="col-md-9" style="display: inline-block">
                                            <input id ="id" name="id" type="text" class="form-control" placeholder="请输入工号"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="psw" class="col-md-2 control-label font-weight-bold text-right" >密码</label>
                                        <div class="col-md-9" style="display: inline-block">
                                            <input id ="psw" name="password" type="password" class="form-control" placeholder="请输入密码"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="name" class="col-md-2 control-label font-weight-bold text-right">姓名</label>
                                        <div class="col-md-9" style="display: inline-block">
                                            <input id ="name" name="name" type="text" class="form-control " placeholder="请输入姓名"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="title" class="col-md-2 control-label font-weight-bold text-right">职称</label>
                                        <div class="col-md-9 " style="display: inline-block">
                                            <input id ="title" name="title" class="form-control" placeholder="请输入职称"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="brief" class="col-md-2 control-label font-weight-bold text-right">简介</label>
                                        <div class="col-md-9 " style="display: inline-block">
                                            <input id ="brief" name="brief" class="form-control" placeholder="请输入简介"/>
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
                                <h4 class="modal-title">添加教师</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form id = "updateform" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/teacher/update" method="post">
                                    <div class="form-group center-block">
                                        <label for="id" class="col-md-2 control-label font-weight-bold text-right">工号</label>
                                        <div class="col-md-9" style="display: inline-block">
                                            <input id ="uid" name="id" type="text" class="form-control" placeholder="请输入工号" readonly="readonly"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="psw" class="col-md-2 control-label font-weight-bold text-right" >密码</label>
                                        <div class="col-md-9" style="display: inline-block">
                                            <input id ="upsw" name="password" type="password" class="form-control" placeholder="请输入密码"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="name" class="col-md-2 control-label font-weight-bold text-right">姓名</label>
                                        <div class="col-md-9" style="display: inline-block">
                                            <input id ="uname" name="name" type="text" class="form-control " placeholder="请输入姓名"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="title" class="col-md-2 control-label font-weight-bold text-right">职称</label>
                                        <div class="col-md-9 " style="display: inline-block">
                                            <input id ="utitle" name="title" class="form-control" placeholder="请输入职称"/>
                                        </div>
                                    </div>
                                    <div class="form-group center-block">
                                        <label for="brief" class="col-md-2 control-label font-weight-bold text-right">简介</label>
                                        <div class="col-md-9 " style="display: inline-block">
                                            <input id ="ubrief" name="brief" class="form-control" placeholder="请输入简介"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-info" onclick="update()">确认更改</button>
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
                                    <span class="h4 center-block col-md-2" style="padding-top: 5px">教师列表</span>
                                    <span class="col-md-7"></span>
                                    <span class="col-md-3 text-center" >
                                        <p class="btn btn-success" data-toggle="modal" data-target="#insert"
                                           style="margin-right:20px;margin-bottom: 15px;width: 100px">添加教师</p>
                                    </span>
                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;">
                                    <table class="table vm no-th-brd pro-of-month table-hover table-bordered" id="table">
                                        <thead>
                                            <tr>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">工号</td>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">密码</td>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">姓名</td>
                                                <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">职称</td>
                                                <td class = "h7 col-md-5 font-weight-bold" style="text-align: center">简介</td>
                                                <td class = "h7 col-md-3 font-weight-bold" style="text-align: center">操作</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="teacher" items="${teachers}">
                                            <tr>
                                                <td class = "col-md-1" style="text-align: center"><h6>${teacher.id}</h6></td>
                                                <td class = "col-md-1" style="text-align: center"><h6 >${teacher.password}</h6></td>
                                                <td class = "col-md-1" style="text-align: center">${teacher.name}</td>
                                                <td class = "col-md-1" style="text-align: center">${teacher.title}</td>
                                                <td class = "col-md-5" style="text-align: center">${teacher.brief}</td>
                                                <td class = "col-md-3" style="text-align: center">
                                                    <button type="button" class="btn btn-info btn-sm" onclick="showDialog(this)">编辑</button>
                                                    <button type="button" class="btn btn-danger btn-sm" onclick="removeTeacher(this)">删除</button>
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