<%@ taglib prefix="tho" uri="/thorben"%>
<header id="pageHeader" class="pageHeader">
	<div class="headerContent">
		<nav class="navbar navbar-inverse navbar-expand-sm" id="navigation">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes"><tho:out value="global.hompage.home"/></a>
				</li>
				<li class="nav-item dropdown" onmouseover="fn_showNavDropdown(1)" onmouseout="fn_hideNavDropdown(1)">
					<a class="nav-link dropdown-toggle" href="/thorben-dierkes/personal" id="navbardrop" data-toggle="dropdown"><tho:out value="global.name.owner"/></a>
     				<div class="dropdown-menu menuDiv" id="navElement1">
     					<a class="dropdown-item" href="/thorben-dierkes/personal"><tho:out value="global.name.owner.about"/></a>
     					<a class="dropdown-item" href="/thorben-dierkes/newsslider"><tho:out value="global.news"/></a>
     					<a class="dropdown-item" href="/thorben-dierkes/terminslider"><tho:out value="global.events"/></a>
					</div>
				</li>
				<li class="nav-item dropdown" onmouseover="fn_showNavDropdown(2)" onmouseout="fn_hideNavDropdown(2)">
					<a class="nav-link dropdown-toggle" href="/thorben-dierkes/snooker" id="navbardrop" data-toggle="dropdown"><tho:out value="global.snooker"/></a>
   					<div class="dropdown-menu menuDiv" id="navElement2">
     					<a class="dropdown-item" href="/thorben-dierkes/snookernews"><tho:out value="global.snooker.news"/></a>
     					<a class="dropdown-item" href="/thorben-dierkes/overview"><tho:out value="global.snooker.history"/></a>
						<a class="dropdown-item" href="/thorben-dierkes/saison"><tho:out value="global.snooker.seasons"/></a>
					</div>
				</li>
				<li class="nav-item dropdown" onmouseover="fn_showNavDropdown(3)" onmouseout="fn_hideNavDropdown(3)">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><tho:out value="global.politic"/></a>
   					<div class="dropdown-menu menuDiv" id="navElement3">
     					<a class="dropdown-item" href="/thorben-dierkes/politik"><tho:out value="global.politic.goals"/></a>
     					<a class="dropdown-item" href="/thorben-dierkes/politik-werdegang"><tho:out value="global.politic.career"/></a>
					</div>
				</li>
				<li class="nav-item dropdown" onmouseover="fn_showNavDropdown(4)" onmouseout="fn_hideNavDropdown(4)">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><tho:out value="global.mathematic"/></a>
     				<div class="dropdown-menu menuDiv" id="navElement4">
     					<a class="dropdown-item" href="/thorben-dierkes/math"><tho:out value="global.mathematic.basics"/></a>
					</div>
				</li>
			</ul>
		</nav>
	</div>

	<div class="headerLogo">
		<a href="/thorben-dierkes"><img src="resources/core/images/logo_large.png" alt="Bild Thorben" class="headerImage" id="headerImage" /></a>
	</div>
</header>