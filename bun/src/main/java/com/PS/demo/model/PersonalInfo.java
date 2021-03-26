package com.PS.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalInfo {

    @Id
    private Long user_id;
    private String first_name;
    private String surname;
    private int age;
    private String city;
    private Date date_joined;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
