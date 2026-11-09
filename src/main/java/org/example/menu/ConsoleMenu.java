package org.example.menu;

import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.model.Cat;
import org.example.model.Dog;
import org.example.model.Dog;
import org.example.model.Cat;
import org.example.model.Bird;
import org.example.shelter.Shelter;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final Shelter<Animal> shelter;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");

            // Exception checker for scanner.nextInt()
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Consume invalid token
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Switch expression for handling menu options
            switch (choice) {
                case 1 -> handleAddAnimal();
                case 2 -> handleListAllAnimals();
                case 3 -> handleFindBySpecies();
                case 4 -> handleListAvailableAnimals();
                case 5 -> handleMarkAsAdopted();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                }
                default -> System.out.println("Invalid option! Please choose between 0 and 5.");
            }
            System.out.println();
        }
    }

    private void handleAddAnimal() {
        System.out.print("Enter species (Dog, Cat, Bird): ");
        String species = scanner.nextLine().trim();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid age! Please enter a number.");
            scanner.next();
        }
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Using switch for animal creation chain
        Animal animal = switch (species.toLowerCase()) {
            case "dog" -> new Dog(new AnimalId(), name, age);
            case "cat" -> new Cat(new AnimalId(), name, age);
            case "bird" -> new Bird(new AnimalId(), name, age);
            default -> null;
        };

        if (animal != null) {
            shelter.addAnimal(animal);
            System.out.println("Animal added successfully!");
        } else {
            System.out.println("Unknown species! Animal not added.");
        }
    }

    private void handleListAllAnimals() {
        var animals = shelter.getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals in the shelter.");
        } else {
            animals.forEach(System.out::println);
        }
    }

    private void handleFindBySpecies() {
        System.out.print("Enter species to search (Dog, Cat, Bird): ");
        String species = scanner.nextLine();
        var animals = shelter.findBySpecies(species);
        if (animals.isEmpty()) {
            System.out.println("No animals found for species: " + species);
        } else {
            animals.forEach(System.out::println);
        }
    }

    private void handleListAvailableAnimals() {
        var animals = shelter.findAvailableAnimals();
        if (animals.isEmpty()) {
            System.out.println("No available animals.");
        } else {
            animals.forEach(System.out::println);
        }
    }

    private void handleMarkAsAdopted() {
        System.out.print("Enter animal ID to mark as adopted: ");
        String id = scanner.nextLine();
        shelter.markAsAdopted(id);
        System.out.println("Action executed (if ID existed, animal is now adopted).");
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

    private void findBySpeciesMenu() {
        System.out.println("\nFind by Species");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Bird");
        System.out.print("Select species to search: ");
        int choice = scanner.nextInt();

        String speciesName = "";
        if (choice == 1) speciesName = "Dog";
        else if (choice == 2) speciesName = "Cat";
        else if (choice == 3) speciesName = "Bird";
        else {
            System.out.println("Invalid choice!\n");
            return;
        }

        List<Animal> found = shelter.findBySpecies(speciesName);
        System.out.println("\nResults for " + speciesName);
        if (found.isEmpty()) {
            System.out.println("No " + speciesName.toLowerCase() + "s found.\n");
        } else {
            for (Animal animal : found) {
                System.out.println(animal);
            }
            System.out.println();
        }
    }

    private void listAllAnimals() {
        System.out.println("\nAll Animals in Shelter");
        List<Animal> all = shelter.getAllAnimals();
        if (all.isEmpty()) {
            System.out.println("No animals in the shelter yet.\n");
        } else {
            for (Animal animal : all) {
                System.out.println(animal);
            }
            System.out.println();
        }
    }
}

