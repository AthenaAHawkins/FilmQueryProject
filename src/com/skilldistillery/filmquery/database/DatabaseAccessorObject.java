package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
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
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String sql = "SELECT title, release_year, rating, description FROM film WHERE id = ?";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, filmId);
		ResultSet filmResult = pstmt.executeQuery();

		if (filmResult.next()) {
			film = new Film();
			film.setId(filmId);
			film.setTitle(filmResult.getString("title"));
			film.setRelease_year(filmResult.getInt("release_year"));
			film.setRating(filmResult.getString("rating"));
			film.setDescription(filmResult.getString("description"));

			film.setActors(findActorsByFilmId(filmId));
			System.out.println();

		}
		filmResult.close();
		pstmt.close();
		conn.close();
		
		if (film != null) {
			System.out.println("Film ID: " + film.getId());
			System.out.println("Movie title: " + film.getTitle());
			System.out.println("Release year: " + film.getRelease_year());
			System.out.println("Movie rating: " + film.getRating());
			System.out.println("Movie description: " + film.getDescription());
		} else {
			System.out.println("I'm sorry but I can't look up something that doesn't exist... ");
		}
		
		
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";
		// 1. connect to DB
		Connection conn = DriverManager.getConnection(URL, user, pass);
		// 2. Prepare the SQL statement
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 3. bind the value actorID to the bind variable, ?
		pstmt.setInt(1, actorId);
		// 4. run the SQL
		ResultSet actorResult = pstmt.executeQuery();
		// 5. get the results
		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));

//			actor.setFilms(findFilmsByActorId(actorId));

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
			String sql = "SELECT film.*" + " FROM film JOIN film_actor ON film.id = film_actor.film_id "
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
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
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
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.*" + " FROM film JOIN film_actor ON film.id = film_actor.film_id "
					+ " JOIN actor ON film_actor.actor_id = actor.id " + " WHERE film_id = ?";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setInt(1, filmId);
			ResultSet actorsresult = prepstmt.executeQuery();

			while (actorsresult.next()) {
				int actorId = actorsresult.getInt("id");
				String actsFirstNm = actorsresult.getString("first_name");
				String actsLastNm = actorsresult.getString("last_name");
				Actor actor = new Actor(actorId, actsFirstNm, actsLastNm);
				actors.add(actor);
			}
			actorsresult.close();
			prepstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findByKeyWord(String keyWord) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.* , language.name FROM film Join language ON film.language_id = language.id WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, "%" + keyWord + "%");
			prepstmt.setString(2, "%" + keyWord + "%");
			ResultSet filmresult = prepstmt.executeQuery();

			while (filmresult.next()) {

				int id = filmresult.getInt("id");
				String filmName = filmresult.getString("title");
				String description = filmresult.getString("description");
				int released = filmresult.getInt("release_year");
				String language = filmresult.getString("name");
				String rating = filmresult.getString("rating");
				Film film = new Film(id,filmName, description,released,language,rating);
				film.setActors(findActorsByFilmId(id));
				
				
				
				films.add(film);
			}
			System.out.println(films);
			filmresult.close();
			prepstmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

}
