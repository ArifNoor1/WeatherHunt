package com.devadilarif.weatherhunt.repo.local.model

import androidx.room.Entity


@Entity
data class COVID19(
    val death_cases: String,
    val recovery_cases: String,
    val total_cases: String
)