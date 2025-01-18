package model;

// Класс Transaction представляет транзакцию: доход или расход
public class Transaction {
    private String type; // Тип транзакции: "income" или "expense"
    private double amount; // Сумма транзакции
    private String category; // Категория транзакции

    // Конструктор для инициализации транзакции
    public Transaction(String type, double amount, String category) {
        this.type = type;
        this.amount = amount;
        this.category = category;
    }

    // Геттер для получения типа транзакции
    public String getType() {
        return type;
    }

    // Геттер для получения суммы транзакции
    public double getAmount() {
        return amount;
    }

    // Геттер для получения категории транзакции
    public String getCategory() {
        return category;
    }
}
