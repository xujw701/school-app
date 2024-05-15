package com.william.schoolapp.data.model

import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("School_Id")
    val schoolId: String,
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
)
