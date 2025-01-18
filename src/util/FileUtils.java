package utils;

import model.User;

import java.io.*; // Импортируем классы для работы с файлом
import java.util.HashMap;

// Класс FileUtils предоставляет методы для работы с файлами
public class FileUtils {
    // Метод для загрузки пользователей из файла
    public static HashMap<String, User> loadUsers(String filename) {
        HashMap<String, User> users = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            users = (HashMap<String, User>) ois.readObject(); // Читаем объекты из файла
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Обработка ошибок
        }
        return users; // Возвращаем загруженных пользователей
    }

    // Метод для сохранения пользователей в файл
    public static void saveUsers(String filename, HashMap<String, User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users); // Записываем объекты в файл
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок
        }
    }
}
