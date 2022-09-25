package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhoneNumber {
    private final boolean robot;
    private final int phoneNumber;
    private final String name;
    private final People people;
    private final String[] numbers;

    public PhoneNumber(boolean isRobot, int phoneNumber, String name, People people, String[] numbers) {
        this.robot = isRobot;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.people = people;
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "PhoneNumber{"
                + "isRobot=" + robot
                + ", phoneNumber=" + phoneNumber
                + ", name='" + name + '\''
                + ", people=" + people
                + ", numbers=" + Arrays.toString(numbers)
                + '}';
    }

    public boolean isRobot() {
        return robot;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public People getPeople() {
        return people;
    }

    public String[] getNumbers() {
        return numbers;
    }

    public static class People {
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

        public int getPassportNumber() {
            return passportNumber;
        }
    }

    public static void main(String[] args) {
        JSONObject jsonPeople = new JSONObject("{\"passportNumber\":\"51239002\"}");

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        JSONArray jsonNumbers = new JSONArray(list);

        final PhoneNumber phoneNumber = new PhoneNumber(false, 555355, "Mike",
                new People(51239002), new String[] {"1, 2"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isRobot", phoneNumber.isRobot());
        jsonObject.put("phoneNumber", phoneNumber.getPhoneNumber());
        jsonObject.put("name", phoneNumber.getName());
        jsonObject.put("people", jsonPeople);
        jsonObject.put("numbers", jsonNumbers);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(phoneNumber).toString());

    }
}