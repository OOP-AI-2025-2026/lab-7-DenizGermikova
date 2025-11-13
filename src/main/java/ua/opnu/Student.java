package ua.opnu;

import java.util.Arrays;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class Student {

    private final String name;
    private final String group;
    private final int[] marks;

    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    // Перевірка наявності заборгованості
    public boolean hasDebt() {
        for (int mark : marks) {
            if (mark < 60) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + group + ") " + Arrays.toString(marks);
    }
}