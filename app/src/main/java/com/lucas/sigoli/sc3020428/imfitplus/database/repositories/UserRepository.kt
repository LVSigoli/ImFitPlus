package com.lucas.sigoli.sc3020428.imfitplus.database.repositories

// External Libraries
import android.content.Context
import android.database.Cursor
import android.content.ContentValues

// Services
import com.lucas.sigoli.sc3020428.imfitplus.database.db.DatabaseHelper

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User
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
            put("createdAt", System.currentTimeMillis())
        }

        return db.insert("user", null, values)
    }

    fun getUsers(name: String): ArrayList<User> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM user WHERE name = ?",
            arrayOf(name)
        )

        val users = ArrayList<User>()

        if (cursor.moveToFirst()) {
            do {
                users.add(cursorToUser(cursor))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return users
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
        }

        return db.update("user", values, "id = ?", arrayOf(id.toString()))
    }

    fun deleteUser(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("user", "id = ?", arrayOf(id.toString()))
    }

    // Helpers
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
            idealWeight = cursor.getString(cursor.getColumnIndexOrThrow("idealWeight"))
        )
    }

}
