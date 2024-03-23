package com.example.st.main.repository;

import com.example.st.main.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория для работы с данными тендеров в базе данных.
 */
@Repository
public interface TenderJpaRepository extends JpaRepository<Tender, Long> {

}

