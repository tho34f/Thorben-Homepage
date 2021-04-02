package com.thorben.helloworld.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.thorben.helloworld.snooker.Tournament;
import com.thorben.helloworld.snooker.Spieler;

public class ReadData {
	
	public void readData() throws FileNotFoundException {
		try (Scanner scanner1 = new Scanner(new File("D:/eclipse-workspace/Snooker/src/objekte/spieler.txt"),"UTF-8")){
		
		    while (scanner1.hasNext()) {
		    	String player = scanner1.next();
		    	
		    	String[] splitten1 = player.split(";");
		    	String first = splitten1[1];
		    	String last = splitten1[2];
		    	int worldranking = Integer.parseInt(splitten1[3]);
		    	int provisionalranking = Integer.parseInt(splitten1[4]);
		    	
		    	Spieler player1 = new Spieler(first,last,worldranking,provisionalranking, 1, 1, 1, 1);
		 
		 
		    }
		}
	    
		try(Scanner scanner2 = new Scanner(new File("D:/eclipse-workspace/Snooker/src/objekte/tournament.txt"),"UTF-8")){
		    while (scanner2.hasNext()) {
		    	String tournament = scanner2.next();
		    	
		    	String[] splitten2 = tournament.split(";");
		    	String first = splitten2[1];
		    	float weight = Float.parseFloat(splitten2[2]);
		    	int players = Integer.parseInt(splitten2[3]);
		    	int roundnumber = Integer.parseInt(splitten2[4]);
		    	
		    	Tournament tournament1 = new Tournament(first,weight,players,roundnumber);
		    	
		 
		    }
		}
	   
	}

}
