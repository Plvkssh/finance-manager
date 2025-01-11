package main;

import model.User;
import util.FileUtils;

import java.util.*;

public class FinanceManager {
    private static Map<String, User> users = new HashMap<>();
    private static User loggedUser = null;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "finance_data.txt";

    public static void main(String[] args) {
        users = FileUtils.loadUsers(DATA_FILE);
        System.out.println("Добро пожаловать в управление финансами!");

        while (true) {
            if (loggedUser == null) {
                System.out.println("1. Регистрация\n2. Вход\n3. Выход");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        registerUser();
                        break;
                    case "2":
                        loginUser();
                        break;
                    case "3":
                        FileUtils.saveUsers(users, DATA_FILE);
                        System.out.println("Приложение завершено.");
                        return;
                    default:
                        System.out.println("Неверный ввод. Попробуйте снова.");
                }
            } else {
                System.out.println("1. Добавить доход\n2. Добавить расход\n3. Показать баланс\n4. Установить бюджет\n5. Показать бюджет\n6. Выйти");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        addTransaction(true);
                        break;
                    case "2":
                        addTransaction(false);
                        break;
                    case "3":
                        displayBalance();
                        break;
                    case "4":
                        setBudget();
                        break;
                    case "5":
                        displayBudget();
                        break;
                    case "6":
                        loggedUser = null;
                        System.out.println("Вы вышли из аккаунта.");
                        break;
                    default:
                        System.out.println("Неверный ввод. Попробуйте снова.");
                }
            }
        }
    }

    private static void registerUser() {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        if (users.containsKey(login)) {
            System.out.println("Пользователь с таким логином уже существует.");
        } else {
            users.put(login, new User(login, password));
            System.out.println("Регистрация успешна!");
        }
    }

    private static void loginUser() {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        User user = users.get(login);
        if (user != null && user.getPassword().equals(password)) {
            loggedUser = user;
            System.out.println("Вход выполнен успешно.");
        } else {
            System.out.println("Неверный логин или пароль.");
        }
    }

    private static void addTransaction(boolean isIncome) {
        System.out.println("Введите категорию:");

        String category = scanner.nextLine();
        System.out.println("Введите сумму:");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Попробуйте снова.");
            return;
        }

        loggedUser.addTransaction(category, amount, isIncome);
        System.out.println(isIncome ? "Доход добавлен." : "Расход добавлен.");

        if (!isIncome && loggedUser.isBudgetExceeded(category)) {
            System.out.println("⚠️ Внимание! Вы превысили бюджет по категории: " + category);
        }
    }

    private static void displayBalance() {
        System.out.println("Общий баланс: " + loggedUser.getWalletBalance());
        loggedUser.displayTransactions();
    }

    private static void setBudget() {
        System.out.println("Введите категорию:");
        String category = scanner.nextLine();
        System.out.println("Введите бюджет:");
        double budget;
        try {
            budget = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Попробуйте снова.");
            return;
        }

        loggedUser.setBudget(category, budget);
        System.out.println("Бюджет установлен.");
    }

    private static void displayBudget() {
        loggedUser.displayBudgets();
    }
}
