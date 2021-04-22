package com.thorben.helloworld.snooker;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TournamentSeason implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -770545307407172563L;
	private int year;
	
	public TournamentSeason (int year){
		this.setYear(year);
	}
	
	private List<Spieler> player = new ArrayList<>();
	private List<Tournament> tournamentSeasonList = new ArrayList<>();
	
	public void registerPlayer(Spieler player2) {
		getPlayer().add(player2);
	}
	
	public void registerTournament(Tournament tournament) {
		getTournamentSeason().add(tournament);
		
	}

	public List<Spieler> getPlayer() {
		return player;
	}

	public void setPlayer(List<Spieler> player) {
		this.player = player;
	}

	public List<Tournament> getTournamentSeason() {
		return tournamentSeasonList;
	}

	public void setTournamentSeason(List<Tournament> tournamentSeason) {
		this.tournamentSeasonList = tournamentSeason;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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


