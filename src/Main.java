import java.util.Scanner;
import model.User;
import service.UserService;
import service.FinanceService;
import model.Transaction;
import model.Budget;
import utils.FileUtils;

// Основной класс приложения
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService(); // Создаем объект UserService
        String filename = "users.dat"; // Имя файла для хранения пользователей
        userService.loadUsers(filename); // Загружаем пользователей из файла

        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для чтения ввода
        String command; // Переменная для хранения команды
        User loggedUser = null; // Переменная для хранения текущего пользователя

        // Основной цикл приложения
        while (true) {
            // Выводим доступные команды в зависимости от состояния пользователя
            if (loggedUser == null) {
                System.out.println("Введите команду: register, login или exit");
            } else {
                System.out.println("Введите команду: addTransaction, addBudget, showStats или logout");
            }
            command = scanner.nextLine(); // Читаем ввод пользователя

            // Обработка команды 'exit' для завершения работы программы
            if (command.equalsIgnoreCase("exit")) {
                FileUtils.saveUsers(filename, userService.getUsers()); // Сохраняем пользователей перед выходом
                break;
            } 
            // Регистрация нового пользователя
            else if (command.equalsIgnoreCase("register") && loggedUser == null) {
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                if (userService.register(login, password)) {
                    System.out.println("Регистрация успешна.");
                } else {
                    System.out.println("Пользователь уже существует.");
                }
            } 
            // Авторизация пользователя
            else if (command.equalsIgnoreCase("login") && loggedUser == null) {
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                loggedUser = userService.login(login, password);
                if (loggedUser != null) {
                    System.out.println("Авторизация успешна.");
                } else {
                    System.out.println("Неверные логин или пароль.");
                }
            } 
            // Выход из системы
            else if (command.equalsIgnoreCase("logout") && loggedUser != null) {
                loggedUser = null; // Сбрасываем текущего пользователя
                System.out.println("Вы вышли из системы.");
            } 
            // Добавление новой транзакции
            else if (command.equalsIgnoreCase("addTransaction") && loggedUser != null) {
                System.out.println("Введите тип (income/expense):");
                String type = scanner.nextLine();
                System.out.println("Введите сумму:");
                double amount = Double.parseDouble(scanner.nextLine());
                System.out.println("Введите категорию:");
                String category = scanner.nextLine();
                if (InputValidator.isValidAmount(amount) && InputValidator.isValidCategory(category)) {
                    Transaction transaction = new Transaction(type, amount, category);
                    FinanceService financeService = new FinanceService(loggedUser.getWallet());
                  
                    financeService.addTransaction(transaction); // Добавляем транзакцию в кошелек
                    System.out.println("Транзакция добавлена.");
                } else {
                    System.out.println("Некорректные данные.");
                }
            } 
            // Добавление нового бюджета
            else if (command.equalsIgnoreCase("addBudget") && loggedUser != null) {
                System.out.println("Введите категорию:");
                String category = scanner.nextLine();
                System.out.println("Введите лимит:");
                double limit = Double.parseDouble(scanner.nextLine());
                Budget budget = new Budget(category, limit);
                FinanceService financeService = new FinanceService(loggedUser.getWallet());
                financeService.addBudget(budget); // Добавляем бюджет в кошелек
                System.out.println("Бюджет добавлен.");
            } 
            // Показ статистики
            else if (command.equalsIgnoreCase("showStats") && loggedUser != null) {
                FinanceService financeService = new FinanceService(loggedUser.getWallet());
                System.out.println("Общий доход: " + financeService.getTotalIncome());
                System.out.println("Общие расходы: " + financeService.getTotalExpenses());
            } 
            // Неизвестная команда
            else {
                System.out.println("Неверная команда.");
            }
        }
        scanner.close(); // Закрываем сканер
    }
}
