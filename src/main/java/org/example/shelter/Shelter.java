package org.example.shelter;

import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){

        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return animals;
    }

    public List<T> findBySpecies(String species){
        List<T> result = new ArrayList<>();
        for(T animal : animals){
            if(animal.getClass().getSimpleName().equalsIgnoreCase(species)){
                result.add(animal);
            }
        }
        return result;
    }

    public List<T> findAvailableAnimals(){
        // TODO
        return null;
    }

    public void markAsAdopted(String id){
        // TODO
    }
}
