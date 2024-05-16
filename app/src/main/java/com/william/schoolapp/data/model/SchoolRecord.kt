package com.william.schoolapp.data.model

import com.google.gson.annotations.SerializedName

data class SchoolRecord(
    @SerializedName("School_Id")
    val schoolId: Int,
    @SerializedName("Org_Name")
    val orgName: String,
    @SerializedName("Telephone")
    val telephone: String,
    @SerializedName("Email")
    val email: String,
    @SerializedName("URL")
    val url: String,
    @SerializedName("Org_Type")
    val orgType: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Add1_Line1")
    val line1: String,
    @SerializedName("Add1_Suburb")
    val suburb: String,
    @SerializedName("Add1_City")
    val city: String,
)
