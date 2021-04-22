package com.thorben.helloworld.snooker;

import java.io.Serializable;

public class Tournament implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1462585822428695731L;
	private String tournamentname;
	private float gewicht;
	private double playernumber;
	private double roundnumber;
	
	
	public Tournament(String tournamentname, float gewicht, double playernumber, double roundnumber) {
		this.tournamentname = tournamentname;
		this.gewicht = gewicht;
		this.setRoundnumber(roundnumber);
		this.setPlayernumber(playernumber);
	}
	
	public double getGewicht() {
		return gewicht;
	}
	
	public void setGewicht(float neuesGewicht) {
		gewicht = neuesGewicht;
	}

	public String getTournamentname() {
		return tournamentname;
	}

	public void setTournamentname(String tournamentname) {
		this.tournamentname = tournamentname;
	}

	public double getPlayernumber() {
		return playernumber;
	}

	public void setPlayernumber(double playernumber) {
		this.playernumber = playernumber;
	}

	public double getRoundnumber() {
		return roundnumber;
	}

	public void setRoundnumber(double roundnumber) {
		this.roundnumber = roundnumber;
	}
	
}
