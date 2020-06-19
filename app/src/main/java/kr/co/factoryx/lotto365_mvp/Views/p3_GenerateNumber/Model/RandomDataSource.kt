package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Model

interface RandomDataSource {

    fun getFixedNumber() = ArrayList<String>()

    fun getExceptNumber() = ArrayList<String>()

    fun saveFixedNumber(number: ArrayList<String>)

    fun saveExceptNumber(number: ArrayList<String>)

    fun clearFixedNumber()

    fun clearExceptNumber()


}