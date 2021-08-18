package com.github.carvaldo.fimo.api.datasource.repository.local.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Suppress("JpaDataSourceORMInspection")
@Entity(name = "cast_movie")
data class CastMovie(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var role: String? = null,
    var year: Date? = null,
    var description: String? = null,
    var title: String? = null,
    var apiId: String? = null
)