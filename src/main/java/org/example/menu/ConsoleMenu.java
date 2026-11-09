package org.example.menu;

import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.model.Cat;
import org.example.model.Dog;
import org.example.model.Bird;
import org.example.shelter.Shelter;

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
    }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit
                """);
    }
}
