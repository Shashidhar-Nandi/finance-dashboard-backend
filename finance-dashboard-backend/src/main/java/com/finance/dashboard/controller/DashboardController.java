package com.finance.dashboard.controller;

import com.finance.dashboard.dto.CategorySummary;
import com.finance.dashboard.dto.DashboardResponse;
import com.finance.dashboard.dto.MonthlySummary;
import com.finance.dashboard.dto.RecentRecordDTO;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // ✅ Dashboard Summary by User
    @GetMapping("/summary/{userId}")
    public DashboardResponse getDashboardSummary(
            @PathVariable Long userId) {

        return dashboardService
                .getDashboardSummary(userId);
    }

    // ✅ Category Summary by User
    @GetMapping("/category-summary/{userId}")
    public List<CategorySummary> getCategorySummary(
            @PathVariable Long userId) {

        return dashboardService
                .getCategorySummary(userId);
    }
    
    @GetMapping("/monthly-summary/{userId}")
    public List<MonthlySummary> getMonthlySummary(
            @PathVariable Long userId) {

        return dashboardService
                .getMonthlySummary(userId);
    }
    
 // 🔥 Recent Activity API

    @GetMapping("/recent/{userId}")
    public List<RecentRecordDTO>
    getRecentRecords(
            @PathVariable Long userId) {

        return dashboardService
                .getRecentRecords(userId);
    }
}