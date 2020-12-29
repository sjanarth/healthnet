package com.healthnet.api.facilitytype;

public enum SeededFacilityTypes
{
    BIRTH_CENTER("Birth Center", "Birth centers"),
    BLOOD_BANK("Blood Bank", "Blood banks"),
    DIABETES_CENTER("Diabetes Center", "Diabetes center"),
    DIALYSIS_CENTER("Dialysis Center", "Dialysis center"),
    HOSPICE_HOME("Hospice Home", "Hospice home"),
    GENERIC_HOSPITAL("Generic Hospital", "Generic Hospital"),
    MULTI_SPECIALITY_HOSPITAL("Multi-Speciality Hospital", "Multi speciality hospital"),
    IMAGING_CENTER("Imaging Center", "Imaging and Radiology center"),
    MENTAL_HEALTH_CENTER("Mental Health Center", "Mental health facility"),
    NURSING_HOME("Nursing Home", "Nursing home"),
    ORTHOPEDIC_CENTER("Orthopedic and Rehabilitation Center", "Orthopedic and Rehabilitation center"),
    URGENT_CARE("Urgent Care", "Urgent Care"),
    EYE_CARE("Eye Care Hospital", "Eye Care Hospital"),
    DENTAL_CARE("Dental Care Clinic", "Dental Care Clinic"),
    ENT_CENTER("ENT Center", "Ear, Nose, Throat Clinic");

    public final String name;
    public final String description;
    SeededFacilityTypes(String name, String description) { this.name = name; this.description = description; }
}
