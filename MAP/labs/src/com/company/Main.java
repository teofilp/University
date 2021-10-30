package com.company;

import Model.Animal;
import Model.Dog;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        int sum = Arrays.stream(args).map(Integer::parseInt).reduce(0, Integer::sum);
        System.out.println(sum / (double)args.length);
        Animal a = new Dog();
        Animal b = new Dog();
        new Dog();
        new Dog();
//        new Dog();
//        new Dog();
        a.makeSound();

        System.out.println(Animal.instances);
    }
}
