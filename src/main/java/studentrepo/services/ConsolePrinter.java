package studentrepo.services;

import studentrepo.data.Student;

import java.util.List;

/**
 * Console Printer to prints information to console
 */
public class ConsolePrinter implements IPrinter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void printMainMenu(String[] points) {
        for (int i = 1; i <= points.length; i++) {
            System.out.println(i + " : " + points[i - 1]);
        }
        System.out.println("\n0 : Exit\n");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print(List<String> strings, String object) {
        System.out.println(object.substring(0, 1).toUpperCase() + object.substring(1).toLowerCase() + "s");
        strings.forEach(System.out::println);
        System.out.println();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printStudents(List<Student> students) {
        System.out.println("Students");
        System.out.printf("%-5s|%-20s|%-20s|%-10s|%-15s\n", "id", "First Name", "Last Name", "Age", "Scholarship");
        students.forEach(s -> System.out.printf("%-5s|%-20s|%-20s|%-10s|%-15.2f\n",
                s.getId(), s.getFirstName(), s.getLastName(), s.getAge(), s.getScholarship()));
        System.out.println();

    }
}
