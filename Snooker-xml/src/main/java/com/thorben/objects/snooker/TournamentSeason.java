package com.thorben.objects.snooker;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentSeason implements Serializable{
	
	private static final long serialVersionUID = -770545307407172563L;
	private int year;
	private List<Spieler> player = new ArrayList<>();
	private List<Tournament> tournamentSeasonList = new ArrayList<>();
	
	public TournamentSeason (int year){
		this.setYear(year);
	}
	
	public void registerPlayer(Spieler player2) {
		getPlayer().add(player2);
	}
	
	public void registerTournament(Tournament tournament) {
		getTournamentSeasonList().add(tournament);
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TournamentSeason other = (TournamentSeason) obj;
		if (year != other.year)
			return false;
		return true;
	}

	
}


