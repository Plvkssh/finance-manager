package service;

import model.User; // Импортируем класс User
import java.util.HashMap; // Импортируем класс HashMap для хранения пользователей

// Класс UserService управляет пользователями: регистрация и авторизация
public class UserService {
    private HashMap<String, User> users = new HashMap<>(); // Хранилище пользователей по логину

    // Метод для регистрации нового пользователя
    public boolean register(String login, String password) {
        if(!users.containsKey(login)) { // Проверяем, существует ли пользователь с таким логином
            users.put(login, new User(login, password)); // Если нет, добавляем нового пользователя
            return true; // Регистрация успешна
        }
        return false; // Пользователь уже существует
    }

    // Метод для авторизации пользователя
    public User login(String login, String password) {
        User user = users.get(login); // Получаем пользователя по логину
        if (user != null && user.getPassword().equals(password)) { // Проверяем пароль
            return user; // Авторизация успешна
        }
        return null; // Неверные логин или пароль
    }
}
