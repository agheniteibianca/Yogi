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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String body;
    private Date date_posted;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ownerid", nullable = false)
    private User owner;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "productid", nullable = false)
    private Product product;
}
