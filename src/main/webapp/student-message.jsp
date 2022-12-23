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
    <title>Student</title>
    <link href="${pageContext.request.contextPath}/assets/node_modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/morrisjs/morris.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/c3-master/c3.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/pages/dashboard1.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/colors/default.css" id="theme" rel="stylesheet">
    <script type="text/javascript">
        selects = new Array();
        function isSelect(){
            var selected = ${sessionScope.user.selected};
            if(selected != 1){
                $('#select').modal('show');
            }
            else {
                $('#select').modal('hide');
            }
        }
        function select(button,id){
            selects.push(id);
            $(button).parent().html('<p class="btn btn-info btn-xs">已选</p>');
            $('#tabele').load("http://localhost:8080/student/getquestion #table");
        }
        function complete(){

            console.log(selects);
            $.ajax({
                url: '${pageContext.request.contextPath}/student/select', 	// 请求的地址，即要给那里发送请求
                data: {select:selects},
                contentType:'application/x-www-form-urlencoded',
                type: 'post',
                success:function (){
                    var url = "http://localhost:8080/student/getquestion";
                    $(location).attr('href',url);
                }
            })
        }
        setInterval(function (){isSelect();},1000);
        function getDetail(questionId,remarkId){
            var url = "http://localhost:8080/remark/getdetail?id="+questionId;
            $(location).attr('href',url);
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
                    <a class="navbar-brand">
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
                        <p class="btn dropdown-toggle font-weight-bold" data-toggle="dropdown"><img src="${pageContext.request.contextPath}/assets/images/user.png"/>${user.name}</p>
                        <ul class="dropdown-menu" style="width: 100px;margin-left:12px;child-align: auto;min-width: 100px;">
                            <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/changepassword.jsp">修改密码</a></li>
                             <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/logout">退出登录</a></li>
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
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/student/getquestion" aria-expanded="false">
                            <i class="fa active fa-tachometer"></i><span class="h5 hide-menu font-weight-bold">我的提问<span style="color: red;font-weight: normal;font-size: 14px;">
                            <c:if test="${count>0}">
                                (${count}条未读)
                            </c:if>
                            </span></span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" href="${pageContext.request.contextPath}/student/getcourse" aria-expanded="false">
                            <i class="fa fa-user-circle-o"></i><span class="h5 hide-menu font-weight-bold">我的课程</span></a>
                        </li>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
<%--模态框--%>
        <div class="page-wrapper">
            <div class="container">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="select" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">选课</h4>
                            </div>
                            <div class="modal-body">
<%--此处改url--%>
                                <form class="form" action="${pageContext.request.contextPath}/student/select" role="form" method="post" id="selectform">
                                    <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;">
                                        <table class="table vm no-th-brd pro-of-month table-hover table-bordered" id="table">
                                            <thead>
                                            <tr>
                                                <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">课程名称</td>
                                                <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">授课教师</td>
                                                <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">课程评分</td>
                                                <td class = "h7 col-md-6 font-weight-bold" style="text-align: center">选课操作</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="course" items="${courses}">
                                                <tr>
                                                    <td class = "col-md-2" style="text-align: center"><h6>${course.name}</h6></td>
                                                    <td class = "col-md-2" style="text-align: center">${course.teacherid}</td>
                                                    <td class = "col-md-2" style="text-align: center">${course.score}</td>
                                                    <td class = "col-md-6" style="text-align: center">
                                                        <button type="button" class="btn btn-danger btn-xs" onclick="select(this,${course.id})">待选</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-info" onclick="complete()">完成选课</button>
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
                                    <span class="h4 center-block col-md-2" style="padding-top: 5px">待读列表</span>
                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;">
                                    <table class="table vm no-th-brd pro-of-month table-hover table-bordered">
                                        <thead>
                                        <tr>
                                            <td class = "h7 col-md-5 font-weight-bold" style="text-align: center">回复内容</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">归属课程</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">教师姓名</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">回复时间</td>
                                            <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">查看详情</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="message" items="${messages}">
                                            <c:if test="${(message.studentShow==false&&message.student==sessionScope.user.name)==false}">
                                                <tr>
                                                    <td class = "col-md-5" style="text-align: center"><h6>${message.content}</h6></td>
                                                    <td class = "col-md-2" style="text-align: center">${message.course}</td>
                                                    <td class = "col-md-2" style="text-align: center">${message.teacher}</td>
                                                    <td class = "col-md-2" style="text-align: center">${message.time}</td>
                                                    <td class = "col-md-1" style="text-align: center">
                                                        <button type="button" class="btn btn-info btn-sm" onclick="getDetail(${message.questionId})">查看</button>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <span class="h4 center-block col-md-2" style="padding-top: 5px">提问列表</span>
                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;">
                                    <table class="table vm no-th-brd pro-of-month table-hover table-bordered">
                                        <thead>
                                        <tr>
                                            <td class = "h7 col-md-5 font-weight-bold" style="text-align: center">提问内容</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">归属课程</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">教师姓名</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">提问人</td>
                                            <td class = "h7 col-md-1 font-weight-bold" style="text-align: center">查看详情</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="ask" items="${asks}">
                                            <tr>
                                                <td class = "col-md-5" style="text-align: center"><h6>${ask.question}</h6></td>
                                                <td class = "col-md-2" style="text-align: center">${ask.course}</td>
                                                <td class = "col-md-2" style="text-align: center">${ask.teacher}</td>
                                                <td class = "col-md-2" style="text-align: center">${ask.student}</td>
                                                <td class = "col-md-1" style="text-align: center">
                                                    <c:if test="${(ask.studentShow==false&&sessionScope.user.id==ask.studentId)==false}">
                                                        <button type="button" class="btn btn-info btn-sm" onclick="getDetail(${ask.questionId})">查看</button>
                                                    </c:if>
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