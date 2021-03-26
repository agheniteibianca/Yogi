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
public class UserMeasurements {
    @Id
    private Long user_id;
    private float waist;
    private float hips;
    private float shoulders;
    private float shoe_Size;


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
