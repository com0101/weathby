package com.example.weathby.localDataBase

import androidx.room.TypeConverter
import com.google.gson.Gson

class RoomTypeConverters{
    @TypeConverter
    fun convertInvoiceListToJSONString(tempList: tempList): String = Gson().toJson(tempList)
    @TypeConverter
    fun convertJSONStringToInvoiceList(jsonString: String): tempList = Gson().fromJson(jsonString,tempList::class.java)
}