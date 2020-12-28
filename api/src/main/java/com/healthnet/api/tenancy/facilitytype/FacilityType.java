package com.healthnet.api.tenancy.facilitytype;

import com.healthnet.api.core.AuditableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="privileges")
public class Privilege extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="seeded", columnDefinition="BIT(1)")
    private boolean seeded = true;
}
