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
    <title>Question</title>
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
        function deleteMessage(remarkId){
            $.ajax({
                url: '${pageContext.request.contextPath}/remark/remove?id='+remarkId,
                type:'get',
                success:function (){
                    $('#asktable').load("http://localhost:8080/remark/getdetail?id=${ask.questionId} #asktable");
                }
            })
        }
        function editMessage(obj,remarkId){
            var tds= $(obj).parent().parent().find('td');
            content =  $(tds.eq(2)).text();
            remarkid = remarkId;
            $(tds.eq(2)).html("<input class='form-control' onblur='onBlur(this,content,remarkid)' type='text' value='"+content+"' />")
        }
        function onBlur(obj,origincontent,remarkId){
            var content = $(obj).val();
            $(obj).parent().html(content);
            if(content!=origincontent){
                updateMessage(content,remarkId);
            }
        }
        function updateMessage(content,remarkId){
            $.ajax({
                url: '${pageContext.request.contextPath}/remark/update',
                data: {id:remarkId,content:content},
                contentType:'application/x-www-form-urlencoded',
                type:'post',
                success:function (){
                    $('#asktable').load("http://localhost:8080/remark/getdetail?id=${ask.questionId} #asktable");
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
                    $('#asktable').load("http://localhost:8080/remark/getdetail?id=${ask.questionId} #asktable");
                    $
                }
            })
            $('.jodit-wysiwyg').html('');
        }
        function publishRemark(){
            score = $('#remarkValue').val();
            $.ajax({
                url: '${pageContext.request.contextPath}/course/remark?courseName=${ask.course}&score='+score,
                type: 'get',
                success:function (){
                    $("#remarkValue").val("");
                }
            })
        }
    </script>
</head>

<body class="fix-header fix-sidebar card-no-border" id="bodydiv">
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
                             <li style="width: 100px;text-align: center;margin-right: 0;"><a href="${pageContext.request.contextPath}/logout">退出登录</a></li>
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
                                <c:if test="${notNull == true }">
                                    <table class="table vm pro-of-month">
                                        <c:forEach var="message" items="${messages}">
                                            <tr>
                                                <td class = "col-md-1" style="text-align: center;border-width: 0"><h6>${message.ownername}</h6></td>
                                                <td class = "col-md-1" style="text-align: center;border-width: 0" >${message.time}</td>
                                                <td class = "col-md-9" style="text-align: center;border-width: 0">${message.content}</td>
                                                <td class = "col-md-1" style="text-align: center;border-width: 0">
                                                    <c:if test="${message.show||sessionScope.type=='teacher'}">
                                                        <button type="button" class="btn btn-danger btn-sm"  onclick="deleteMessage(${message.remarkid})">删除</button>
                                                        <button type="button" class="btn btn-info btn-sm" onclick="editMessage(this,${message.remarkid})">编辑</button>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                                <c:if test="${notNull == false}">
                                    <table class="table vm no-th-brd pro-of-month">
                                        <thead>
                                        <tr>
                                            <td class = "h7 col-md-12 font-weight-bold" style="text-align: center">暂无回答</td>
                                        </tr>
                                        </thead>
                                    </table>
                                </c:if>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${sessionScope.user.id == ask.studentId||sessionScope.user.id == ask.teacherId}">
                <div class="row col-lg-12" id="inputarea">
                    <form action="${pageContext.request.contextPath}/remark/insert" method="post" id = 'formId'>
                        <style>
                            #box {
                                padding: 0;
                                padding-left: 0;
                                margin: 0 110px;
                                position: relative;
                                height: 500px;
                            }
                            @media (max-width: 100px) {
                                #box {
                                    padding: 0;
                                }
                            }
                        </style>
                        <div id="box">
                        <textarea id="editor" name = "content">
                        </textarea>
                            <input type="button" class = "btn btn-info" value="回复" style="margin-left: 94%" onclick="addRemark()"/>
                        </div>
                        <input type="hidden" name = 'questionid' value='${ask.questionId}'/>
                        <input type="hidden" name = 'ownerid' value='${sessionScope.user.id}'/>
                        <c:if test="${sessionScope.type=='student'}">
                            <input type="hidden" name = 'targetid' value='${ask.teacherId}'/>
                        </c:if>
                        <c:if test="${sessionScope.type=='teacher'}">
                            <input type="hidden" name = 'targetid' value='${ask.studentId}'/>
                        </c:if>

                        <script>
                            const editor = Jodit.make('#editor' ,{
                                uploader: {
                                    url: 'https://xdsoft.net/jodit/finder/?action=fileUpload'
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
            </c:if>
            <c:if test="${sessionScope.user.id == ask.studentId}">
                <div class="row col-md-12" style="padding-left: 38%" >
                    <form action="${pageContext.request.contextPath}/course/remark" method="post" id = 'remark'>
                        <div class="input-group">
                            <input class="form-control col-md-10" type="text" name="publish" placeholder="请输入分值" id="remarkValue"/>
                            <div class="input-group-btn">
                                <button class="btn btn-info col-md-12" type="button" onclick="publishRemark()" >提交评分</button>
                            </div>
                        </div>
                    </form>
                </div>
            </c:if>
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