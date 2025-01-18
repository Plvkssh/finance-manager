package model;

import java.util.ArrayList;
import java.util.List;

// Класс Wallet представляет кошелек пользователя, содержащий транзакции и бюджеты
public class Wallet {
    private List<Transaction> transactions;  // Список транзакций
    private List<Budget> budgets;  // Список бюджетов

    // Конструктор для инициализации кошелька с пустыми списками транзакций и бюджетов
    public Wallet() {
        transactions = new ArrayList<>();
        budgets = new ArrayList<>();
    }

    // Метод для добавления новой транзакции
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Метод для добавления нового бюджета
    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    // Геттер для получения списка транзакций
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Геттер для получения списка бюджетов
    public List<Budget> getBudgets() {
        return budgets;
    }
}
