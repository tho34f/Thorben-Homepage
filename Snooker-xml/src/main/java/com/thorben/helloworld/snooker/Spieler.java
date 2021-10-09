package com.thorben.helloworld.snooker;

import java.io.Serializable;

public class Spieler  implements Serializable{
	
	private static final long serialVersionUID = 916064280372814616L;
	private String firstname;
	private String lastname;
	private int worldRanking;
	private int provisionalRanking;
	private float winPercentage;
	private int centuryBreaks;
	private int age;
	private int profiYears;
	
	public Spieler(String firstname, String lastname, int worldranking, int provisionalranking, float winPercentage, int centuryBreaks, int age, int profiYears) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.worldRanking = worldranking;
		this.setProvisionalRanking(provisionalranking);
		this.winPercentage = winPercentage;
		this.centuryBreaks = centuryBreaks;
		this.age = age;
		this.profiYears = profiYears;
	}
	
	public double getWorldRanking() {
		return worldRanking;
	}
	
	public void setWorldRanking(int neuerWorldranking) {
		worldRanking = neuerWorldranking;
	}
	
	public void playerNameDruck() {
		System.out.format("%5s %5s, Weltrangliste: %5.2f, Provisional_Ranking: %5.2f %n", firstname, lastname, worldRanking, getProvisionalRanking());
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public float getWinPercentage() {
		return winPercentage;
	}

	public void setWinPercentage(float winPercentage) {
		this.winPercentage = winPercentage;
	}

	public int getCenturyBreaks() {
		return centuryBreaks;
	}

	public void setCenturyBreaks(int centuryBreaks) {
		this.centuryBreaks = centuryBreaks;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getProfiYears() {
		return profiYears;
	}

	public void setProfiYears(int profiYears) {
		this.profiYears = profiYears;
	}

	public int getProvisionalRanking() {
		return provisionalRanking;
	}

	public void setProvisionalRanking(int provisionalRanking) {
		this.provisionalRanking = provisionalRanking;
	}



}
