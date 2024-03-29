package org.example.st.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "short_tender_info")
@ToString
public class ShortTenderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String region;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @JoinColumn
    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Product> productList;
}
