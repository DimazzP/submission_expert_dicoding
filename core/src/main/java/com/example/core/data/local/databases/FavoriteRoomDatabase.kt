package com.example.core.data.local.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.data.local.entities.FavoriteEntity
import com.example.core.data.local.rooms.FavoriteDao
@Database(entities = [FavoriteEntity::class], version = 4, exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteRoomDatabase::class.java,
                        "favorite_database"
                    )
                        .fallbackToDestructiveMigration()  // Menambahkan fallbackToDestructiveMigration
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }
}
