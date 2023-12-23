package com.thorben.objectbrowser.title.filter;

import com.thorben.service.ThorbenDierkes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ob3ColumDefinitions {
	
	TITLE(1, ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE),
	TEASER(2,ThorbenDierkes.TEASER, ThorbenDierkes.TEASER_TITLE),
	DESCRIPTION(3, ThorbenDierkes.DESCRIPTION, ThorbenDierkes.DESCRIPTION_TITLE),
	CREATION_DATE(4, ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE),
	CHANGE_DATE(5, ThorbenDierkes.CHANGE_DATE,ThorbenDierkes.CHANGE_DATE_TITLE),
	LOGIN(6, ThorbenDierkes.LOGIN_OB3,ThorbenDierkes.LOGIN_OB3_TITLE),
	FIRSTNAME(7, ThorbenDierkes.FIRSTNAME,ThorbenDierkes.FIRSTNAME_TITLE),
	LASTNAME(8, ThorbenDierkes.LASTNAME,ThorbenDierkes.LASTNAME_TITLE);
	
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
