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
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date datePosted;
    private String text;
    private float offeredPrice;
    private Boolean isAccepted;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "senderid", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receiverid", nullable = false)
    private User receiver;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "productid", nullable = false)
    private Product product;
}