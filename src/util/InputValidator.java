package utils;

// Класс InputValidator предоставляет методы для валидации ввода данных
public class InputValidator {
    // Метод для проверки корректности суммы
    public static boolean isValidAmount(double amount) {
       return amount > 0; // Сумма должна быть больше нуля
    }

    // Метод для проверки корректности категории
    public static boolean isValidCategory(String category) {
        return category != null && !category.isEmpty(); // Категория не должна быть пустой
    }
}
