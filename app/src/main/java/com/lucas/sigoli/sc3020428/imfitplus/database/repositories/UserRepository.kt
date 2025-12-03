package com.lucas.sigoli.sc3020428.imfitplus.database.repositories

// External Libraries
import android.content.Context
import android.database.Cursor
import android.content.ContentValues

// Services
import com.lucas.sigoli.sc3020428.imfitplus.database.db.DatabaseHelper

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
import com.lucas.sigoli.sc3020428.imfitplus.dtos.UserHistory
import com.lucas.sigoli.sc3020428.imfitplus.enums.Gender
import com.lucas.sigoli.sc3020428.imfitplus.enums.SportsLevel

class UserRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    // Functions
    fun insert(user: User): Long {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("age", user.age)
            put("name", user.name)
            put("weight", user.weight)
            put("height", user.height)
            put("gender", user.gender.name)
            put("sportsLevel", user.sportsLevel.name)
            put("imc", user.imc)
            put("imcCategory", user.imcCategory)
            put("baseCalories", user.baseCalories)
            put("idealWeight", user.idealWeight)
            put("waterConsumption", user.waterConsumption)
            put("createdAt", System.currentTimeMillis())
        }

        return db.insert("user", null, values)
    }


    fun getUserHistory(name: String): ArrayList<UserHistory> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM user WHERE name = ? ORDER BY createdAt DESC",
            arrayOf(name)
        )

        val list = ArrayList<UserHistory>()

        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToHistory(cursor))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return list
    }


    fun updateUser(user: User, id: Int): Int {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("age", user.age)
            put("name", user.name)
            put("weight", user.weight)
            put("height", user.height)
            put("gender", user.gender.name)
            put("sportsLevel", user.sportsLevel.name)
            put("imc", user.imc)
            put("imcCategory", user.imcCategory)
            put("baseCalories", user.baseCalories)
            put("idealWeight", user.idealWeight)
            put("waterConsumption", user.waterConsumption)
        }

        return db.update("user", values, "id = ?", arrayOf(id.toString()))
    }

    fun deleteUser(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("user", "id = ?", arrayOf(id.toString()))
    }

    // HELPERS
    private fun cursorToUser(cursor: Cursor): User {
        return User(
            age = cursor.getInt(cursor.getColumnIndexOrThrow("age")),
            name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
            weight = cursor.getDouble(cursor.getColumnIndexOrThrow("weight")),
            height = cursor.getDouble(cursor.getColumnIndexOrThrow("height")),
            gender = Gender.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("gender"))),
            sportsLevel = SportsLevel.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("sportsLevel"))),
            imc = cursor.getString(cursor.getColumnIndexOrThrow("imc")),
            imcCategory = cursor.getString(cursor.getColumnIndexOrThrow("imcCategory")),
            baseCalories = cursor.getString(cursor.getColumnIndexOrThrow("baseCalories")),
            idealWeight = cursor.getString(cursor.getColumnIndexOrThrow("idealWeight")),
            waterConsumption = cursor.getString(cursor.getColumnIndexOrThrow("waterConsumption"))
        )
    }

    private fun cursorToHistory(cursor: Cursor): UserHistory {
        val user = cursorToUser(cursor)

        return UserHistory(
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            user = user,
            createdAt = cursor.getLong(cursor.getColumnIndexOrThrow("createdAt"))
        )
    }
}
