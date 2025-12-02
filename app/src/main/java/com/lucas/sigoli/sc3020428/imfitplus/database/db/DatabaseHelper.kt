package com.lucas.sigoli.sc3020428.imfitplus.database.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE user (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                age INTEGER NOT NULL,
                name TEXT NOT NULL,
                weight REAL NOT NULL,
                height REAL NOT NULL,
                gender TEXT NOT NULL,
                sportsLevel TEXT NOT NULL,
                imc TEXT NOT NULL,
                imcCategory TEXT NOT NULL,
                baseCalories TEXT NOT NULL,
                idealWeight TEXT NOT NULL
            );
            """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

    companion object{
        const val DB_NAME = "imfitplus.db"
        const val DB_VERSION = 1
        }


}