package studentrepo.services;

import studentrepo.data.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class to have access to a database
 */
public class StudentRepository implements IStudentRepository {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Creates an instance of the class
     *
     * @param url      a database url of the form jdbc:subprotocol:subname
     * @param user     the database user on whose behalf the connection is being made
     * @param password the user's password
     */
    public StudentRepository(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("While showing data occurred exception : " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createTable(String tableName) {
        StringBuilder builder = new StringBuilder();
        builder.append("id INTEGER, ")
                .append("firstName VARCHAR(50), ")
                .append("lastName VARCHAR(50), ")
                .append("age INTEGER, ")
                .append("scholarship DECIMAL(15,2)");
        String query = String.format("CREATE TABLE %s (%s)", tableName, builder.toString());
        try {
            statement.executeUpdate(query);
            System.out.println("Successful\n");
        } catch (SQLException e) {
            System.err.println("While creating occurred exception : " + e.getMessage());
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteTable(String tableName) {
        String query = String.format("DROP TABLE %s", tableName);
        try {
            statement.executeUpdate(query);
            System.out.println("Successful\n");
        } catch (SQLException e) {
            System.err.println("While deleting occurred exception : " + e.getMessage());
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllTables() {
        List<String> tables = new ArrayList<>();
        String[] types = {"TABLE"};
        try {
            resultSet = connection.getMetaData().getTables("people", null, "%", types);
            while (resultSet.next()) {
                tables.add(resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.err.println("While getting tables occurred exception : " + e.getMessage());
        }
        return tables;
    }

    /**
     * Inserts the given Student to the given table
     *
     * @param tableName  the destined table
     * @param newStudent the Student to insert
     * @return true if successful
     */
    public boolean update(String tableName, Student newStudent) {
        String query = String.format("INSERT INTO %s VALUES(%s, '%s', '%s', %s, %s)",
                tableName, newStudent.getId(), newStudent.getFirstName(), newStudent.getLastName(),
                newStudent.getAge(), newStudent.getScholarship());
        try {
            statement.executeUpdate(query);
            System.out.println("Successful\n");
        } catch (SQLException e) {
            System.err.println("While inserting data occurred exception : " + e.getMessage());
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> getSorted(String tableName, String sortedColumn) {
        String query = String.format("SELECT * FROM %s ORDER BY %s", tableName, sortedColumn);
        return getStudents(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> search(String tableName, String text, String sortedColumn) {
        StringBuilder builder = new StringBuilder();
        getColumns(tableName).forEach(s ->
                builder.append(s)
                        .append(","));
        builder.delete(builder.length() - 1, builder.length());
        String query = String.format("SELECT * FROM %s WHERE CONCAT(%s) LIKE %s%s%s ORDER BY %s",
                tableName, builder.toString(), "'%", text, "%'", sortedColumn);
        return getStudents(query);
    }

    /**
     * Gets records from a student table
     *
     * @param sqlQuery String with query
     * @return existed records from as a List
     */
    private List<Student> getStudents(String sqlQuery) {
        List<Student> students = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                createStudentFromResultSet(resultSet).ifPresent(students::add);
            }
        } catch (SQLException e) {
            System.err.println("Exception while getting ResultSet : " + e.getMessage());
        }
        return students;
    }

    /**
     * Retrieves data to create a list of Students
     *
     * @param resultSet
     * @return retrieved student as a list
     */
    private Optional<Student> createStudentFromResultSet(ResultSet resultSet) {
        try {
            return Optional.of(new Student(resultSet.getInt("id"), resultSet.getString("firstName"),
                    resultSet.getString("lastName"), resultSet.getInt("age"),
                    resultSet.getFloat("scholarship")));
        } catch (SQLException e) {
            System.err.println("Exception while reading ResultSet : " + e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getColumns(String tableName) {
        List<String> columns = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", tableName);
        try {
            resultSet = statement.executeQuery(query);
            ResultSetMetaData data = resultSet.getMetaData();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                columns.add(data.getColumnName(i));
            }
        } catch (SQLException e) {
            System.err.println("While getting tables occurred exception : " + e.getMessage());
        }
        return columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
