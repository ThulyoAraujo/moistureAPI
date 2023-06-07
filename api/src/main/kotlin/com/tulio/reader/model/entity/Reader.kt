package com.tulio.reader.model.entity

import javax.persistence.*

@Entity(name = "trs_rea_reader")
class Reader(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rea_id")
    var id: Long? = null,

    @Column(name = "rea_moisture")
    @Enumerated(EnumType.STRING)
    var moisture: MoistureLevel? = MoistureLevel.SECO,

    @Column(name = "rea_date")
    var date: String? = null,

    @Column(name = "rea_time")
    var time: String? = null,

    @Column(name = "rea_latitude")
    var latitude: String? = null,

    @Column(name = "rea_longitude")
    var longitude: String? = null
) {
    enum class MoistureLevel {
        SECO,
        UMIDO,
        ENCHARCADO
    }
}