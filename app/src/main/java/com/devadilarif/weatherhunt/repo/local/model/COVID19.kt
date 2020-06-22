package com.devadilarif.weatherhunt.repo.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class COVID19(
    @PrimaryKey
    var death_cases: String,

    var recovery_cases: String,
    var total_cases: String
)