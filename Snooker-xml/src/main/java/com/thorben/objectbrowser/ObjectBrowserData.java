package com.thorben.objectbrowser;

import java.util.List;
import java.util.Set;

import com.thorben.objectbrowser.title.filter.ObjectBrowserFilter;
import com.thorben.objectbrowser.title.filter.ObjectBrowserTitle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObjectBrowserData {
	
	private List<ObjectBrowserTitle> title;
	private List<ObjectBrowserFilter> filter;
	private Set<?> objectList;
	
	public ObjectBrowserData(List<ObjectBrowserTitle> titleList, List<ObjectBrowserFilter> filterList) {
		this.title = titleList;
		this.filter = filterList;
	}
	

}
