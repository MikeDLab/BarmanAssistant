<!doctype html>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.locale" var="locale" />
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
						<button type="submit">
							<fmt:message bundle="${locale}" key="menubar.homebutton" />
						</button>
					</form>
				</li>
				<c:if test="${sessionScope.Role == 'GUEST'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="SignIn" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.login" /></button>
					</form>
				</li>
			</c:if>
			<c:if test="${sessionScope.Role != 'GUEST'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="LogOut" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.logout" /></button>
					</form>
				</li>
			</c:if>
				<li class="active">
					<form action="Es" method="get">
						<button type="submit">Es</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="get">
						<input type="hidden" name="locale" value="Ru" /> <input
							type="hidden" name="pageId" value="index.jsp" />
						<button type="submit">Русский</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="get">
						<input type="hidden" name="locale" value="En" />
						<button type="submit">Eng</button>
					</form>
				</li>
				<c:if test="${sessionScope.Role != 'GUEST'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="UserPanel" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.userpanel" /></button>
					</form>
				</li>
				</c:if>
				<c:if test="${(sessionScope.Role == 'ADMIN') || sessionScope.Role == 'BARMAN' }">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="BarmanPanel" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.barmanpanel" /></button>
					</form>
				</li>
			</c:if>
				<c:if test="${sessionScope.Role == 'ADMIN'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="AdminPanel" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.adminpanel" /></button>
					</form>
				</li>
			</c:if>
			</ul>
		</nav>
		<div id="heading">

			<h1>${language}</h1>

			<h1>
				<label for="username"><fmt:message bundle="${locale}"
						key="homepage.title" /></label>
			</h1>
			<h1>${Role}</h1>
			<h1>${User.userName}</h1>
		</div>
		<aside>
			<nav>
				<ul class="aside-menu">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="CocktailList" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.cocktaillist" />
							</button>
						</form>
					</li>
					<c:if test="${sessionScope.Role != 'GUEST'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="ShowBarman" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.barmanlist" />
							</button>
						</form>
					</li>
					</c:if>
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="ShowIngredient" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}"
									key="sidebar.ingredientlist" />
							</button>
						</form>
					</li>
					<c:if test="${sessionScope.Role == 'ADMIN'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="AddIngredient" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.addingredientpage" />
							</button>
						</form>
					</li>
					</c:if>
					<c:if test="${sessionScope.Role != 'GUEST'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="AddCocktail" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.addcocktail" />
							</button>
						</form>
					</li>
					</c:if>
				</ul>
			</nav>
			<h2>OUR OFFICES</h2>
			<p>
				<img src="images/sample.png" width="230" height="180"
					alt="Our offices">
			</p>
		</aside>
		 <section>
			<blockquote>
				<form method="post">

				</form>

				<p>&ldquo;QUISQUE IN ENIM VELIT, AT DIGNISSIM EST. NULLA UL
					CORPER, DOLOR AC PELLENTESQUE PLACERAT, JUSTO TELLUS GRAVIDA ERAT,
					VEL PORTTITOR LIBERO ERAT.&rdquo;</p>
				<ctg:info-tag/>
			</blockquote>
<!-- 
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Aenean non neque ac sem accumsan rhoncus ut ut turpis. In hac
				habitasse platea dictumst. Proin eget nisi erat, et feugiat arcu.
				Duis semper porttitor lectus, ac pharetra erat imperdiet nec. Morbi
				interdum felis nulla. Aenean eros orci, pellentesque sed egestas
				vitae, auctor aliquam nisi. Nulla nec libero eget sem rutrum
				iaculis. Quisque in enim velit, at dignissim est. Nulla ullamcorper,
				dolor ac pellentesque placerat, justo tellus gravida erat, vel
				porttitor libero erat condimentum metus. Donec sodales aliquam orci
				id suscipit. Proin sed risus sit amet massa ultrices laoreet quis a
				erat. Aliquam et metus id erat vulputate egestas. Cum sociis natoque
				penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
			<p>Donec vel nisl nibh. Aenean quam tortor, tempus sit amet
				mattis dapibus, egestas tempor dui. Duis vestibulum imperdiet risus
				pretium pretium. Nunc vitae porta ligula. Vestibulum sit amet nulla
				quam. Aenean lacinia, ante vitae sodales sagittis, leo felis
				bibendum neque, mattis sagittis neque urna vel magna. Sed at sem
				vitae lorem blandit feugiat.</p>
			<p>Donec vel orci purus, ut ornare orci. Aenean rutrum
				pellentesque quam. Quisque gravida adipiscing augue, eget commodo
				augue egestas varius. Integer volutpat, tellus porta tincidunt
				sodales, lacus est tempus odio, fringilla blandit tortor lectus ut
				sem. Pellentesque nec sem lacus, sit amet consequat neque. Etiam
				varius urna quis arcu cursus in consectetur dui tincidunt. Quisque
				arcu orci, lacinia eget pretium vel, iaculis pellentesque nibh.
				Etiam cursus lacus eget neque viverra vestibulum. Aliquam erat
				volutpat. Duis pulvinar tellus ut urna facilisis mollis. Maecenas ac
				pharetra dui. Pellentesque neque ante, luctus eget congue eget,
				rhoncus vel mauris. Duis nisi magna, aliquet a convallis non,
				venenatis at nisl. Nunc at quam eu magna malesuada dignissim. Duis
				bibendum iaculis felis, eu venenatis risus sodales non. In ligula
				mi, faucibus eu tristique sed, vulputate rutrum dolor.</p>

			<figure>
				<img src="images/sample.png" width="320" height="175" alt="">
			</figure>
			<figure>
				<img src="images/sample.png" width="320" height="175" alt="">
			</figure>

			<h2>OUR TEAM</h2>
			<div class="team-row">
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						John Doe<span>ceo</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Saundra Pittsley<span>team leader</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Julio Simser<span>senior developer</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Margery Venuti<span>senior developer</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Fernando Tondrea<span>developer</span>
					</figcaption>
				</figure>
			</div>
			<div class="team-row">
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Ericka Nobriga<span>art director</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Cody Rousselle<span>senior ui designer</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Erik Wollman<span>senior ui designer</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Dona Shoff<span>ux designer</span>
					</figcaption>
				</figure>
				<figure>
					<img src="images/sample.png" width="96" height="96" alt="">
					<figcaption>
						Darryl Brunton<span>ui designer</span>
					</figcaption>
				</figure>
			</div -->>
		</section> 
	</div>
	
</body>
</html>
