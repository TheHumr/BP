
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css' />" /> 
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" /> 
        <script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/js/jquery-2.1.0.min.js' />"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div style="text-align: right; margin-top: 10px;">
            Přihlášen jako: <b>${user.login}</b>
            <a class="btn btn-danger" href="/BKApp/odhlasit">Odhlásit</a>
        </div>
        <div style="padding-top:30px" class="panel-body" >
            <div class="col-sm-6">
                <form action="<c:url value='/vytvoritPrispevek' />" method="GET" id="signupform" class="form-horizontal" role="form">
                    <div class="input-group">
                        <span class="input-group-addon" title="Vytořit nový příspěvek"><i class="glyphicon glyphicon-envelope"></i></span>
                        <input id="login-username" type="text" class="form-control" name="text" value="" placeholder="Vytvořit nový příspěvek">
                    </div>
                    <div  style="margin-top: 5px" class="form-group">
                        <div class="col-sm-3 controls">
                            <button id="btn-login" type="submit"  class="btn btn-success">Vytvořit</button>
                        </div>
                        <c:if test="${param.status == 'prispevekError'}">
                            <div style="margin-bottom: 0; padding-top: 5px; height: 35px; text-align: center;" class="alert alert-danger col-sm-8">Je nutné vyplnit text příspěvku!</div>
                        </c:if>
                    </div>
                </form>
            </div>
            <div class="col-sm-6">
                <form action="<c:url value='/home' />" method="GET" id="loginform" class="form-horizontal" role="form">

                    <div  class="input-group">
                        <span class="input-group-addon" title="Hledat příspěvky uživatele"><i class="glyphicon glyphicon-search"></i></span>
                        <input id="login-username" type="text" class="form-control" name="filterUsername" value="" placeholder="Vyhledat příspěvky uživatele">                                        
                    </div>

                    <div style="margin-top:5px" class="form-group">
                        <div class="col-sm-3 controls">
                            <button id="btn-login" type="submit" href="#" class="btn btn-success">Hledat</button>
                        </div>
                        <c:if test="${param.status == 'filterError'}">
                            <div style="margin-bottom: 0; padding-top: 5px; height: 35px; text-align: center;" class="alert alert-danger col-sm-8">Je nutné vyplnit jméno uživatele!</div>
                        </c:if>
                        <c:if test="${param.status == 'filterError2'}">
                            <div style="margin-bottom: 0; padding-top: 5px; height: auto; text-align: center; " class="alert alert-danger col-sm-8"><span style="overflow: auto">Hledaný uživatel "${param.username}" nenalezen!</span></div>
                        </c:if>

                    </div>

                </form>        
            </div>
        </div>
    </div>
    <h3>Příspěvky<c:if test="${not empty filterUsername}"> uživatele "${filterUsername}"</c:if></h1>

    <c:forEach items="${prispevky}" var="prispevek">

        <div style="background: #eeeeee; box-shadow: #262626 0px 0px 1px 1px; padding: 5px" class="alert alert-info">
            <b style="font-size: 20px">${prispevek.uzivatel.login}</b> </br> <p style="color: black;">${fn:escapeXml(prispevek.text)}</p>
        </div>

    </c:forEach>
</body>
</html>
