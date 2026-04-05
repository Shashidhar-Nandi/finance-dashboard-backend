package com.finance.dashboard.controller;

import com.finance.dashboard.dto.RecordRequest;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.service.RecordService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    // ✅ Create record for logged-in user
    @PostMapping
    public FinancialRecord createRecord(
            @RequestBody RecordRequest request) {

        return recordService.createRecord(request);
    }

    // ✅ Create record for specific user (ADMIN)
    @PostMapping("/user/{userId}")
    public FinancialRecord createRecordForUser(
            @PathVariable Long userId,
            @RequestBody RecordRequest request) {

        return recordService
                .createRecordForUser(userId, request);
    }

    // ✅ Get logged-in user records
    @GetMapping
    public List<FinancialRecord> getMyRecords() {

        return recordService.getMyRecords();
    }

    // ✅ Get records by userId (ADMIN)
    @GetMapping("/user/{userId}")
    public List<FinancialRecord> getRecordsByUser(
            @PathVariable Long userId) {

        return recordService.getRecordsByUser(userId);
    }

    // ✅ Delete Record
    @DeleteMapping("/{recordId}")
    public String deleteRecord(
            @PathVariable Long recordId) {

        recordService.deleteRecord(recordId);

        return "Record deleted successfully";
    }
}