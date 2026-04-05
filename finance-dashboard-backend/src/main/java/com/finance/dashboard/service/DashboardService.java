package com.finance.dashboard.service;

import com.finance.dashboard.dto.CategorySummary;
import com.finance.dashboard.dto.DashboardResponse;
import com.finance.dashboard.dto.MonthlySummary;
import com.finance.dashboard.dto.RecentRecordDTO;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.repository.RecordRepository;
import org.springframework.data.domain.PageRequest;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final RecordRepository recordRepository;

    // ✅ Dashboard Summary by User
    public DashboardResponse getDashboardSummary(
            Long userId) {

        Double income =
                recordRepository
                        .getTotalByTypeAndUser(
                                RecordType.INCOME,
                                userId);

        Double expense =
                recordRepository
                        .getTotalByTypeAndUser(
                                RecordType.EXPENSE,
                                userId);

        if (income == null)
            income = 0.0;

        if (expense == null)
            expense = 0.0;

        Double netBalance =
                income - expense;

        return DashboardResponse.builder()
                .totalIncome(income)
                .totalExpense(expense)
                .netBalance(netBalance)
                .build();
    }

    // ✅ Category Summary by User
    public List<CategorySummary> getCategorySummary(
            Long userId) {

        return recordRepository
                .getCategoryTotalsByUser(userId);
    }
    
    public List<MonthlySummary> getMonthlySummary(
            Long userId) {

        return recordRepository
                .getMonthlySummary(userId);
    }
    
    public List<RecentRecordDTO>
    getRecentRecords(Long userId) {

        return recordRepository.getRecentRecords(
                userId,
                PageRequest.of(0, 5) // Top 5
        );
    }

}