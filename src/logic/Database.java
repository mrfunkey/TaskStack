package logic;

import swing.TaskList;

import javax.swing.*;
import java.sql.*;

public class Database {

    final static String url = "jdbc:sqlite:tasks.db";

    public static void connectPortal(){
        connect();
        createNewTable();
        createCompletionTable();
    }

    public static void newTaskEntry(String task, String date){
        System.out.println("Adding task to database...");
        String sql = "INSERT INTO tasks VALUES (?, ?)";
        executeVoidSQL(sql, task, date);
    }

    public static void loadTasks(){
        System.out.println("Loading Tasks...");
        String sql = "SELECT task, date FROM tasks";
        TaskList.modelRowReset();

        loadTasks(sql);
    }

    public static boolean isDatabaseEmpty(){
        System.out.println("Checking if database is empty...");
        String sql = "SELECT COUNT(*) FROM tasks;";
        if (!isDatabaseEmpty(sql)){
            return false;
        }
        return true;
    }

    public static JComboBox createTaskCB(){
        return createTaskComboBox();
    }

    public static void deleteTask(String selectedTask, String deletionType){
        System.out.println("Deleting task from database...");
        String sql = "DELETE FROM tasks WHERE task = ?";

        executeVoidSQL(sql, selectedTask);

        if (deletionType.equals("c")){
            System.out.println("Updating completion counter...");
            sql = "UPDATE completion_counter SET completed = completed + 1 WHERE id = 1";
            executeVoidSQL(sql);
        }
    }

    public static int getTotalCompletedPortal(){
        System.out.println("Getting total completed tasks...");
        String sql = "SELECT completed FROM completion_counter WHERE id = 1";

        return executeIntSQL(sql);
    }

    private static void connect(){
        System.out.println("Connecting to database...");
        try (Connection connection = DriverManager.getConnection(url);){
            if (connection != null){
                System.out.println("Connected to the database.");
            }
        }
        catch (Exception e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private static void createNewTable() {
        System.out.println("Checking if task table exists...");
        String sql = "CREATE TABLE IF NOT EXISTS tasks(\n"
                + "task TEXT NOT NULL,\n"
                + "date TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Finished checking for task table.");
        } catch (SQLException e) {
            System.out.println("Table check failed: " + e.getMessage());
        }
    }

    private static void createCompletionTable(){
        System.out.println("Checking if completion table exists...");
        String sql = "CREATE TABLE IF NOT EXISTS completion_counter(\n"
                + "id INTEGER PRIMARY KEY,\n"
                + "completed INTEGER DEFAULT 0\n"
                + ");";

        String sqlSeed = "INSERT OR IGNORE INTO completion_counter (completed, id) VALUES (0, 1) ";

        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement()){
            statement.execute(sql);
            statement.execute(sqlSeed);
            System.out.println("Finished checking for completion table.");
        }
        catch (Exception e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private static void executeVoidSQL(String sql, Object... params) {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstat = conn.prepareStatement(sql)){

            for (int i = 0; i < params.length; i++){
                pstat.setObject(i + 1, params[i]);
            }

            pstat.executeUpdate();
        }
        catch (Exception e){
            System.out.println("SQL execution failed: " + e.getMessage());
        }
    }

    private static int executeIntSQL(String sql, Object... params) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)){

            if (rs.next()){
                return rs.getInt(1);
            }
        }
        catch (Exception e){
            System.out.println("SQL execution failed: " + e.getMessage());
        }

        return 0;
    }

    private static boolean isDatabaseEmpty(String sql){
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            if (resultSet.next()){
                int count =  resultSet.getInt(1);
                if (count != 0){
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Empty Check Failed: " + e.getMessage());
        }

        return true;
    }

    private static void loadTasks(String sql){
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while(resultSet.next()){
                String task = resultSet.getString("task");
                String date = resultSet.getString("date");

                TaskList.modelRowUpdate(task, date);
            }
        } catch (SQLException e) {
            System.out.println("Task Load Failed: " + e.getMessage());
        }
    }

    private static JComboBox<String> createTaskComboBox(){
        JComboBox<String> taskComboBox = new JComboBox<>();
        String sql = "SELECT task FROM tasks";

        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while(resultSet.next()){
                taskComboBox.addItem(resultSet.getString("task"));
            }
        } catch (SQLException e) {
            System.out.println("Task Load Failed: " + e.getMessage());
        }
        return taskComboBox;
    }
}
