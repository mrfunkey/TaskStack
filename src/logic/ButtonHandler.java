package logic;

import swing.TaskList;

public class ButtonHandler {

    public static void addTaskButton(String task, String date){
        Database.newTaskEntryPortal(task, date);
        swing.TaskList.addTask(task, date);
    }
}
