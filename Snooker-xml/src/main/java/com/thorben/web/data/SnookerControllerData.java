package com.thorben.web.data;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.thorben.objects.snooker.TournamentSeason;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class SnookerControllerData extends ControllerData {
	
	private Set<TournamentSeason> seasons = new HashSet<>();

}
