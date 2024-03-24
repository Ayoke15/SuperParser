package com.example.st.add.repository;

import com.example.st.add.model.ActiveAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveActionRepository extends JpaRepository<ActiveAction, Long> {
}
