package com.example.st.add.repository;

import org.example.st.model.ActiveAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveActionRepository extends JpaRepository<ActiveAction, Long> {
}
