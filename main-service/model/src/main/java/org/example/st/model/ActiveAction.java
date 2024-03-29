package org.example.st.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne(fetch = FetchType.EAGER)
//    @NotNull
    @Transient
    private Xpath xpath;

    @Column
    @NotNull
    private String xpath_path;

    @Column
    @NotNull
    private Integer number;

}
