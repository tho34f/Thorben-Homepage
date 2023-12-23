package com.thorben.queries.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.thorben.objectbrowser.title.filter.Ob3ColumDefinitions;
import com.thorben.objectbrowser.title.filter.Ob3FilterDefinitions;
import com.thorben.queries.AbstractQuerries;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;

public class Ob3Updates extends AbstractQuerries{
	
	private static final ThorbenDierkesLogger logger = new ThorbenDierkesLogger();

	public Ob3Updates(MySql sql) {
		super(sql);
	}
	
	public boolean writeOb3Title() {
		logger.infoLog("Ob3Updates: writeOb3Title()");
		boolean success = false;
		String sql = "INSERT INTO ob3_title (`id`, `title`, `description`) VALUES (?,?,?) " + 
						" ON DUPLICATE KEY UPDATE `title` = ?, `description` = ?;";
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
			try(PreparedStatement pstmt = con.prepareStatement(sql)){
				for(Ob3ColumDefinitions title : Ob3ColumDefinitions.values()) {
					pstmt.setInt(1, title.getId());
					pstmt.setString(2, title.getTitle());
					pstmt.setString(3, title.getDescription());
					pstmt.setString(4, title.getTitle());
					pstmt.setString(5, title.getDescription());
					pstmt.addBatch();
				}
				pstmt.executeBatch();
				con.commit();
				success = true;
			}
		}catch (SQLException e) {
			handleSqlException(e);
		} 
		return success;
	}
	
	public boolean writeOb3TitleDefinition() {
		logger.infoLog("Ob3Updates: writeOb3TitleDefinition()");
		boolean success = false;
		
		String deleteSql = "DELETE FROM ob3_title_definition;";
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
			try(PreparedStatement pstmt = con.prepareStatement(deleteSql)){
				success = pstmt.execute();
			}
			con.commit();
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		String insertSql = "INSERT INTO ob3_title_definition (`ob3_number`, `title_id`) VALUES (?,?), " + 
						" (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), (?,?), " + 
						" (?,?), (?,?), (?,?);";
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
			try(PreparedStatement pstmt = con.prepareStatement(insertSql)){
				int counter = 1;
				//User-OB3
				pstmt.setInt(counter++, ThorbenDierkes.USER);
				pstmt.setInt(counter++, Ob3ColumDefinitions.LOGIN.getId());
				pstmt.setInt(counter++, ThorbenDierkes.USER);
				pstmt.setInt(counter++, Ob3ColumDefinitions.FIRSTNAME.getId());
				pstmt.setInt(counter++, ThorbenDierkes.USER);
				pstmt.setInt(counter++, Ob3ColumDefinitions.LASTNAME.getId());
				pstmt.setInt(counter++, ThorbenDierkes.USER);
				pstmt.setInt(counter++, Ob3ColumDefinitions.CREATION_DATE.getId());
				pstmt.setInt(counter++, ThorbenDierkes.USER);
				pstmt.setInt(counter++, Ob3ColumDefinitions.CHANGE_DATE.getId());
				//News-Ob3
				pstmt.setInt(counter++, ThorbenDierkes.NEWS);
				pstmt.setInt(counter++, Ob3ColumDefinitions.TITLE.getId());
				pstmt.setInt(counter++, ThorbenDierkes.NEWS);
				pstmt.setInt(counter++, Ob3ColumDefinitions.TEASER.getId());
				pstmt.setInt(counter++, ThorbenDierkes.NEWS);
				pstmt.setInt(counter++, Ob3ColumDefinitions.CREATION_DATE.getId());
				pstmt.setInt(counter++, ThorbenDierkes.NEWS);
				pstmt.setInt(counter++, Ob3ColumDefinitions.CHANGE_DATE.getId());
				//Termine-OB3
				pstmt.setInt(counter++, ThorbenDierkes.CALENDAR);
				pstmt.setInt(counter++, Ob3ColumDefinitions.TITLE.getId());
				pstmt.setInt(counter++, ThorbenDierkes.CALENDAR);
				pstmt.setInt(counter++, Ob3ColumDefinitions.DESCRIPTION.getId());
				pstmt.setInt(counter++, ThorbenDierkes.CALENDAR);
				pstmt.setInt(counter++, Ob3ColumDefinitions.TEASER.getId());
				pstmt.setInt(counter++, ThorbenDierkes.CALENDAR);
				pstmt.setInt(counter++, Ob3ColumDefinitions.CREATION_DATE.getId());
				pstmt.setInt(counter++, ThorbenDierkes.CALENDAR);
				pstmt.setInt(counter++, Ob3ColumDefinitions.CHANGE_DATE.getId());
				//ERROR-Log-Ob3
				pstmt.setInt(counter++, ThorbenDierkes.ERROR_LOG_MASSAGE);
				pstmt.setInt(counter++, Ob3ColumDefinitions.TITLE.getId());
				pstmt.setInt(counter++, ThorbenDierkes.ERROR_LOG_MASSAGE);
				pstmt.setInt(counter++, Ob3ColumDefinitions.DESCRIPTION.getId());
				pstmt.setInt(counter++, ThorbenDierkes.ERROR_LOG_MASSAGE);
				pstmt.setInt(counter++, Ob3ColumDefinitions.CREATION_DATE.getId());
				success &= pstmt.execute();
			}
			con.commit();
			success = true;
		}catch (SQLException e) {
			handleSqlException(e);
		} 
		return success;
	}
	
	public boolean writeOb3Filter() {
		logger.infoLog("Ob3Updates: writeOb3Filter()");
		boolean success = false;
		String sql = "INSERT INTO ob3_filter (`id`, `title`, `description`, `sql_function`) VALUES (?,?,?,?) " + 
						" ON DUPLICATE KEY UPDATE `title` = ?, `description` = ?, `sql_function` = ?;";
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
			try(PreparedStatement pstmt = con.prepareStatement(sql)){
				for(Ob3FilterDefinitions filter : Ob3FilterDefinitions.values()) {
					pstmt.setInt(1, filter.getId());
					pstmt.setString(2, filter.getTitle());
					pstmt.setString(3, filter.getDescription());
					pstmt.setString(4, filter.getSqlFunction());
					pstmt.setString(5, filter.getTitle());
					pstmt.setString(6, filter.getDescription());
					pstmt.setString(7, filter.getSqlFunction());
					pstmt.addBatch();
				}
				pstmt.executeBatch();
				con.commit();
				success = true;
			}
		}catch (SQLException e) {
			handleSqlException(e);
		} 
		return success;
	}

}
