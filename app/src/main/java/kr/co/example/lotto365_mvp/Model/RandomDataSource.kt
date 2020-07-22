package kr.co.example.lotto365_mvp.Model

import kr.co.example.lotto365_mvp.MiniFrameWork.DataBase.Entity.History

interface RandomDataSource {

    fun getFixedNumber() = ArrayList<String>()

    fun getExceptNumber() = ArrayList<String>()

    fun saveFixedNumber(number: ArrayList<String>)

    fun saveExceptNumber(number: ArrayList<String>)

    fun clearFixedNumber()

    fun clearExceptNumber()

    fun saveHistory(history: History)

    fun saveAllHistory(historyArray : ArrayList<History>)

    fun getAllHistory() = ArrayList<History>()

    fun removeHistory(uniqueId: String)

    fun removeAllHistory()




}