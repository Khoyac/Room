package com.example.roomfunciona

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MisAmigos::class],
    version = 1
)

abstract class AmigosBd :RoomDatabase(){
    abstract fun misAmigosDao(): MisAmigosDao
}