package com.healthnet.api.accounts.tenancy;

import com.healthnet.api.core.AuditableEntity;
import com.healthnet.api.accounts.tenancygroup.TenancyGroup;
import com.healthnet.api.identity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name="tenancies")
public class Tenancy extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="shortname")
    private String shortName;

    @Column(name="longname")
    private String longName;

    @Column(name="email")
    private String email;

    @Column(name="active", columnDefinition="BIT(1)")
    private boolean active = true;

    @JoinColumn(name="tenancy_group_id")
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private TenancyGroup tenancyGroup;

    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinTable(name="tenancy_users",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id", nullable=false, updatable=false)},
            inverseJoinColumns={@JoinColumn(name="tenancy_id", referencedColumnName="id", nullable=false, updatable=false)})
    private Set<User> users;
}