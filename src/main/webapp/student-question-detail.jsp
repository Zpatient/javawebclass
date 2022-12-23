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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jodit/app.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jodit/build/jodit.min.css" />
    <script src="${pageContext.request.contextPath}/jodit/build/jodit.js"></script>
    <script type="text/javascript">
        selects = new Array();
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
                type: 'get',
                success:function (){
                    var url = "http://localhost:8080/student/getquestion";
                    $(location).attr('href',url);
                }
            })
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
        function addRemark(){
            $.ajax({
                url: '${pageContext.request.contextPath}/remark/insert', 	// 请求的地址，即要给那里发送请求
                data: $('#formId').serialize(),
                contentType:'application/x-www-form-urlencoded',
                type: 'post',
                success:function (){
                    $('#messageDisplay').load("http://localhost:8080/remark/getdetail?id=${ask.questionId} #messageDisplay");
                    $("#askform").load("http://localhost:8080/remark/getdetail?id=${ask.questionId} #formId")
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
                            <img src="${pageContext.request.contextPath}/assets/images/logo-text.png" alt="homepage" class="dark-logo" />
                         </span>
                    </a>
                </div>
                <div class = "pull-right" style="width: 100px;child-align: auto;margin-right: 40px;">
                    <div class="btn-group" style="display: inline-block;">
                        <p class="btn dropdown-toggle font-weight-bold" data-toggle="dropdown"><img src="${pageContext.request.contextPath}/assets/images/user.png"/>${user.name}</p>
                        <ul class="dropdown-menu" style="width: 100px;margin-left:12px;child-align: auto;min-width: 100px;">
                            <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/changepassword.jsp">修改密码</a></li>
                            <li style="width: 100px;text-align: center;margin-right: 0;"><a href="#">退出登录</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
            <div class="container-fluid">
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <span class="h4 col-md-12" style="text-align:center;padding-top: 30px">${ask.question}</span>
                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="margin-top:0;" id="asktable">
                                    <table class="table vm no-th-brd pro-of-month table-hover table-bordered">
                                        <thead>
                                        <tr>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">评论人</td>
                                            <td class = "h7 col-md-2 font-weight-bold" style="text-align: center">评论时间</td>
                                            <td class = "h7 col-md-5 font-weight-bold" style="text-align: center">评论内容</td>
                                            <td class = "h7 col-md-3 font-weight-bold" style="text-align: center">操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="message" items="${messages}">
                                            <tr>
                                                <td class = "col-md-2" style="text-align: center"><h6>${message.ownername}</h6></td>
                                                <td class = "col-md-2" style="text-align: center">${message.time}</td>
                                                <td class = "col-md-5" style="text-align: center">${message.content}</td>
                                                <td class = "col-md-3" style="text-align: center">
                                                    <c:if test="${ask.show}">
                                                        <button type="button" class="btn btn-danger btn-sm"  onclick="deleteQues(${ask.questionId})">删除</button>
                                                    </c:if>
                                                    <button type="button" class="btn btn-info btn-sm" onclick="editMessage(${ask.questionId})">编辑</button>
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
                    <form action="${pageContext.request.contextPath}/remark/insert" method="post" id = 'formId'>
                        <style>
                            #box {
                                padding: 0;
                                padding-left: 25%;
                                margin: 0;
                                position: relative;
                                height: 500px;
                            }
                            @media (max-width: 480px) {
                                #box {
                                    padding: 0;
                                }
                            }
                        </style>
                        <div id="box">
                            <textarea id="editor" name = "content">
                                &lt;img src="https://xdsoft.net/jodit/files/artio.jpg"/&gt;
                            </textarea>
                            <input type="button" class = "btn btn-info col-md-push-6" value="回复" style="margin-right: 0" onclick="addRemark()"/>
                        </div>
                        <input type="hidden" name = 'questionid' value='${ask.questionId}'/>
                        <input type="hidden" name = 'ownerid' value='${sessionScope.user.id}'/>
                        <input type="hidden" name = 'targetid' value='${ask.teacherId}'/>

                        <script>
                            const editor = Jodit.make('#editor' ,{
                                uploader: {
                                    url: 'http://localhost:8080/file'
                                },
                                filebrowser: {
                                    ajax: {
                                        url: 'https://xdsoft.net/jodit/finder/'
                                    }
                                }
                            });
                        </script>
                    </form>
                </div>
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