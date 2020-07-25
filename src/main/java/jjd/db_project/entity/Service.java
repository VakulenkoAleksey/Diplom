package jjd.db_project.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Service {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Setter
    @Getter
    @Column(length = 500, nullable = false)
    private String service;

    @Setter
    @Getter
    @Column(nullable = false)
    private BigDecimal price;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn
    private Visit visit;

}
