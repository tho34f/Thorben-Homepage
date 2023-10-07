package com.thorben.objects.snooker;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tournament implements Serializable{
	
	private static final long serialVersionUID = 1462585822428695731L;
	
	private String tournamentname;
	private float gewicht;
	private double playernumber;
	private double roundnumber;
	
	
}
