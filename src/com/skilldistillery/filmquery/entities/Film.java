package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String descritpion;
	private int release_year;
	private int language_id;
	private int rental_duration;
	private double rental_rate;
	private int length;
	private double replacement_cost;
	private String rating;
	private String special_features;
	private List<ActorEntity> actors;
	
	public Film(int id, String title, String descritpion, int release_year, int language_id, int rental_duration,
			double rental_rate, int length, double replacement_cost, String rating, String special_features) {
		super();
		this.id = id;
		this.title = title;
		this.descritpion = descritpion;
		this.release_year = release_year;
		this.language_id = language_id;
		this.rental_duration = rental_duration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacement_cost = replacement_cost;
		this.rating = rating;
		this.special_features = special_features;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescritpion() {
		return descritpion;
	}
	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}
	public int getRelease_year() {
		return release_year;
	}
	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}
	public int getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}
	public int getRental_duration() {
		return rental_duration;
	}
	public void setRental_duration(int rental_duration) {
		this.rental_duration = rental_duration;
	}
	public double getRental_rate() {
		return rental_rate;
	}
	public void setRental_rate(double rental_rate) {
		this.rental_rate = rental_rate;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getReplacement_cost() {
		return replacement_cost;
	}
	public void setReplacement_cost(double replacement_cost) {
		this.replacement_cost = replacement_cost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecial_features() {
		return special_features;
	}
	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
		
	}
	public List<ActorEntity> getActors() {
		return actors;
	}
	public void setActors(List<ActorEntity> actors) {
		this.actors = actors;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", descritpion=" + descritpion + ", release_year=" + release_year
				+ ", language_id=" + language_id + ", rental_duration=" + rental_duration + ", rental_rate="
				+ rental_rate + ", length=" + length + ", replacement_cost=" + replacement_cost + ", rating=" + rating
				+ ", special_features=" + special_features + ", actors=" + actors + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(actors, descritpion, id, language_id, length, rating, release_year, rental_duration,
				rental_rate, replacement_cost, special_features, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(descritpion, other.descritpion) && id == other.id
				&& language_id == other.language_id && length == other.length && Objects.equals(rating, other.rating)
				&& release_year == other.release_year && rental_duration == other.rental_duration
				&& Double.doubleToLongBits(rental_rate) == Double.doubleToLongBits(other.rental_rate)
				&& Double.doubleToLongBits(replacement_cost) == Double.doubleToLongBits(other.replacement_cost)
				&& Objects.equals(special_features, other.special_features) && Objects.equals(title, other.title);
	}
	
	




}




