<%@ taglib prefix="tho" uri="/thorben"%>
<header id="pageHeader" class="pageHeader">
		<nav class="navbar navbar-inverse navbar-expand-sm" id="navigation">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes"><tho:out value="global.hompage.home"/></a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/thorben-dierkes/personal" id="navbardrop" data-toggle="dropdown"><tho:out value="global.name.owner"/></a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/thorben-dierkes/personal"><tho:out value="global.name.owner.about"/></a>
      					<a class="dropdown-item" href="/thorben-dierkes/newsslider"><tho:out value="global.news"/></a>
      					<a class="dropdown-item" href="/thorben-dierkes/terminslider"><tho:out value="global.events"/></a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/thorben-dierkes/snooker" id="navbardrop" data-toggle="dropdown"><tho:out value="global.snooker"/></a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/thorben-dierkes/snookernews"><tho:out value="global.snooker.news"/></a>
      					<a class="dropdown-item" href="/thorben-dierkes/overview"><tho:out value="global.snooker.history"/></a>
						<a class="dropdown-item" href="/thorben-dierkes/saison"><tho:out value="global.snooker.seasons"/></a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><tho:out value="global.politic"/></a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/thorben-dierkes/politik"><tho:out value="global.politic.goals"/></a>
      					<a class="dropdown-item" href="/thorben-dierkes/politik-werdegang"><tho:out value="global.politic.career"/></a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><tho:out value="global.mathematic"/></a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/thorben-dierkes/math"><tho:out value="global.mathematic.basics"/></a>
					</div>
				</li>
			</ul>
			<div>
				<form class="example" id="search" method="post" action="search">
					<input type="text" id="suche" placeholder="Suche..." name="suchen">
					<button type="submit">Los!</button>
				</form>
			</div>
			<div style="margin-left: auto;">
				<h2 class="headerHashTag" id="headerHashTag">#POLITICALMATHE</h2>
			</div>
		</nav>

		<div style="text-align:center">
			<a href="/thorben-dierkes"><img src="resources/core/images/logo_large.png" alt="Bild Thorben" class="headerImage" id="headerImage" /></a>
		</div>
</header>