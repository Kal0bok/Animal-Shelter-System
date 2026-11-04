package org.example.menu;

import org.example.model.Animal;
import org.example.shelter.Shelter;

import java.util.Scanner;

public class ConsoleMenu {

    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){
        while(true){
            int choice = printMenu();

            if(choice == 0){
                System.out.println("Exit the programm...");
                break;
            }

            switch (choice){
                case 1:
                    System.out.println("Add animal");
                    break;
                case 2:
                    System.out.println("Animal list");
                    break;
                case 3:
                    System.out.println("Search by species");
                    break;
                default: System.out.println("Incorrect input! Try again!");
                break;
            }
        }
        // TODO:
        // Show menu in a loop
        // Read user input
        // Call correct 'Shelter' methods based on selected option
    }
    private int printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit
                """);
        System.out.println("Choose an option: ");

        return scanner.nextInt();
    }
}
