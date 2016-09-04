package com.company;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;

import static java.util.Arrays.asList;

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/09/16.
 */
public class FuncEx {

    public static void main(String[] args) {
//        Integer a = new Integer(1);
//        System.out.println(a + " increment -> " + increment(a));
//        mapLen();
//        mapSquare();
//        mapRandomNames();
//        mapHashNames();
//        reduce();
//        reduceCount();
//        averageHeight();
        carRace();
    }

    private static Integer increment(Integer a) {
        System.out.println("increment");
        return a + 1;
    }

    private static void mapLen() {
        System.out.println("mapLen");
        asList("Pippo", "pluto", "Paperino").stream().map(s -> s.length()).forEach(s -> System.out.println(s));
    }

    private static void mapSquare() {
        System.out.println("mapSquare");
        asList(1, 4, 6, 9).stream().map(x -> x * x).forEach(x -> System.out.println(x));

    }

    private static void mapRandomNames() {
        System.out.println("mapRandomNames");
        List<String> codes = asList("sdvsd", "dsgfwg", "ascasfcas");
        asList("Pippo", "pluto", "Paperino").stream().map(s -> codes.get(new Random().nextInt(3))).forEach(s -> System.out.println(s));
    }

    private static void mapHashNames() {
        System.out.println("mapHashNames");
        asList("Pippo", "pluto", "Paperino").stream().map(s -> s.hashCode()).forEach(s -> System.out.println(s));
    }

    private static void reduce() {
        System.out.println("reduce");
        System.out.println(asList("Pippo", "pluto", "Paperino").stream().reduce((a, x) -> a + x).get());
    }

    private static void reduceCount() {
        System.out.println("reduceCount");
        System.out.println(asList("Pippo as bag", "pluto has tail", "Paperino has bag").stream().mapToInt(x -> StringUtils.countMatches(x, "bag")).sum());
        System.out.println(asList("Pippo as bag", "pluto has bag one and bag two", "Paperino has bag").stream().mapToInt(x -> StringUtils.countMatches(x, "bag")).sum());
    }

    private static void averageHeight() {
        class Person {
            public String name;
            public Integer height;

            public Person(String name, Integer height) {
                this.name = name;
                this.height = height;
            }

        }
        List<Person> persons = asList(new Person("Mary", 160), new Person("Isal", 80), new Person("Sam", null));
        System.out.println(persons
                .stream()
                .filter(p -> p.height != null)
                .mapToInt(p -> p.height)
                .average()
                .getAsDouble());
    }


    private static void carRace() {
        List<Integer> carPositions = asList(1, 1, 1);
        for (int time = 0; time < 5; time++) {
            moveCars(carPositions);
            drawCars(carPositions);
        }
    }

    private static void moveCars(List<Integer> carPositions) {
        carPositions.replaceAll(p -> {
            if (new Random().nextDouble() > 0.3) {
                p += 1;
            }
            return p;
        });
    }

    private static void drawCars(List<Integer> carPositions) {
        System.out.println();
        carPositions
                .stream()
                .forEach(x -> {
                    System.out.println(StringUtils.repeat("-", x));
                });
    }


}
