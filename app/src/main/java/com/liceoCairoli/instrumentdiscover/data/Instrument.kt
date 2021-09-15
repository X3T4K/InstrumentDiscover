package com.liceoCairoli.instrumentdiscover.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Instrument(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var ytLink: String,
    var docLink: String
)