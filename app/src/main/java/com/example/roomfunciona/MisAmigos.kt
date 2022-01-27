package com.example.roomfunciona

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MisAmigos(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "identificador")
    val id:Int = 0, //le pongo un valor por defecto para no tener que paserle un id cuando quiera hacer un insert
    var nombre:String,
    var email:String
)
