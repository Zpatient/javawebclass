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
    <title>Teacher</title>
    <link href="${pageContext.request.contextPath}/assets/node_modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/morrisjs/morris.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/node_modules/c3-master/c3.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/pages/dashboard1.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/colors/default.css" id="theme" rel="stylesheet">
    <script type="text/javascript">
        function getDetail(questionId){
            var url = "http://localhost:8080/remark/getdetail?id="+questionId;
            $(location).attr('href',url);
        }
        function deleteQues(questionId){
            $.ajax({
                url: '${pageContext.request.contextPath}/question/remove?id='+questionId,
                type:'get',
                success:function (){
                    $('#asktable').load("http://localhost:8080/course/getdetail?id=${courseView.id} #asktable");
                }
            })
        }
       function notQues(obj,studentId,courseId){
           var originStatus = $(obj).text();
           if(originStatus=="禁止提问"){
               var ask = 0;
               updateSelectionAsk(studentId,courseId,ask);
               $(obj).text("允许提问");
           }
           else{
               var ask = 1;
               updateSelectionAsk(studentId,courseId,ask);
               $(obj).text("禁止提问");
           }
       }
        function notLook(obj,studentId,courseId){
            var originStatus =$(obj).text();
            if(originStatus=="禁止浏览"){
                var see = 0;
                updateSelectionSee(studentId,courseId,see);
                $(obj).text("允许浏览");
            }
            else{
                var see = 1;
                updateSelectionSee(studentId,courseId,see);
                $(obj).text("禁止浏览");
            }
        }
       function updateSelectionAsk(studentId,courseId,ask){
           $.ajax({
               url: '${pageContext.request.contextPath}/teacher/updateSelectionAsk?studentId='+studentId+'&courseId='+courseId+'&ask='+ask, 	// 请求的地址，即要给那里发送请求
               type: 'get'
           })
       }
       function updateSelectionSee(studentId,courseId,see){
           $.ajax({
               url: '${pageContext.request.contextPath}/teacher/updateSelectionSee?studentId='+studentId+'&courseId='+courseId+'&see='+see, 	// 请求的地址，即要给那里发送请求
               type: 'get'
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
                            <img src="${pageContext.request.contextPath}/assets/images/teacher.png" alt="homepage" class="dark-logo" />
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
                <br/><br/>
                <h1 class="font-weight-bold"  style="text-align: center">课程信息</h1><br/>
                <h3 class="font-weight-bold" style="text-align: center">课程名：${courseView.name} </h3><br/>
                <h4 class="font-weight-bold" style="text-align: center">教师：${courseView.teacher} </h4><br/>
                <h6 style="text-align: center">简介:${courseView.content} </h6>
            </div>
        </aside>
<%--模态框--%>
        <div class="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <span class="h4 center-block col-md-2" style="padding-top: 5px">提问列表</span>
                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;" id="asktable">
                                    <table class="table vm no-th-brd pro-of-month table-hover table-bordered">
                                        <thead>
                                        <tr>
                                            <td class = "h7 col-md-5 font-weight-bold" style="text-align: center">提问内容</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">提问时间</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">提问人</td>
                                            <td class = "h7 col-md-3 font-weight-bold" style="text-align: center">操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="ask" items="${asks}">
                                            <tr>
                                                <td class = "col-md-5" style="text-align: center"><h6>${ask.question}</h6></td>
                                                <td class = "col-md-2" style="text-align: center">${ask.askTime}</td>
                                                <td class = "col-md-2" style="text-align: center">${ask.student}</td>
                                                <td class = "col-md-3" style="text-align: center">
                                                    <c:if test="${ask.show||sessionScope.type=='teacher'}">
                                                        <button type="button" class="btn btn-danger btn-sm"  onclick="deleteQues(${ask.questionId})">删除</button>
                                                    </c:if>
                                                    <button type="button" class="btn btn-info btn-sm" onclick="getDetail(${ask.questionId})">查看</button>
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
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <span class="h4 center-block col-md-2" style="padding-top: 5px">学生列表</span>
                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;">
                                    <table class="table vm no-th-brd pro-of-month table-hover table-bordered">
                                        <thead>
                                        <tr>
                                            <td class = "h7 col-md-4 font-weight-bold" style="text-align: center">学号</td>
                                            <td class = "h7 col-md-4 font-weight-bold" style="text-align: center">姓名</td>
                                            <td class = "h7 col-md-4 font-weight-bold" style="text-align: center">权限设置</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="student" items="${students}">
                                            <tr>
                                                <td class = "col-md-4" style="text-align: center"><h6>${student.id}</h6></td>
                                                <td class = "col-md-4" style="text-align: center">${student.name}</td>
                                                <td class = "col-md-4" style="text-align: center">
                                                <button type="button" class="btn btn-info btn-sm" onclick="notQues(this,${student.id},${courseView.id})">禁止提问</button>
                                                <button type="button" class="btn btn-info btn-sm" onclick="notLook(this,${student.id},${courseView.id})">禁止浏览</button>
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
               <c:if test="${sessionScope.type == student}">
                   <div class="row">
                       <!-- Column -->
                       <div class="col-md-12">
                           <div class="card">
                               <div class="card-body">
                                   <form id = "askform">
                                       <div class="input-group">
                                           <div class="radio col-md-1">
                                               <label style="font-weight: bold;padding-top: 7px;padding-left: 14px"><input type="radio" name="see" value="1" style="left:15px;top:12px;opacity: 1;"/>&nbsp;&nbsp;公&nbsp;&nbsp;开</label>
                                           </div>
                                           <input class="form-control col-md-10" type="text" name="publish" placeholder="请输入提问的内容" id="askcontent"/>
                                           <div class="input-group-btn">
                                               <button class="btn btn-info col-md-12" type="button" onclick="publishQues()" >提问</button>
                                           </div>
                                       </div>
                                   </form>
                               </div>
                           </div>
                       </div>
                   </div>
               </c:if>

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