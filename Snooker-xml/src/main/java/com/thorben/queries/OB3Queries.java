package com.thorben.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.thorben.objectbrowser.title.filter.Ob3ColumDefinitions;
import com.thorben.objectbrowser.title.filter.Ob3FilterDefinitions;
import com.thorben.objectbrowser.title.filter.ObjectBrowserFilter;
import com.thorben.objectbrowser.title.filter.ObjectBrowserTitle;
import com.thorben.tags.service.BundleTranslator;

public class OB3Queries extends AbstractQuerries{

	public OB3Queries(MySql sql) {
		super(sql);
	}
	
	
	public List<ObjectBrowserTitle> getColumTitleForOb3(int ob3Id, Locale locale){
		BundleTranslator bt = new BundleTranslator(locale.getLanguage());
		List<ObjectBrowserTitle> titleList = new ArrayList<>();
		
		String sql = "SELECT title_id FROM ob3_title_definition WHERE ob3_number = ?";
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, ob3Id);
				try(ResultSet rs = stmt.executeQuery()){
					int columId = 0;
					ObjectBrowserTitle title = null;
					Ob3ColumDefinitions colum = null;
					while(rs.next()) {
						columId = rs.getInt("title_id");
						colum = Ob3ColumDefinitions.getById(columId);
						title = new ObjectBrowserTitle(colum.getId(), colum.getTitle(), bt.translatBundle(colum.getDescription()));
						titleList.add(title);
					}
				}
			}
			
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return titleList;
	}
	
	public List<ObjectBrowserFilter> getFilterForOb3(int ob3Id, Locale locale){
		BundleTranslator bt = new BundleTranslator(locale.getLanguage());
		List<ObjectBrowserFilter> filterList = new ArrayList<>();
		
		String sql = "SELECT filter_id FROM ob3_filter_definition WHERE ob3_number = ?";
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, ob3Id);
				try(ResultSet rs = stmt.executeQuery()){
					int filterId = 0;
					ObjectBrowserFilter filter = null;
					Ob3FilterDefinitions filterDefinition = null;
					while(rs.next()) {
						filterId = rs.getInt("filter_id");
						filterDefinition = Ob3FilterDefinitions.getById(filterId);
						filter = new ObjectBrowserFilter(filterDefinition.getId(), filterDefinition.getTitle(), bt.translatBundle(filterDefinition.getDescription()),"",filterDefinition.getSqlFunction());
						filterList.add(filter);
					}
				}
			}
			
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return filterList;
	}

}
