<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" type="image/ico" href="${path}/resources/img/favicon.ico" />
    <title>演示系统</title>
    
    <link href="${path}/resources/css/checkbox.css" type="text/css" media="screen" rel="stylesheet" />
    <link href="${path}/resources/login-01/css/styles.css" type="text/css" media="screen" rel="stylesheet" />
    
    <script type="text/javascript" src="${path}/resources/js/jquery/jquery-1.8.0.min.js"></script>
    <style type="text/css">
    img, div { behavior: url(iepngfix.htc) }
    </style>
</head>

<body id="login">
    <div id="wrappertop"></div>
    <div id="wrapper">
        <div id="content">
            <div id="header">
                <h1><a href=""><img src="${path}/resources/login-01/images/logo.png" alt="FreelanceSuite"></a></h1>
            </div>
            <div id="darkbanner" class="banner320">
                <h2>登录系统</h2>
            </div>
            <div id="darkbannerwrap">
            </div>
            <form name="form1" method="post" action="">
            <fieldset class="form">
                <c:choose>
                    <c:when test="${isError}">
                <div style="float:left;margin-bottom:12px;">
                    <label>提示：</label>
                    <div style="float:left;margin-top:8px;color:red;font-size:14px;">${message_login}</div>
                </div>
                    </c:when>
                </c:choose>
                <div style="float:left;">
                    <label for="username">用&nbsp;&nbsp;户：</label>
                    <input name="username" id="username" type="text" value="" />
                </div>
                <div style="float:left;">
                    <label for="password">密&nbsp;&nbsp;码：</label>
                    <input name="password" id="password" type="password" />
                </div>
                <div style="float:left;">
                    <label for="vicode">验证码：</label>
                    <div style="float:left;margin-top:4px;"><img src="${path}/jcaptcha.jpg" /></div>
                    <div style="float:left;">
                        <input style="width:80px;margin-top:6px;margin-bottom:11px;" name="jcaptchaCode" id="jcaptchaCode" type="text" value="" />
                    </div>
                    <div style="float:left;margin:8px 0px 0px 5px;">
                        <img id="vicode-right" style="display:none;" src="${path}/resources/img/check_24.png" />
                        <img id="vicode-error" style="display:none;" src="${path}/resources/img/close_delete_24.png" />
                    </div>
                </div>
                <!-- <div style="float:left;width:100%;">
                    <section style="float:left;width:124px;margin-left:96px;">
                        <div class="checkboxFive">
                            <input style="width:0px;" type="checkbox" value="0" id="checkboxFiveInput" name="rememberMe" />
                            <label style="width:26px;padding-top:2px;margin-left:78px;" for="checkboxFiveInput"></label>
                        </div>
                    </section>
                    <div style="float:left;font-size:16px;margin-top:3px;">记住我</div>
                </div> -->
                <button type="submit" class="positive" name="Submit"><img src="${path}/resources/login-01/images/key.png" alt="Announcement"/>确定</button>
                <ul id="forgottenpassword" style="float:left;margin-top:20px;">
                    <li class="boldtext">|</li>
                    <li><a href="#">忘记密码?</a></li>
                </ul>
            </fieldset>
            </form>
        </div>
    </div>

    <div id="wrapperbottom_branding">
        <div id="wrapperbottom_branding_text"><a href="#" style='text-decoration:none'>Powered By company</a>.</div>
    </div>

<script type="text/javascript">
    // 页面加载
    $(document).ready(function() {
        console.dir('wwwwwwww');
        
        $('#jcaptchaCode').bind('input propertychange', function() {
            var vicodeVal = $('#jcaptchaCode').val();
            var errorObj = $('#vicode-error');
            var rightObj = $('#vicode-right');
            $.get("${path}/jcaptchaCheckVicode?vicode=" + vicodeVal, function(result) {
                if (eval(result)) {
                    errorObj.hide();
                    rightObj.show();
                } else {
                    rightObj.hide();
                    errorObj.show();
                }
            });
        });
    });
</script>
    
</body>
</html>