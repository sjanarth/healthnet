package com.healthnet.api.tenancy.facilitytype;

public enum SeededPrivileges
{
    ADD_SUPER_ADMIN("AddSuperAdmin", "Add new super admins"),
    MANAGE_SUPER_ADMIN("ManageSuperAdmin", "Manage current super admins"),

    ADD_TENANCY_GROUP("AddTenancyGroup", "Add new tenancy group"),
    MANAGE_TENANCY_GROUP("ManageTenancyGroup", "Manage current tenancy group"),
    ADD_TENANCY("AddTenancy", "Add new tenancy to a tenancy group"),
    MANAGE_TENANCY("ManageTenancy", "Manage current tenancies in a tenancy group"),
    ADD_TENANCY_FACILITY("AddTenancyFacility", "Add new facilities to a tenancy"),
    MANAGE_TENANCY_FACILITY("ManageTenancyFacility", "Manage current facilities in a tenancy"),
    ADD_TENANCY_USER("AddTenancyUser", "Add new users to the tenancy except new patients"),
    MANAGE_TENANCY_USER("ManageTenancyUser", "Manage current uses in a tenancy except patients"),
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
    SeededPrivileges(String name, String description) { this.name = name; this.description = description; }
}
