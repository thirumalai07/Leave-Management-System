package org.example.leavemanagementsystem.repository;

import org.example.leavemanagementsystem.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    
    Optional<Holiday> findByHolidayDate(LocalDate holidayDate);
    
    List<Holiday> findByIsOptionalFalse();
    
    List<Holiday> findByHolidayDateBetween(LocalDate startDate, LocalDate endDate);
}
