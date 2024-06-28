/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muzoo.ssc.springwebapp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gigadot
 */
@Entity
@Setter
@Getter
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "join_user_group",
        inverseJoinColumns = @JoinColumn(name = "user_id"),
        joinColumns = @JoinColumn(name = "group_id")
    )
    private Set<User> users = new HashSet<>();

}
