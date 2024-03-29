package pham.hien.assignment.Utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pham.hien.assignment.Model.User
import kotlin.collections.ArrayList

object SharedPrefUtils {

    private val PREFERENCES_NAME = "RouManga"

    /**
     * Không sửa các sharedPre này
     */
    /**
     * Lưu kiểu booblean
     */
    fun setBoolean(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("boolean", value).apply()
    }

    fun getBoolean(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("boolean", false)
    }

    /**
     * Lưu kiểu string
     */
    fun getString(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString("string", "")
    }

    fun setString(context: Context, value: String?) {
        val sharedPreferences = context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString("string", value).apply()
    }

    /**
     * Lưu kiêu int
     */
    fun getInt(context: Context): Int {
        val sharedPreferences =
            context.getSharedPreferences(
                PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
        return sharedPreferences.getInt("int", -1)
    }

    fun setInt(context: Context, value: Int) {
        val sharedPreferences =
            context.getSharedPreferences(
                PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putInt("int", value).apply()
    }

    /**
     * Lưu kiểu long
     */
    fun setLong(context: Context, time: Long) {
        val sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putLong("long", time).apply()
    }

    fun getLong(context: Context): Long {
        val sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPref.getLong("long", 0L)
    }

    /**
     * Lưu lại dạng list
     */
    fun setListString(context: Context, lisString: ArrayList<String>) {
        val pref = context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        val gson = Gson()
        val listHistoryDayWorkout = gson.toJson(lisString)
        editor.putString("list_string", listHistoryDayWorkout)
        editor.apply()
    }

    fun getListString(context: Context): ArrayList<String> {
        val preferences = context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val result = preferences.getString("list_string", null)
        return if (result != null) {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<String>>() {}.type
            gson.fromJson(result, type)
        } else {
            ArrayList()
        }
    }

    /**
     * lưu dạng model
     */

    fun setLoginInformationUser(context: Context, value: User) {
        val pref = context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        val gson = Gson()
        val userModel = gson.toJson(value)
        editor.putString("LoginInformationUser", userModel)
        editor.apply()
    }

    fun getLoginInformationUser(context: Context): User {
        val preferences = context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val result = preferences.getString("LoginInformationUser", null)
        return if (result != null) {
            val gson = Gson()
            val type = object : TypeToken<User?>() {}.type
            gson.fromJson(result, type)
        } else {
            User()
        }
    }

}
