package com.finance.dashboard.dto;

import java.time.LocalDate;

public class RecentRecordDTO {

    private Long id;
    private Double amount;
    private String type;
    private String category;
    private LocalDate date;
    private String description;
    private String createdByName;

    // 🔥 REQUIRED constructor (must match JPQL)
    public RecentRecordDTO(
            Long id,
            Double amount,
            Object type, // Enum handled safely
            String category,
            LocalDate date,
            String description,
            String createdByName) {

        this.id = id;
        this.amount = amount;
        this.type = type.toString();
        this.category = category;
        this.date = date;
        this.description = description;
        this.createdByName = createdByName;
    }

    public Long getId() { return id; }
    public Double getAmount() { return amount; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public String getCreatedByName() { return createdByName; }
}