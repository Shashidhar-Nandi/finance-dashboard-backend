package com.finance.dashboard.service;

import com.finance.dashboard.dto.RecordRequest;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.repository.RecordRepository;
import com.finance.dashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    // 🔐 Get Logged-in User
    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }

    // ✅ Create Record for Logged-in User
    public FinancialRecord createRecord(
            RecordRequest request) {

        User user = getLoggedInUser();

        FinancialRecord record =
                FinancialRecord.builder()
                        .amount(request.getAmount())
                        .type(request.getType())
                        .category(request.getCategory())
                        .date(request.getDate())
                        .description(request.getDescription())
                        .createdBy(user)
                        .build();

        return recordRepository.save(record);
    }

    // ✅ Create Record for Specific User (ADMIN use)
    public FinancialRecord createRecordForUser(
            Long userId,
            RecordRequest request) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        FinancialRecord record =
                FinancialRecord.builder()
                        .amount(request.getAmount())
                        .type(request.getType())
                        .category(request.getCategory())
                        .date(request.getDate())
                        .description(request.getDescription())
                        .createdBy(user)
                        .build();

        return recordRepository.save(record);
    }

    // ✅ Get Logged-in User Records
    public List<FinancialRecord> getMyRecords() {

        User user = getLoggedInUser();

        return recordRepository
                .findByCreatedById(user.getId());
    }

    // ✅ Get Records by UserId (ADMIN)
    public List<FinancialRecord> getRecordsByUser(
            Long userId) {

        return recordRepository
                .findByCreatedById(userId);
    }

    // ✅ Delete Record
    public void deleteRecord(Long recordId) {

        FinancialRecord record =
                recordRepository.findById(recordId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Record not found"));

        recordRepository.delete(record);
    }
}