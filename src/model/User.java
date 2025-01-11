package model;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private final String login;
    private final String password;
    private double walletBalance = 0.0;
    private final List<Transaction> transactions = new ArrayList<>();
    private final Map<String, Double> budgets = new HashMap<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void addTransaction(String category, double amount, boolean isIncome) {
        transactions.add(new Transaction(category, amount, isIncome));
        walletBalance += isIncome ? amount : -amount;
    }

    public void setBudget(String category, double budget) {
        budgets.put(category, budget);
    }

    public boolean isBudgetExceeded(String category) {
        double totalExpenses = transactions.stream()
                .filter(t -> !t.isIncome() && t.getCategory().equals(category))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return budgets.getOrDefault(category, Double.MAX_VALUE) < totalExpenses;
    }

    public void displayTransactions() {
        System.out.println("История операций:");
        for (Transaction t : transactions) {
            System.out.printf("- Категория: %s | Сумма: %.2f | Тип: %s\n", t.getCategory(), t.getAmount(), t.isIncome() ? "Доход" : "Расход");
        }
    }

    public void displayBudgets() {
        System.out.println("Бюджеты по категориям:");
        for (Map.Entry<String, Double> entry : budgets.entrySet()) {
            System.out.printf("- Категория: %s | Установленный бюджет: %.2f\n", entry.getKey(), entry.getValue());
        }
    }

    public double getWalletBalance() {
        return walletBalance;
    }
}
