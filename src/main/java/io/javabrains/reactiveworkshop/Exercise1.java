package io.javabrains.reactiveworkshop;

import java.util.List;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        StreamSources.intNumbersStream().forEach(e -> System.out.print(e + " "));
        //StreamSources.intNumbersStream().forEach(System.out::println); //Method Reference

        System.out.println("\n-------Less than 5 ---------");
        // Print numbers from intNumbersStream that are less than 5
        StreamSources.intNumbersStream().filter(e -> e < 5).forEach(System.out::println);

        System.out.println("\n-------Print 2nd and 3rd number greater than 5 ---------");
        // Print the second and third numbers in intNumbersStream that's greater than 5
        StreamSources.intNumbersStream().filter(e -> e > 5).limit(3).skip(1).forEach(System.out::println);

        System.out.println("\n-------Get 1st number greater than 5 else -1 ---------");
        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        int Value = StreamSources.intNumbersStream()
                .filter(e -> e > 5)
                .findFirst().orElse(-1);

        System.out.println("\n-------Print first names ---------");
        // Print first names of all users in userStream
        //StreamSources.userStream().forEach(user -> System.out.print(user.getFirstName() + " "));
        StreamSources.userStream()
                .map(user -> user.getFirstName())
                .forEach(username -> System.out.print(username + " "));

        System.out.println("\n-------Print first names for users with IDs in number stream ---------");
        // Print first names in userStream for users that have IDs from number stream
        final List<Integer> numbers = StreamSources.intNumbersStream().toList();
        StreamSources.userStream()
                .filter(user -> numbers.contains(user.getId()))
                .forEach(user -> System.out.println(user.getFirstName() + " " + user.getId()));

        StreamSources.intNumbersStream()
                //Flatten stream to apply function on single element and not stream
                .flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id))
                .map(user -> user.getFirstName())
                .forEach(firstname -> System.out.print(firstname + " "));

        System.out.println("\n");
        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(e -> e == user.getId()))
                .forEach(user -> System.out.println(user.getFirstName() + " " + user.getId()));

    }

}
