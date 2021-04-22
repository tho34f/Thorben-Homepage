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
					<a class="nav-link" href="/thorben-dierkes/backend/backendindex">Startseite</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes/backend/backendObjectBrowser?id=39" id="navbardrop"> Nachrichten </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes/backend/backendObjectBrowser?id=40" id="navbardrop"> Termine </a>
				</li>
			</ul>
			<div>
				<form class="example" id="search" method="post" action="search">
					<input type="text" id="suche" placeholder="Suche..." name="suchen">
					<button type="submit">Los!</button>
				</form>
			</div>
		</nav>
		<div class="headerPanel">
			<div style="margin-top: -5px;height: 75px;border-right: 1px solid #bbb;float: left;width: 270px;">
				<div class="${Objectbrowser.objectIcon}" id="objectIcon"></div>
				<h4 id="objectHeader">${Objectbrowser.objectTitle}</h4>
			</div>
			<div>
				<button id="newObject" name="${Objectbrowser.objectType}" type="button" title="${Objectbrowser.buttonTitle} erzeugen">
					<em>${Objectbrowser.buttonTitle}</em>
				</button>
			</div>
		</div>
</header>