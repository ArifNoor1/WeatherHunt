package com.devadilarif.weatherhunt.repo.local.model

import androidx.room.Entity


@Entity
data class COVID19(
    val active_cases_critical_percentage: String,
    val active_cases_mild_percentage: String,
    val cases_with_outcome: String,
    val closed_cases_death_percentage: String,
    val closed_cases_recovered_percentage: String,
    val critical_condition_active_cases: String,
    val currently_infected: String,
    val death_cases: String,
    val death_closed_cases: String,
    val general_death_rate: String,
    val last_update: String,
    val mild_condition_active_cases: String,
    val recovered_closed_cases: String,
    val recovery_cases: String,
    val total_cases: String
)