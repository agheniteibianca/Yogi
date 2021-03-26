package com.PS.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nume;
    private Boolean gender;
    private float rating;
    private int itemssold;



    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private PersonalInfo personalInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private UserMeasurements userMeasurements;

    //@OneToMany
    //@JoinColumn(name = "owner_id")
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offersMade;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offersReceived;


    @OneToMany
    @JoinColumn(name = "userfrom")
    private List<Review> reviewsGiven;

    @OneToMany
    @JoinColumn(name = "userto")
    private List<Review> reviewsGotten;


    @OneToMany
    @JoinColumn(name = "pmfrom")
    private List<Pm> pms_sent;

    @OneToMany
    @JoinColumn(name = "pmto")
    private List<Pm> pms_received;


    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString(){
        return username;
    }
}
