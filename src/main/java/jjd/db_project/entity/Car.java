package jjd.db_project.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Car {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Setter
    @Getter
    @Column
    @NotBlank
    private String model;

    @Setter
    @Getter
    @NotBlank
    @Column(length = 17)
    private String vin;


    @Setter
    @Getter
    @Column(length = 20)
    private String gosnumber;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn
    private Client client;

    @Setter
    @Getter
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

    @Override
    public String toString() {
        return model + " " + gosnumber + " " + vin;
    }
}
