<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css' />" /> 
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" /> 
        <script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js' />"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${param.status == 'logout'}">
            <div style="position: absolute; width: 30%; margin-left: 20%; top: 50px;" class="alert alert-danger">Uživatel úspěšně odhlášen!</div>
        </c:if>
        <div id="loginbox" style="margin-top:150px;" class="mainbox col-md-12">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title">Přihlášení</div>
                </div>     
                <div style="padding-top:30px" class="panel-body" >
                    <form action="<c:url value='/prihlasit' />" method="POST" id="loginform" class="form-horizontal" role="form">

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username">                                        
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="login-password" type="password" class="form-control" name="password" placeholder="heslo">
                        </div>

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-3 controls">
                                <button id="btn-login" type="submit" href="#" class="btn btn-success">Přihlásit</button>
                            </div>
                            <div class="col-sm-9">
                                <c:if test="${param.status == 'logerror'}">
                                    <div style="margin-bottom: 0; padding-top: 5px; height: 35px;" class="alert alert-danger">Chybně vyplněné přihlašovací údaje!</div>
                                </c:if>
                            </div>
                        </div>

                    </form>     
                </div>                     
            </div>  
        </div>
        <div id="signupbox" style="margin-top: 150px; width: 50%" class="mainbox col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">Registrace</div>
                </div>  
                <div style="padding-top:30px" class="panel-body" >
                    <form action="<c:url value='/registrace' />" method="POST" id="signupform" class="form-horizontal" role="form">
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username">                                        
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="login-password" type="password" class="form-control" name="password" placeholder="heslo">
                        </div>

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-3 controls">
                                <button id="btn-login" type="submit" href="#" class="btn btn-info">Registrovat</button>
                            </div>
                            <div class="col-sm-9">
                                <c:if test="${param.status == 'userreg'}">
                                    <div style="margin-bottom: 0; padding-top: 5px; height: 35px;" class="alert alert-success">Registrace proběhla úspěšně!</div>
                                </c:if>
                                <c:if test="${param.status == 'userregerror'}">
                                    <div style="margin-bottom: 0; padding-top: 5px; height: 35px;" class="alert alert-danger">Je nutné vyplnit potřebné údaje!</div>
                                </c:if>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
    </body>
</html>
