package com.example.st.add.repository;

import org.example.st.model.Xpath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XpathRepository extends JpaRepository<Xpath, Long> {
    Xpath findByLinkSite(String linkSite);
}
