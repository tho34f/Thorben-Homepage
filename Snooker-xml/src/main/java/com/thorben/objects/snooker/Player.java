package com.thorben.objects.snooker;

import java.io.Serial;
import java.io.Serializable;

import org.json.JSONObject;

import com.thorben.service.TypeConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Serializable{
	
	@Serial
    private static final long serialVersionUID = 916064280372814616L;
	
	private String firstname;
	private String lastname;
	private int id;
	private int worldRanking;
	private int provisionalRanking;
	private float winPercentage;
	private int centuryBreaks;
	private int age;
	private int profiYears;
	
	public static Player convertJsonToObject(JSONObject object) {
		Player player = new Player();
		player.setId(TypeConverter.string2int(object.getString("id").split(":")[2], 0));
		String[] names = object.getString("name").split(",");
		player.setFirstname(names[1]);
		player.setLastname(names[1]);
		return player;
		
	}

}
