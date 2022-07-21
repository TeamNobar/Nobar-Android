package org.sopt.appzam.nobar_android.data.local.db

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import org.json.JSONArray

object SearchSharedPreferences {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    //private lateinit var jsonArray: JSONArray

    fun init(context: Context, key: String) {
        sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        //jsonArray = JSONArray()
    }

    fun addString2Pref(key: String, text: String) {
        val list = getStringArrayPref(key)
        list.add(0,text)
        while (list.size > 10) {
            list.removeAt(list.size-1)
        }
        val convertedData: String = Gson().toJson(list)
        editor.putString(key, convertedData).apply()
    }

    fun getStringArrayPref(key: String): ArrayList<String> {
        val json = sharedPreferences.getString(key, null)
        val list = ArrayList<String>()

        if (json != null) {
            val tmp = JSONArray(json)
            for (i in 0..(tmp.length() - 1)) {
                list.add(tmp.optString(i))
            }
        }
        return list
    }

    fun clearPref(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}