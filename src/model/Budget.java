package model;

// Класс Budget представляет бюджет по категориям
public class Budget {
    private String category; // Категория бюджета
    private double limit; // Лимит бюджета

    // Конструктор для инициализации бюджета
    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
    }

    // Геттер для получения категории бюджета
    public String getCategory() {
        return category;
    }

    // Геттер для получения лимита бюджета
    public double getLimit() {
        return limit;
    }

    // Сеттер для изменения лимита бюджета
    public void setLimit(double limit) {
        this.limit = limit;
    }
}
