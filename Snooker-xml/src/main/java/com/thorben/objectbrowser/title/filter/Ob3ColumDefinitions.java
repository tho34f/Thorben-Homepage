package com.thorben.objectbrowser.title.filter;

import com.thorben.service.ThorbenDierkes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ob3ColumDefinitions {
	
	TITLE(1, ThorbenDierkes.TITLE_TITLE, "backend.wizard.title"),
	TEASER(2,ThorbenDierkes.TEASER, "backend.wizard.teaser"),
	DESCRIPTION(3, ThorbenDierkes.DESCRIPTION, "backend.wizard.text"),
	CREATION_DATE(4, ThorbenDierkes.CREATION_DATE,"global.creation.date"),
	CHANGE_DATE(5, ThorbenDierkes.CHANGE_DATE,"global.change.date"),
	LOGIN(6, ThorbenDierkes.LOGIN_OB3,"global.username"),
	FIRSTNAME(7, ThorbenDierkes.FIRSTNAME,"global.firstname"),
	LASTNAME(8, ThorbenDierkes.LASTNAME,"global.surname");
	
	private int id;
	private String title;
	private String description;
	
	public static Ob3ColumDefinitions getById(int id) {
        for (Ob3ColumDefinitions es : Ob3ColumDefinitions.values()) {
            if (es.id == id) {
                return es;
            }
        }
        throw new IllegalArgumentException();
	}

}
