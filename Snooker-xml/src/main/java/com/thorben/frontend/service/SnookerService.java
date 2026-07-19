package com.thorben.frontend.service;

import com.thorben.frontend.data.SnookerControllerData;
import com.thorben.objects.snooker.Player;
import com.thorben.objects.snooker.Tournament;
import com.thorben.objects.snooker.TournamentSeason;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.TypeConverter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor()
public class SnookerService extends AbstractFrontendService {

	private static final String TOURNAMENT = "tournament";
	
	public boolean getData(String url, String action) {
		List<Object> data = new ArrayList<>();
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create(ThorbenDierkes.SNOOKER_RADAR_API + url + "?" + ThorbenDierkes.SNOOKER_RADAR_API_KEY))
			    .header("accept", "application/json")
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
		HttpResponse<String> response;
		
		try(HttpClient client = HttpClient.newHttpClient()) {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if(response.statusCode() == HttpStatus.OK.value()) {
				JSONObject jsonObject = new JSONObject(response.body());
				JSONArray jsonObjectCompetitions = null;
				if(action.equals(TOURNAMENT)) {
					jsonObjectCompetitions = (JSONArray) jsonObject.get("competitions");
				} else {
					jsonObjectCompetitions = (JSONArray) jsonObject.get("season_competitors");
				}
				
				for(Object competition : jsonObjectCompetitions) {
					if(action.equals(TOURNAMENT)) {
						data.add(Tournament.convertJsonToObject((JSONObject) competition));
					} else {
						data.add(Player.convertJsonToObject((JSONObject) competition));
					}
				}
				
				if(action.equals(TOURNAMENT)) {
					MySql.getInstance().getSnookerQueries().insertOrUpdateTournament(data);
				} else {
					MySql.getInstance().getSnookerQueries().insertOrUpdatePlayer(data);
				}
			}
		} catch (IOException | InterruptedException e) {
			Thread.currentThread().interrupt();
			LOOGER.errorLogWithTrace("Exception", "Aufruf der Snooker-Api fehlgeschlagen", e);
		}

		
		return true;
	}
	
	public static Player simulation(String participationPlayer) {
		
		String[] arrayPlayer = participationPlayer.split(",");
		return new Player(arrayPlayer[0], arrayPlayer[1],0,0,0,0,0,0, 0);
	
	}
	
	public static TournamentSeason creatSeason(int year) {
		
		TournamentSeason season = new TournamentSeason(year);
		
        // Ergebnisse anzeigen.
		if(MySql.getInstance().getSnookerQueries() != null) {
			MySql.getInstance().getSnookerQueries().creatTournamentList(season);
			MySql.getInstance().getSnookerQueries().creatPlayerList(season);
		}

		return season;
	}
	
	public static boolean setSeason(String number, SnookerControllerData controllerData, final HttpServletRequest request) {
		
		TournamentSeason season = null;
		boolean isSeasonOk = false;
		
		if(!number.isEmpty()) {
			int number2 = TypeConverter.string2int(number, 0);
			season =  creatSeason(number2);
			controllerData.getSeasons().add(season);
			isSeasonOk = true;
		}
		
		LOOGER.infoLog("Saision erfolgreich erzeugt.");
		request.getSession().setAttribute("seasions", controllerData.getSeasons());	
		
		return isSeasonOk;

	}

}
