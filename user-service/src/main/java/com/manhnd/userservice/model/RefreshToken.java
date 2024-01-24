package com.manhnd.userservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table(name = "refreshtoken")
public class RefreshToken {
    @Id
    private String ids;
    private String token;
    private Instant expirydate;
    @OneToOne
    @JoinColumn(name = "ids", referencedColumnName = "ids")
    private User user;
}
