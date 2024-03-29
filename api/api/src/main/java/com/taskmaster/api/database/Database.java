package com.taskmaster.api.database;

import com.taskmaster.api.models.Status;
import com.taskmaster.api.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Java representation of the database.
 */
public class Database {

    public static boolean createTask(Task taskToCreate, Connection connection) {

        String hql = "INSERT INTO tasks (name, description, due_date, status, user_id) VALUES (?, ?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(hql)) {
            statement.setString(1, taskToCreate.getName());
            statement.setString(2, taskToCreate.getDescription());
            statement.setLong(3, taskToCreate.getDate());
            statement.setString(4, taskToCreate.getStatus().toString());
            statement.setString(5, taskToCreate.getUserId().toString());
            statement.executeUpdate();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }

    public static Task update(long id, String name, String desc, long dueDate, String status, UUID userId, Connection connection) {
        String hql = "UPDATE tasks SET name=?, description=?, due_date=?, status=? WHERE id=?";

        try(PreparedStatement statement = connection.prepareStatement(hql)) {
            statement.setString(1, name);
            statement.setString(2, desc);
            statement.setLong(3, dueDate);
            statement.setString(4, status);
            statement.setLong(5, id);
            statement.executeUpdate();
        }
        catch (Exception ex) {
           throw new RuntimeException(ex);
        }

        return new Task(name, desc, dueDate, Status.valueOf(status), userId);
    }

    public static List<Task> getTasks(UUID userId, Connection connection) {
        String sql = "SELECT * FROM tasks WHERE user_id = ?;";
        List<Task> tasks = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId.toString());
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                tasks.add(new Task(set.getLong("id"), set.getString("name"), set.getString("description"), set.getLong("due_date"), Status.valueOf(set.getString("status")), userId));
            }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return tasks;
    }

    public static boolean delete(long id, Connection connection) {
        String sql = "DELETE FROM tasks WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }
}
