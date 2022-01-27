package com.example.roomfunciona

import androidx.room.*

@Dao
interface MisAmigosDao {
    @Query("SELECT * from MisAmigos")
    suspend fun getTodo(): List<MisAmigos>

    @Query("SELECT * FROM MisAmigos WHERE identificador = :id")
    suspend fun getPorId(id: Int): MisAmigos

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(amigos: MisAmigos)

    @Update
    suspend fun update(amigo: MisAmigos)

    @Delete
    suspend fun delete(amigo: MisAmigos)
}