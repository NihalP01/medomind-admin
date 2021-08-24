package com.ebarim.admin.Network.Data.ROLE

data class RoleDataClass(
    val `data`: Data
)

data class Data(
    val details: Details,
    val user: User
)

data class Details(
    val created_at: String,
    val details: DetailsX,
    val id: Int,
    val updated_at: String,
    val user_id: Int
)

data class DetailsX(
    val speciality: String,
    val works_at: String
)

data class User(
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val role: String
)