package com.healthnet.api.identity.privilege;

import com.sugarsaas.api.identity.privilege.SeededPrivilege;

public enum HealthnetSeededPrivileges implements SeededPrivilege
{
    ADD_TENANCY_FACILITY("AddTenancyFacility", "Add new facilities to a tenancy"),
    MANAGE_TENANCY_FACILITY("ManageTenancyFacility", "Manage current facilities in a tenancy"),
    ADD_CARETEAM("AddCareTeam", "Define a new care team"),
    MANAGE_CARETEAM("ManageCareTeam", "Manage a current care team"),

    ADD_PATIENT("AddPatient", "Add a new patient"),
    MANAGE_PATIENT("ManagePatient", "Manage current patients"),
    SCHEDULE_PATIENT("SchedulePatient", "Schedule an appointment for a patient"),

    ORDER_TESTS("OrderTests", "Order diagnostic tests"),
    ORDER_MEDICATIONS("OrderMedications", "Order new medications for a patient"),
    READ_PATIENT_RECORDS("ReadPatientRecords", "Read a patient's health records"),
    UPDATE_PATIENT_RECORDS("UpdatePatientRecords", "Update a patient's health records");

    public final String name;
    public final String description;
    HealthnetSeededPrivileges(String name, String description) { this.name = name; this.description = description; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}