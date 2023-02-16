package by.moskalyuk;

import by.moskalyuk.model.Animal;
import by.moskalyuk.model.Car;
import by.moskalyuk.model.Flower;
import by.moskalyuk.model.House;
import by.moskalyuk.model.Person;
import by.moskalyuk.util.Util;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        System.out.println("------------------1");
        task2();
        System.out.println("------------------2");
        task3();
        System.out.println("------------------3");
        task4();
        System.out.println("------------------4");
        task5();
        System.out.println("------------------5");
        task6();
        System.out.println("------------------6");
        task7();
        System.out.println("------------------7");
        task8();
        System.out.println("------------------8");
        task9();
        System.out.println("------------------9");
        task10();
        System.out.println("------------------10");
        task11();
        System.out.println("------------------11");
        task12();
        System.out.println("------------------12");
        task13();
        System.out.println("------------------13");
        task14();
        System.out.println("------------------14");
        task15();
        System.out.println("------------------15");
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        AtomicInteger index = new AtomicInteger(0);
        animals.stream()
                .filter(animal -> animal.getAge()>10 && animal.getAge()<20)
                .sorted(Comparator.comparingInt(Animal::getAge))
//                .skip(14)
//                .limit(7)
                .collect(Collectors.groupingBy(zoo->index.getAndIncrement() / 7 ))
                .getOrDefault(2,null)
                .forEach(System.out::println);

    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese") && animal.getGender().equals("Female"))
                .map(animal -> animal.getBread().toUpperCase())
                .forEach(System.out::println);

    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge()>30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin->origin.charAt(0)==('A'))
                .forEach(System.out::println);

    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count());

    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                .filter(animal -> animal.getAge()>20 && animal.getAge()<30)
                .anyMatch(animal -> animal.getOrigin().equals("Hungarian")));
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .allMatch(animal -> animal.getGender().equals("Male") && animal.getGender().equals("Female")));
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .noneMatch(animal -> animal.getOrigin().equals("Oceania")));
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparingInt(Animal::getAge)));
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(value -> value.length)));
        }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .mapToInt(Animal::getAge)
                .sum());
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average());
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears()>18)
                .filter(person->Period.between(person.getDateOfBirth(), LocalDate.now()).getYears()<27)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry(house.getBuildingType().equals("Hospital")? 1 :
                                Period.between(person.getDateOfBirth(), LocalDate.now()).getYears()<18 &&
                                Period.between(person.getDateOfBirth(), LocalDate.now()).getYears()>60? 2:3 , person))
                        .sorted(Map.Entry.comparingByKey()))
                .limit(500)
                .map(Map.Entry::getValue)
                .forEach(System.out::println);



    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();

        DoubleStream costs = Stream.of(
                        Map.entry(1,
                                cars.stream()
                                        .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                        ),
                        Map.entry(2,
                                cars.stream()
                                        .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                                        .filter(car -> car.getMass() < 1500 || car.getCarMake().equals("BMW")
                                        || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler")
                                        || car.getCarMake().equals("Toyota"))

                        ),
                        Map.entry(3,
                                cars.stream()
                                        .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                                        .filter(car -> car.getMass() < 1500 || car.getCarMake().equals("BMW")
                                                || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler")
                                                || car.getCarMake().equals("Toyota"))
                                        .filter(car -> car.getColor().equals("Black"))
                                        .filter(car -> car.getMass() > 4000)
                                        .filter(car -> car.getCarMake().equals("GMC"))
                                        .filter(car -> car.getCarMake().equals("Dodge"))
                        ),
                        Map.entry(4,
                                cars.stream()
                                        .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                                        .filter(car -> car.getMass() < 1500 || car.getCarMake().equals("BMW")
                                                || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler")
                                                || car.getCarMake().equals("Toyota"))
                                        .filter(car -> car.getColor().equals("Black"))
                                        .filter(car -> car.getMass() > 4000)
                                        .filter(car -> car.getCarMake().equals("GMC"))
                                        .filter(car -> car.getCarMake().equals("Dodge"))
                                        .filter(car -> car.getReleaseYear() < 1982 || car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee"))

                        ),
                        Map.entry(5,
                                cars.stream()
                                        .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                                        .filter(car -> car.getMass() < 1500 || car.getCarMake().equals("BMW")
                                                || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler")
                                                || car.getCarMake().equals("Toyota"))
                                        .filter(car -> car.getColor().equals("Black"))
                                        .filter(car -> car.getMass() > 4000)
                                        .filter(car -> car.getCarMake().equals("GMC"))
                                        .filter(car -> car.getCarMake().equals("Dodge"))
                                        .filter(car -> car.getReleaseYear() < 1982 || car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee"))
                                        .filter(car -> !car.getColor().equals("Yellow"))
                                        .filter(car -> !car.getColor().equals("Red"))
                                        .filter(car -> !car.getColor().equals("Green"))
                                        .filter(car -> !car.getColor().equals("Blue"))
                                        .filter(car -> car.getPrice() > 4000)
                        ),
                        Map.entry(6,
                                cars.stream()
                                        .filter(car -> car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                                        .filter(car -> car.getMass() < 1500 || car.getCarMake().equals("BMW")
                                                || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler")
                                                || car.getCarMake().equals("Toyota"))
                                        .filter(car -> car.getColor().equals("Black"))
                                        .filter(car -> car.getMass() > 4000)
                                        .filter(car -> car.getCarMake().equals("GMC"))
                                        .filter(car -> car.getCarMake().equals("Dodge"))
                                        .filter(car -> car.getReleaseYear() < 1982 || car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee"))
                                        .filter(car -> !car.getColor().equals("Yellow"))
                                        .filter(car -> !car.getColor().equals("Red"))
                                        .filter(car -> !car.getColor().equals("Green"))
                                        .filter(car -> !car.getColor().equals("Blue"))
                                        .filter(car -> car.getPrice() > 4000)
                                        .filter(car -> car.getVin().contains("59"))
                        ))
                .mapToInt(entry -> entry.getValue()
                        .mapToInt(Car::getMass)
                        .sum()
                )
                .mapToDouble(value-> value * 0.001 * 7.14)
                .peek(System.out::println);
        double sum = costs.sum();
        System.out.println(cars.stream()
                .mapToInt(Car::getPrice)
                .sum() - sum);

    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        System.out.println(flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparingInt(Flower::getPrice)
                        .thenComparing(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> flower.getCommonName().charAt(0) <= 'C' || flower.getCommonName().charAt(0)>='S')
                .filter(Flower::isShadePreferred)
                .filter(flower -> flower.getFlowerVaseMaterial().stream()
                        .anyMatch(vase-> vase.equals("Aluminum") || vase.equals("Glass") || vase.equals("Steel")))
                .mapToDouble(flower -> flower.getPrice() + (flower.getWaterConsumptionPerDay() * 1826 * 0.001 * 1.39))
                .sum());

    }
}