package com.healthnet.api.facilitytype;

import com.sugarsaas.api.core.AuditableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="facility_types")
public class FacilityType extends AuditableEntity
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
