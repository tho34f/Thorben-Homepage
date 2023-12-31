package com.thorben.objectbrowser.title.filter;

import com.thorben.service.ThorbenDierkes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ob3FilterDefinitions {
	
	TITLE(1, ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE, ThorbenDierkes.LIKE),
	TEASER(2,ThorbenDierkes.TEASER, ThorbenDierkes.TEASER_TITLE, ThorbenDierkes.LIKE),
	DESCRIPTION(3, ThorbenDierkes.DESCRIPTION, ThorbenDierkes.DESCRIPTION_TITLE, ThorbenDierkes.LIKE),
	LOGIN(4, ThorbenDierkes.LOGIN_OB3,ThorbenDierkes.LOGIN_OB3_TITLE, ThorbenDierkes.LIKE),
	FIRSTNAME(5, ThorbenDierkes.FIRSTNAME,ThorbenDierkes.FIRSTNAME_TITLE, ThorbenDierkes.LIKE),
	LASTNAME(6, ThorbenDierkes.LASTNAME,ThorbenDierkes.LASTNAME_TITLE, ThorbenDierkes.LIKE);
	
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
