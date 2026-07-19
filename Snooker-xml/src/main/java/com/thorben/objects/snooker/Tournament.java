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
@AllArgsConstructor
@NoArgsConstructor
public class Tournament implements Serializable{
	
	@Serial
    private static final long serialVersionUID = 1462585822428695731L;
	
	private String tournamentname;
	private int id;
	private float gewicht;
	private double playernumber;
	private double roundnumber;
	
	public static Tournament convertJsonToObject(JSONObject object) {
		Tournament tournament = new Tournament();
		tournament.setTournamentname(object.getString("name"));
		tournament.setId(TypeConverter.string2int(object.getString("id").split(":")[2], 0));
		return tournament;
	}
	
	
}
