package service;

import model.Transaction; // Импортируем класс Transaction
import model.Wallet; // Импортируем класс Wallet
import model.Budget; // Импортируем класс Budget

// Класс FinanceService содержит логику управления финансами
public class FinanceService {
    private Wallet wallet; // Кошелек, с которым будет работать сервис

    // Конструктор принимает кошелек как параметр
    public FinanceService(Wallet wallet) {
        this.wallet = wallet;
    }

    // Метод для добавления транзакции в кошелек
    public void addTransaction(Transaction transaction) {
        wallet.addTransaction(transaction);
    }

    // Метод для расчета общего дохода
    public double getTotalIncome() {
        return wallet.getTransactions().stream()
                .filter(t -> t.getType().equals("income"))  // Фильтруем только доходы
                .mapToDouble(Transaction::getAmount)  // Преобразуем в сумму
                .sum();  // Суммируем
    }

    // Метод для расчета общих расходов
    public double getTotalExpenses() {
        return wallet.getTransactions().stream()
                .filter(t -> t.getType().equals("expense"))  // Фильтруем только расходы
                .mapToDouble(Transaction::getAmount)  // Преобразуем в сумму
                .sum();  // Суммируем
    }

    // Метод для добавления бюджета в кошелек
    public void addBudget(Budget budget) {
        wallet.addBudget(budget);
    }
}
    private static void displayBudget() {
        loggedUser.displayBudgets();
    }
}
