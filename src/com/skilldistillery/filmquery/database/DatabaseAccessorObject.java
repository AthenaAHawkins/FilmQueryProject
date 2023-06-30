package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.ActorEntity;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	String user = "student";
	String pass = "student";
	String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		return null;
	}

	@Override
	public ActorEntity findActorById(int actorId) throws SQLException {
		ActorEntity actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";
		//1. connect to DB
		Connection conn = DriverManager.getConnection(URL, user, pass);
		//2. Prepare the SQL statement
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//3. bind the value actorID to the bind variable, ?
		pstmt.setInt(1, actorId);
		//4. run the SQL
		ResultSet actorResult = pstmt.executeQuery();
		//5. get the results
		if (actorResult.next()) {
			actor = new ActorEntity();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
			
			actor.setFilms(findFilmsByActorId(actorId));
			
		}
		actorResult.close();
		pstmt.close();
		conn.close();
		return actor;
	}
	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		  List<Film> films = new ArrayList<>();
		  try {
		    Connection conn = DriverManager.getConnection(URL, user, pass);
		    String sql = "SELECT film.*"
		               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
		               + " WHERE actor_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, actorId);
		    ResultSet rs = stmt.executeQuery();
		    
		    while (rs.next()) {
		      int filmId = rs.getInt("id");
		      String title = rs.getString("title");
		      String desc = rs.getString("description");
		      short releaseYear = rs.getShort("release_year");
		      int langId = rs.getInt("langauage_id");
		      int rentDur = rs.getInt("rental_duration");
		      double rate = rs.getDouble("rental_rate");
		      int length = rs.getInt("length");
		      double repCost = rs.getDouble("replacement_cost");
		      String rating = rs.getString("rating");
		      String features = rs.getString("special_features");
		      Film film = new Film(filmId, title, desc, releaseYear, langId,
		                           rentDur, rate, length, repCost, rating, features);
		      films.add(film);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return films;
		}
	

	@Override
	public List<ActorEntity> findActorsByFilmId(int filmId) {
		List<ActorEntity> actors = new ArrayList<>();
		
		try {
		    Connection conn = DriverManager.getConnection(URL, user, pass);
		    String sql = "SELECT actor.*"
		               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
		               + " JOIN actor ON film_actor.actor_id = actor.id "
		               + " WHERE film_id = ?";
		    PreparedStatement prepstmt = conn.prepareStatement(sql);
		    prepstmt.setInt(1, filmId);
		    ResultSet actsresult = prepstmt.executeQuery();
		    
		    while (actsresult.next()) {
		    	int actorId = actsresult.getInt("id");
		    	String actsFirstNm = actsresult.getString("first_name");
		    	String actsLastNm = actsresult.getString("last_name");
		      ActorEntity actor = new ActorEntity(actorId, actsFirstNm, actsLastNm);
		      actors.add(actor);
		    }
		    actsresult.close();
		    prepstmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return actors;
		}

		
	}
	
	