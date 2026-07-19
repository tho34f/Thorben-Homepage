package com.thorben.backend.service;

import com.thorben.objectbrowser.ObjectBrowser;
import com.thorben.objectbrowser.ObjectBrowserData;
import com.thorben.objectbrowser.ObjectBrowserService;
import com.thorben.objectbrowser.filter.ObjectBrowserFilter;
import com.thorben.objectbrowser.title.ObjectBrowserTitle;
import com.thorben.objects.AbstractData;
import com.thorben.queries.MySql;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Service
@NoArgsConstructor
public class BackendService extends AbstractBackendService {

	public void fillObjectBrowser(ObjectBrowser ob, Locale locale, int objectId) {
		if (ob != null) {
			List<ObjectBrowserTitle> titleList = MySql.getInstance().getOb3Queries().getColumTitleForOb3(objectId, locale);
			List<ObjectBrowserFilter> filterList = MySql.getInstance().getOb3Queries().getFilterForOb3(objectId, locale);
			ObjectBrowserData obd = new ObjectBrowserData(titleList, filterList);
			ob.setOb3Data(obd);
		}
	}

	public ObjectBrowser createAndfillObjectBrowser(HttpServletRequest request, int objectId) {
		ObjectBrowser ob = ObjectBrowserService.createObjectBrowser(objectId);
		fillObjectBrowser(ob, request.getLocale(), objectId);

		Map<String,String> filterValue = ObjectBrowserService.getOb3FilterValue(request);
		int id = ob.getObjectType();

		Set<? extends AbstractData> objects = ObjectBrowserService.getObjects(id, filterValue);
		if(objects.isEmpty()) {
			clearInformationAndSetError(request);
		} else {
			ob.getOb3Data().setObjectList(objects);
		}

		return ob;
	}
}