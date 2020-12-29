package com.healthnet.api.facility;

import com.sugarsaas.api.core.AuditableEntity;
import com.sugarsaas.api.tenancy.Tenancy;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="facilities")
public class Facility extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="active", columnDefinition="BIT(1)")
    private boolean active = true;

    @Column(name="address1")
    private String address1;

    @Column(name="address2")
    private String address2;

    @Column(name="phone")
    private String phone;

    @JoinColumn(name="tenancy_id")
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Tenancy tenancy;

    /*
    @JoinColumn(name="facility_type")
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Tenancy tenancy;
    */
}
