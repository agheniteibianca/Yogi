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
public class ProductMeasurements {
    @Id
    private Long product_id;

    private float waist;
    private float hips;
    private float shoulders;
    private float shoe_Size;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
}