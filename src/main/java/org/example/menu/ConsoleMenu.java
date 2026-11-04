package org.example.menu;

import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.model.Dog;
import org.example.model.Cat;
import org.example.model.Bird;
import org.example.shelter.Shelter;

import java.util.List;
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

            switch (choice) {
                case 1:
                    addAnimalMenu();
                    break;
                case 2:
                    listAllAnimals();
                    break;
                case 3:
                    findBySpeciesMenu();
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

    private void addAnimalMenu() {
        System.out.println("\nAdd Animal:");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Bird");
        System.out.print("Select species: ");
        int speciesChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();

        System.out.print("Enter animal age: ");
        int age = scanner.nextInt();

        switch (speciesChoice) {
            case 1:
                shelter.addAnimal(new Dog(new AnimalId(), name, age));
                System.out.println("Dog added successfully!\n");
                break;
            case 2:
                shelter.addAnimal(new Cat(new AnimalId(), name, age));
                System.out.println("Cat added successfully!\n");
                break;
            case 3:
                shelter.addAnimal(new Bird(new AnimalId(), name, age));
                System.out.println("Bird added successfully!\n");
                break;
            default:
                System.out.println("Invalid species choice. Animal not added.\n");
                break;
        }
    }
}
