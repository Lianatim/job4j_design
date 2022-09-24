package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        double weight = 332.0;
        float ccal = 34.5e+33f;
        long size = 100000;
        char sex = 'm';
        boolean isWorking = false;
        byte file = 22;
        short number = 55;
        LOG.debug("User info age : {}, weight : {}, ccal : {}, size : {}, sex : {}, isWorking : {}, file : {}, number : {}",
                age, weight, ccal, size, sex, isWorking, file, number);
    }
}