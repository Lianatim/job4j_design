package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, String> prev = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User el : current) {
            if (!prev.containsKey(el.getId())) {
                rsl.setAdded(rsl.getAdded() + 1);
            } else if (!el.getName().equals(prev.get(el.getId()))) {
                rsl.setChanged(rsl.getChanged() + 1);
                prev.remove(el.getId());
            } else if (el.getName().equals(prev.get(el.getId()))) {
                prev.remove(el.getId());
            }
        }
        rsl.setDeleted(prev.size());
        return rsl;

    }
}