package studentrepo.services;

import studentrepo.data.Student;

import java.util.List;

/**
 * Interface of repository to have access to a database with Student instance
 */
public interface IStudentRepository {
    /**
     * Creates a table with a given name
     *
     * @param tableName the name to create a table
     * @return true if success
     */
    boolean createTable(String tableName);

    /**
     * Update a table with a given table name
     *
     * @param tableName the name of the destined table to update
     * @param newStudent the Student to update the table
     * @return true if success
     */
    boolean update(String tableName, Student newStudent);

    /**
     * Deletes a table with a given table name
     *
     * @param tableName the name of table to delete
     * @return true if success
     */
    boolean deleteTable(String tableName);

    /**
     * Returns all a given table records sorted by a given column
     *
     * @param tableName the name of table to sort
     * @param columnName the name of the column to sort
     * @return list of Students sorted by the given column
     */
    List<Student> getSorted(String tableName, String columnName);

    /**
     * Returns a list of Students that nave matches by a given text in any fields. The result is sorted by a given column
     *
     * @param tableName the name of table to search
     * @param text the string to search
     * @param columnName the name of the column to sort
     * @return list of Students with the given text in any fields sorted by the given column
     */
    List<Student> search(String tableName, String text, String columnName);

    /**
     * Returns a list of column names of a a given table
     *
     * @param tableName the name of the table
     * @return list of column names
     */
    List<String> getColumns(String tableName);

    /**
     * Returns a list of table names of a database
     *
     * @return table names
     */
    List<String> getAllTables();

    /**
     * Close all of connection
     */
    void stop();
}

