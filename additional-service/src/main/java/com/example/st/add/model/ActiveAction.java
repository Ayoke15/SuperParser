package com.example.st.add.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActiveAction {
    /**
     * Уникальный идентификатор действия.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название статуса.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @NotNull
    private List<Xpath> xpath;

    @Column
    @NotNull
    private String xpath_path;

    @Column
    @NotNull
    private Integer number;

}
