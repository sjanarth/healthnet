package com.healthnet.api.identity.role;

import com.healthnet.api.identity.privilege.HealthnetSeededPrivileges;
import com.sugarsaas.api.identity.privilege.SeededPrivilege;
import com.sugarsaas.api.identity.role.SeededRole;
import com.sugarsaas.api.identity.role.SugarSeededRoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum HealthnetSeededRoles implements SeededRole
{
    PATIENT_MANAGER("PatientManager", "Staff that can add/schedule patients",
            new ArrayList<>(
                    Arrays.asList(
                        new HealthnetSeededPrivileges[] {
                                HealthnetSeededPrivileges.ADD_PATIENT,
                                HealthnetSeededPrivileges.SCHEDULE_PATIENT,
                        }))),
    PATIENT_CARETEAM_MEMBER("PatientCareTeamMember", "Member of a care team",
            new ArrayList<>(
                Arrays.asList(
                    new HealthnetSeededPrivileges[] {
                            HealthnetSeededPrivileges.MANAGE_PATIENT,
                            HealthnetSeededPrivileges.SCHEDULE_PATIENT,
                            HealthnetSeededPrivileges.READ_PATIENT_RECORDS
                    }))),
    DOCTOR("Doctor", "A doctor, physician or surgeon",
            new ArrayList<>(
                Arrays.asList(
                    new HealthnetSeededPrivileges[] {
                            HealthnetSeededPrivileges.MANAGE_PATIENT,
                            HealthnetSeededPrivileges.SCHEDULE_PATIENT,
                            HealthnetSeededPrivileges.ORDER_TESTS,
                            HealthnetSeededPrivileges.ORDER_MEDICATIONS,
                            HealthnetSeededPrivileges.READ_PATIENT_RECORDS,
                            HealthnetSeededPrivileges.UPDATE_PATIENT_RECORDS
                    })));
    static
    {
        SugarSeededRoles.TENANCY_GROUP_ADMIN.privileges.add(HealthnetSeededPrivileges.ADD_TENANCY_FACILITY);
        SugarSeededRoles.TENANCY_GROUP_ADMIN.privileges.add(HealthnetSeededPrivileges.MANAGE_TENANCY_FACILITY);
        SugarSeededRoles.TENANCY_ADMIN.privileges.add(HealthnetSeededPrivileges.ADD_TENANCY_FACILITY);
        SugarSeededRoles.TENANCY_ADMIN.privileges.add(HealthnetSeededPrivileges.MANAGE_TENANCY_FACILITY);
    }

    public String name;
    public String description;
    public List<SeededPrivilege> privileges;
    HealthnetSeededRoles(String name, String description, List<SeededPrivilege> privileges) {
        this.name = name;
        this.description = description;
        this.privileges = privileges;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<SeededPrivilege> getPrivileges() {
        return privileges;
    }
}