<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="test.css">
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p style="color: red;">${error}</p>
          </div>
        </div>
        <form class="login-form" action="LogInController" method="post" name="confirm">
          <input type="text" placeholder="User ID" value="USR1"name="id"/>
          <input type="password" placeholder="Password" value="111"name="password"/>
          <button>login</button>
          <p class="message">Not registered? <a href="add_user.jsp">Create an account</a></p>
        </form>
      </div>
    </div>
</body>

</html>