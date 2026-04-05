package com.finance.dashboard.repository;

import com.finance.dashboard.dto.CategorySummary;
import com.finance.dashboard.dto.MonthlySummary;
import com.finance.dashboard.dto.RecentRecordDTO;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.entity.User;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository
        extends JpaRepository<FinancialRecord, Long> {

    // Existing Filters
    List<FinancialRecord> findByType(
            RecordType type);

    List<FinancialRecord> findByCategory(
            String category);

    List<FinancialRecord> findByDateBetween(
            LocalDate start,
            LocalDate end);

    List<FinancialRecord> findByCreatedById(
            Long userId);
    
    List<FinancialRecord> findByCreatedBy(User user);

    // 🔥 Dashboard Total by Type & User
    @Query("""
           SELECT SUM(r.amount)
           FROM FinancialRecord r
           WHERE r.type = :type
           AND r.createdBy.id = :userId
           """)
    Double getTotalByTypeAndUser(
            @Param("type") RecordType type,
            @Param("userId") Long userId);


    // 🔥 Category Summary by User
    @Query("""
           SELECT new com.finance.dashboard.dto.CategorySummary(
                  r.category,
                  SUM(r.amount))
           FROM FinancialRecord r
           WHERE r.createdBy.id = :userId
           GROUP BY r.category
           """)
    List<CategorySummary> getCategoryTotalsByUser(
            @Param("userId") Long userId);


    //months trends 
    @Query("""
    	       SELECT new com.finance.dashboard.dto.MonthlySummary(
    	           YEAR(r.date),
    	           MONTH(r.date),
    	           SUM(CASE WHEN r.type = 'INCOME' THEN r.amount ELSE 0 END),
    	           SUM(CASE WHEN r.type = 'EXPENSE' THEN r.amount ELSE 0 END)
    	       )
    	       FROM FinancialRecord r
    	       WHERE r.createdBy.id = :userId
    	       GROUP BY YEAR(r.date), MONTH(r.date)
    	       ORDER BY YEAR(r.date), MONTH(r.date)
    	       """)
    	List<MonthlySummary> getMonthlySummary(@Param("userId") Long userId);
    
 // 🔥 Recent Activity (Last 5 Records)

    @Query("""
    	       SELECT new com.finance.dashboard.dto.RecentRecordDTO(
    	           r.id,
    	           r.amount,
    	           r.type,
    	           r.category,
    	           r.date,
    	           r.description,
    	           r.createdBy.name
    	       )
    	       FROM FinancialRecord r
    	       WHERE r.createdBy.id = :userId
    	       ORDER BY r.date DESC
    	       """)
    	List<RecentRecordDTO> getRecentRecords(
    	        @Param("userId") Long userId,
    	        Pageable pageable);
}