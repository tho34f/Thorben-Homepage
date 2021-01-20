package com.mkyong.helloworld.snooker;


import java.util.ArrayList;
import java.util.List;


public class Tournament_Season {
	
	private int year;
	
	public Tournament_Season (int year){
		this.setYear(year);
	}
	
	private List<Spieler> player = new ArrayList<Spieler>();
	private List<Tournament> tournament_season = new ArrayList<Tournament>();
	
	public void registerPlayer(Spieler player2) {
		getPlayer().add(player2);
	}
	
	public void registerTournament(Tournament tournament) {
		getTournament_season().add(tournament);
		
	}

	public List<Spieler> getPlayer() {
		return player;
	}

	public void setPlayer(List<Spieler> player) {
		this.player = player;
	}

	public List<Tournament> getTournament_season() {
		return tournament_season;
	}

	public void setTournament_season(List<Tournament> tournament_season) {
		this.tournament_season = tournament_season;
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
		Tournament_Season other = (Tournament_Season) obj;
		if (year != other.year)
			return false;
		return true;
	}

	
}


