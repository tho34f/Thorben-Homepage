<header>
	<div id="mainLogo">
		<img src="../resources/core/images/logo_large.png" alt="Bild Thorben" style="width:500px;margin-top:4px;" />
	</div>
	<ul id="serviceBox">
		<li>
			<b>
				${user.firstName} ${user.lastName }
			</b>
		</li>
		<li>
			<button type="button" title="Abmelden" class="dooBtn" id="abmeldeButton">
				<em class="fa fa-sign-out" style="font-size:24px" id="dooIcon"></em>
			</button>
		</li>
	</ul>
		<nav class="navbar navbar-inverse navbar-expand-sm">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="/thorben-dierkes">Startseite</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/thorben-dierkes/snooker" id="navbardrop" data-toggle="dropdown"> Snooker </a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/thorben-dierkes/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/thorben-dierkes/saison">Season</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/thorben-dierkes/personal" id="navbardrop" data-toggle="dropdown"> Persönliches </a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/thorben-dierkes/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/thorben-dierkes/saison">Season</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/thorben-dierkes/politik" id="navbardrop" data-toggle="dropdown"> Politik </a>
      				<div class="dropdown-menu">
      					<a class="dropdown-item" href="/thorben-dierkes/saisonOverwiev">Überblickt erzeugte Saisons</a>
						<a class="dropdown-item" href="/thorben-dierkes/saison">Season</a>
					</div>
				</li>
			</ul>
			<div>
				<form class="example" id="search" method="post" action="search" value="Suchen">
					<input type="text" id="suche" placeholder="Suche..." name="suchen">
					<button type="submit">Los!</button>
				</form>
			</div>

		</nav>
</header>