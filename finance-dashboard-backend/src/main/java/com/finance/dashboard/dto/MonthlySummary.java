package com.finance.dashboard.dto;

public class MonthlySummary {

    private String month;
    private Double income;
    private Double expense;

    public MonthlySummary(Integer year, Integer month, Double income, Double expense) {
        // Format month as YYYY-MM
        this.month = year + "-" + (month < 10 ? "0" + month : month);
        this.income = income != null ? income : 0.0;
        this.expense = expense != null ? expense : 0.0;
    }

    // Getters and setters
    public String getMonth() { return month; }
    public Double getIncome() { return income; }
    public Double getExpense() { return expense; }

    public void setMonth(String month) { this.month = month; }
    public void setIncome(Double income) { this.income = income; }
    public void setExpense(Double expense) { this.expense = expense; }
}