package org.example.st.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс, представляющий сущность статуса тендера.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "status")
public class Status {
    /**
     * Уникальный идентификатор статуса.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название статуса.
     */
    @Column
    private String name;
}

