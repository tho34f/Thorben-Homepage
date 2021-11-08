<%@ taglib prefix="tho" uri="/thorben"%>
<header>
	<div id="mainLogo">
		<img src="../resources/core/images/logo_large.png" alt="Bild Thorben" style="width:500px;margin-top:4px;" />
	</div>
	<ul id="serviceBox">
		<li>
			<strong>
				${user.firstName} ${user.lastName }
			</strong>
		</li>
		<li>
			<button type="button" title="Informationen" class="dooBtn" id="informationButton">
				<em class="fa fa-info" style="font-size:24px" id="dooIcon"></em>
			</button>
			<button type="button" title="Zum Frontend" class="dooBtn" id="wechselButton">
				<em class="fa fa-retweet" style="font-size:24px" id="dooIcon"></em>
			</button>
			<button type="button" title="Abmelden" class="dooBtn" id="abmeldeButton">
				<em class="fa fa-sign-out" style="font-size:24px" id="dooIcon"></em>
			</button>
		</li>
	</ul>
		<nav class="navbar navbar-inverse navbar-expand-sm">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes/backend/backendindex"><tho:out value="global.hompage.home"/></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes/backend/backendObjectBrowser?id=38" id="navbardrop"> <tho:out value="global.user"/> </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes/backend/backendObjectBrowser?id=39" id="navbardrop"> <tho:out value="global.news"/> </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes/backend/backendObjectBrowser?id=40" id="navbardrop"> <tho:out value="global.events"/> </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes/backend/backendObjectBrowser?id=41" id="navbardrop"> <tho:out value="global.error.log"/> </a>
				</li>
			</ul>
			<div>
				<form class="example" id="search" method="post" action="search">
					<input type="text" id="suche" placeholder="Suche..." name="suchen">
					<button type="submit"><tho:out value="global.go"/></button>
				</form>
			</div>
		</nav>
</header>