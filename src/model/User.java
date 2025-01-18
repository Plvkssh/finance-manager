package model;

// Класс User представляет пользователя с логином, паролем и кошельком
public class User {
    private String login;  // Логин пользователя
    private String password;  // Пароль пользователя
    private Wallet wallet;  // Кошелек пользователя

    // Конструктор для создания нового пользователя
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.wallet = new Wallet();  // Инициализируем новый кошелек пользователя
    }

    // Геттер для получения логина
    public String getLogin() {
        return login;
    }

    // Геттер для получения пароля
    public String getPassword() {
        return password;
    }

    // Геттер для получения кошелька
    public Wallet getWallet() {
        return wallet;
    }
}
