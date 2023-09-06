<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Rubik:400,700'>
    <link rel="stylesheet" href="css/styleLogin.css">

</head>
<body>

<div class="login-form">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <h1>Login</h1>
        <div class="content">
            <div class="input-field">
                <label>
                    <input type="text" name="username" placeholder="Username">
                </label>
            </div>
            <div class="input-field">
                <label>
                    <input type="password" name="password" placeholder="Password">
                </label>
            </div>
        </div>
        <div class="action">
            <button>Sign in</button>
        </div>
    </form>
</div>
</body>
</html>
