package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class PhoneNumber {
    private final boolean isRobot;
    private final int phoneNumber;
    private final String name;
    private final People people;
    private final String[] numbers;

    public PhoneNumber(boolean isRobot, int phoneNumber, String name, People people, String[] numbers) {
        this.isRobot = isRobot;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.people = people;
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "PhoneNumber{"
                + "isRobot=" + isRobot
                + ", phoneNumber=" + phoneNumber
                + ", name='" + name + '\''
                + ", people=" + people
                + ", numbers=" + Arrays.toString(numbers)
                + '}';
    }

    static class People {
        private final int passportNumber;

        public People(int passportNumber) {
            this.passportNumber = passportNumber;
        }

        @Override
        public String toString() {
            return "People{"
                    + "passportNumber=" + passportNumber
                    + '}';
        }
    }

    public static void main(String[] args) {
        final PhoneNumber phoneNumber = new PhoneNumber(false, 555355, "Mike",
                new People(51239002), new String[] {"1, 2"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(phoneNumber));

        final String phoneNumberJson =
                "{"
                        + "\"isRobot\":false,"
                        + "\"phoneNumber\":555355,"
                        + "\"people\":"
                        + "{"
                        + "\"passportNumber\":\"51239002\""
                        + "},"
                        + "\"numbers\":"
                        + "[\"1\",\"2\"]"
                        + "}";
        final PhoneNumber phoneNumberMod = gson.fromJson(phoneNumberJson, PhoneNumber.class);
        System.out.println(phoneNumberMod);
    }
}
