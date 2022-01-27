package com.example.roomfunciona

import android.app.Application
import androidx.room.Room

class RoomApp : Application(){
    companion object{
        lateinit var db: AmigosBd
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AmigosBd::class.java, "amigos")
            .build()
    }
}

