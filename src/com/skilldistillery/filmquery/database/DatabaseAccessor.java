package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.ActorEntity;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId);

	public ActorEntity findActorById(int actorId) throws SQLException;

	public List<Film> findFilmsByActorId(int actorId);

	public List<ActorEntity> findActorsByFilmId(int filmId);
}
