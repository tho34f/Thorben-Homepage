package com.thorben.objects.snooker;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
		this.provisionalRanking = provisionalranking;
		this.winPercentage = winPercentage;
		this.centuryBreaks = centuryBreaks;
		this.age = age;
		this.profiYears = profiYears;
	}

}
