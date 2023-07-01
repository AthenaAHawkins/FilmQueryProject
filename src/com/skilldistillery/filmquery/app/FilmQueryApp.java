package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	private Scanner input = new Scanner(System.in);

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//    List<Actor> actors = db.findActorsByFilmId(1);
//    for (Actor actor : actors) {
//		System.out.println(actor);
//	}

//  }

	private void launch() throws SQLException {
		startUserInterface(input);
		input.close();

	}

	private void startUserInterface(Scanner input) throws SQLException {
		menu();
		boolean isRunning = true;
		while (isRunning) {
			int choice = input.nextInt();
			
			if (choice == 1) {
				System.out.println("Please enter the film ID: ");
				int filmIdselect = input.nextInt();
				db.findFilmById(filmIdselect);
				
				
			} else if (choice == 2) {
				System.out.println();
				System.out.println("Please enter a keyword");
				String keyWordSelect = input.next();
				if (db.findByKeyWord(keyWordSelect).size() == 0 ) {
					System.out.println("Thats so fetch stop trying to make " + keyWordSelect + 
							" happen, its not going to happen");
				}else {
				db.findByKeyWord(keyWordSelect);
				}

			} else if (choice == 3) {
				System.out.println("Goodbye");
				isRunning = false;
				break;
			}
			
			else {
				System.out.println("Invalid input try again");
			}
			System.out.println("Do you want to continue? (Y/N)");
			System.out.println("If yes: ");
			System.out.println("1. Look up a film by its ID");
			System.out.println("2. Look up a film by a search keyword.");
			String continueChoice = input.next();
			if (!continueChoice.equalsIgnoreCase("Y")) {
				System.out.println("goodbye");
				isRunning = false;
			}
		}
	}

	private void menu() {
		System.out.println("Select from the following options");
		System.out.println("");
		System.out.println("1. Look up a film by its ID");
		System.out.println("2. Look up a film by a search keyword.");
		System.out.println("3. Exit the application.");
	}

}
