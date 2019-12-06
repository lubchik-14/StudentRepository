package studentrepo.services;

import studentrepo.data.Student;

import java.util.List;

/**
 * Interface of printers to work with menu
 */
public interface IPrinter {
    /**
     * Prints a given string array like a numbered list
     *
     * @param points the given points of menu
     */
    void printMainMenu(String[] points);

    /**
     * Prints a given list of strings
     *
     * @param strings strings to print
     * @param object an object name
     */
    void print(List<String> strings, String object);

    /**
     * Prints a given list of students
     *
     * @param students the given students
     */
    void printStudents(List<Student> students);
}
