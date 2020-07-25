package jjd.db_project.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
public class Visit {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Setter
    @Getter
    private int mileage;

    @Setter
    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateOfVisit;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn
    private Car car;

    @Setter
    @Getter
    @OneToMany(mappedBy = "visit",cascade = CascadeType.ALL)
    private List<Service> listOfWorks = new ArrayList<>();

}
