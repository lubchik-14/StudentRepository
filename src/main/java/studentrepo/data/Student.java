package studentrepo.data;

/**
 * Student that is used in DB
 */
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private float scholarship;

    /**
     * Creates an instance of the class
     */
    public Student(int id, String firstName, String lastName, int age, float scholarship) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.scholarship = scholarship;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public float getScholarship() {
        return scholarship;
    }
}
