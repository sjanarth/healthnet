package com.healthnet.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "active")
    private int active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)

    @JoinTable(name="user_roles",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id", nullable=false, updatable=false)},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id", nullable=false, updatable=false)})
    private Set<Role> roles;


}