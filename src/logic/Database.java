package logic;

import swing.TaskList;

import java.sql.*;

public class Database {

    final static String url = "jdbc:sqlite:tasks.db";

    public static void connectPortal(){
        connect();
        createNewTable();
    }

    public static void newTaskEntryPortal(String task, String date){
        newTaskEntry(task, date);
    }

    public static void loadTasksPortal(){
        loadTasks();
    }

    private static void loadTasks(){
        System.out.println("Loading Tasks...");
        String sql = "SELECT task, date FROM tasks";

        TaskList.modelRowReset();

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

    private static void newTaskEntry(String task, String date){
        System.out.println("Creating new task entry...");
        String sql = "INSERT INTO tasks (task, date) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             java.sql.PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, task);
            statement.setString(2, date);
            statement.executeUpdate();
            System.out.println("New task entry has been added");
        } catch (SQLException e) {
            System.out.println("Task entry Failed: " + e.getMessage());
        }
    }

    private static void createNewTable() {
        System.out.println("Creating table...");
        String sql = "CREATE TABLE IF NOT EXISTS tasks(\n"
                + "task TEXT NOT NULL,\n"
                + "date TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             java.sql.Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Created task table");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private static void connect(){
        System.out.println("Connecting to database...");
        try (Connection connection = DriverManager.getConnection(url);){
            if (connection != null){
                System.out.println("Connected to the database");
            }
        }
        catch (Exception e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
