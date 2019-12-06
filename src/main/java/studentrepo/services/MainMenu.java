package studentrepo.services;

import studentrepo.data.Student;

import java.util.List;
import java.util.Scanner;

/**
 * Class to show a menu
 */
public class MainMenu {
    /**
     * Points of the main menu
     */
    private final String[] MENU = {
            "Create a student table",
            "Delete an existing table",
            "Show existing tables",
            "Add a student",
            "Show sorted data (A-Z)",
            "Search"};
    /**
     * Scanner to read user answers
     */
    private Scanner scanner;
    /**
     * Interlocutor to ask the user and get appropriate answers
     */
    private Interlocutor interlocutor;
    /**
     * Repository to deal with Student
     */
    private IStudentRepository studentRepository;
    /**
     * Printer to print the information
     */
    private IPrinter printer;

    /**
     * Creates an instance of the class
     *
     * @param studentRepository
     * @param printer
     */
    public MainMenu(IStudentRepository studentRepository, IPrinter printer) {
        this.studentRepository = studentRepository;
        this.printer = printer;
        this.scanner = new Scanner(System.in);
        this.interlocutor = new Interlocutor(scanner);
    }

    /**
     * Shows the main menu
     */
    public void start() {
        int answer;
        do {
            printer.printMainMenu(MENU);
            answer = interlocutor.askAndCheckInt("menu item");
            switch (answer) {
                case 1: {
                    createTableMenu();
                    break;
                }
                case 2: {
                    deleteStudentTableMenu();
                    break;
                }
                case 3: {
                    showExistingTablesMenu();
                    break;
                }
                case 4: {
                    addStudentMenu();
                    break;
                }
                case 5: {
                    showSortedDataMenu();
                    break;
                }
                case 6: {
                    searchMenu();
                    break;
                }
            }
        } while (answer != 0);
        studentRepository.stop();
    }

    /**
     * Shows the menu to create a table with students
     */
    private void createTableMenu() {
        System.out.println("Creating a table of students");
        String tableName = interlocutor.askString("table name to create");
        studentRepository.createTable(tableName);
    }

    /**
     * Shows the menu to delete a table
     */
    private void deleteStudentTableMenu() {
        System.out.println("Deleting a table");
        List<String> tables = studentRepository.getAllTables();
        printer.print(tables, "table");
        String tableName = interlocutor.askAndCheckString("table name to delete", tables);
        studentRepository.deleteTable(tableName);
    }

    /**
     * Shows all names of tables
     */
    private void showExistingTablesMenu() {
        printer.print(studentRepository.getAllTables(), "table");
    }

    /**
     * Shows the menu to add a Student
     */
    private void addStudentMenu() {
        System.out.println("Inserting a student");
        List<String> tables = studentRepository.getAllTables();
        printer.print(tables, "table");
        String tableName = interlocutor.askAndCheckString("destined table", tables);
        do {
            int id = interlocutor.askAndCheckInt("id");
            String firstName = interlocutor.askString("first name");
            String lastName = interlocutor.askString("last name");
            int age = interlocutor.askAndCheckInt("age");
            float scholarship = interlocutor.askAndCheckFloat("scholarship");

            Student newStudent = new Student(id, firstName, lastName, age, scholarship);
            studentRepository.update(tableName, newStudent);

            System.out.println("One more student? (yes)");
        } while (scanner.next().toUpperCase().equals("YES"));
    }

    /**
     * Shows the menu to sort and show table data
     */
    private void showSortedDataMenu() {
        System.out.println("Showing data");
        List<String> tables = studentRepository.getAllTables();
        printer.print(tables, "table");
        String tableName = interlocutor.askAndCheckString("table to show data", tables);
        printer.printStudents(studentRepository.getSorted(tableName, "firstName"));
    }

    /**
     * Shows the menu to search text
     */
    private void searchMenu() {
        System.out.println("Search text");
        List<String> tables = studentRepository.getAllTables();
        printer.print(tables, "table");
        String tableName = interlocutor.askAndCheckString("table to search", tables);
        String text = interlocutor.askString("text to search");
        printer.printStudents(studentRepository.search(tableName, text, "firstName"));
    }
}
