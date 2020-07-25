package jjd.db_project.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Client {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Setter
    @Getter
    @Column(nullable = false, length = 50)
    @NotBlank
    private String name;

    @Setter
    @Getter
    @NotBlank
    @Column(unique = true, nullable = false)
    private String phone;

    @Setter
    @Getter
    @Email
    @Column(unique = true)
    private String email;

    @Setter
    @Getter
    @Column(nullable = false)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL
    /*orphanRemoval = true - удаление из коллекции приведет к удалению из таблицы */)
    private List<Car> cars = new ArrayList<>();

}
