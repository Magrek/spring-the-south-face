package com.vodianytskyivi.thesouthface.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private boolean active;

    @OneToOne
    @JoinColumn(name = "bag_id", referencedColumnName = "id")
    private Bag bag;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}