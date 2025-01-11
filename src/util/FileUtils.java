package util;

import model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    public static void saveUsers(Map<String, User> users, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить данные: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, User> loadUsers(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не удалось загрузить данные. Начало с чистого состояния.");
            return new HashMap<>();
        }
    }
}
