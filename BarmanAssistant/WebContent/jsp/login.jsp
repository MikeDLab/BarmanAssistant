<!doctype html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>Barman Assistant</title>
<link rel="stylesheet" href="css/styles.css" type="text/css">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Oswald:400,300"
	type="text/css">
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	<div id="wrapper">
		<header>
			<img src="images/logo.png" alt="Whitesquare logo">
		</header>
		<nav>
			<ul class="top-menu">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="Home" />
						<button type="submit">Home</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="post">
						<button type="submit">Login</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="post">
						<button type="submit">About Us</button>
					</form>
				</li>
			</ul>
		</nav>
		<div id="heading">
			<h1>Login Page</h1>
		</div>
		<section>
			<blockquote>
				<div class="col-6 col-sm-3">
					<form action="MainServlet" method="post" name="register">
						<div class="form-group">
							<input type="hidden" name="command" value="Login" /> <input
								type="text" class="edit" name="username" id="username"
								placeholder="Inter username" maxlength="45"> <input
								type="password" class="edit" name="password" id="username"
								placeholder="Inter password" maxlength="45">
							<button type="submit" class="active">Sing in</button>
						</div>
					</form>
					<form action="MainServlet" method="post" name="register">
						<div class="form-group">
						<input type="hidden" name="command" value="RegisterPage" />
							<button type="submit" class="active">Registration</button>
						</div>
					</form>
				</div>
			</blockquote>
		</section>
	</div>
</body>
</html>
