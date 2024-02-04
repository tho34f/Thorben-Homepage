package com.thorben.objectbrowser.title.filter;

import com.thorben.service.ThorbenDierkes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ob3FilterDefinitions {
	
	TITLE(1, ThorbenDierkes.TITLE_TITLE, "backend.wizard.title", ThorbenDierkes.LIKE),
	TEASER(2,ThorbenDierkes.TEASER, "backend.wizard.teaser", ThorbenDierkes.LIKE),
	DESCRIPTION(3, ThorbenDierkes.DESCRIPTION, "backend.wizard.text", ThorbenDierkes.LIKE),
	LOGIN(4, ThorbenDierkes.LOGIN_OB3,"global.username", ThorbenDierkes.LIKE),
	FIRSTNAME(5, ThorbenDierkes.FIRSTNAME,"global.firstname", ThorbenDierkes.LIKE),
	LASTNAME(6, ThorbenDierkes.LASTNAME,"global.surname", ThorbenDierkes.LIKE);
	
	private int id;
	private String title;
	private String description;
	private String sqlFunction;
	
	public static Ob3FilterDefinitions getById(int id) {
        for (Ob3FilterDefinitions es : Ob3FilterDefinitions.values()) {
            if (es.id == id) {
                return es;
            }
        }
        throw new IllegalArgumentException();
	}

}
