package com.finance.dashboard.repository;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(Type type);
}