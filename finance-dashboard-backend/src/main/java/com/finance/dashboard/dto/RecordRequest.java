package com.finance.dashboard.dto;

import com.finance.dashboard.entity.RecordType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordRequest {

    private Double amount;

    private RecordType type;

    private String category;

    private LocalDate date;

    private String description;
}