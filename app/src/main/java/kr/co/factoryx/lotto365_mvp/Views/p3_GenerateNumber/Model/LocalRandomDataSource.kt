package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Model

import android.content.Context
import kr.co.factoryx.lotto365_mvp.Common.commonset.C
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract.RandomGenerateContract
import org.json.JSONArray

class LocalRandomDataSource constructor(context: Context, val presenter: RandomGenerateContract.Presenter) :
    RandomDataSource {

    private var fixedArrayList: ArrayList<String> = ArrayList()
    private var exceptArrayList: ArrayList<String> = ArrayList()

    init {

        run {
            var jsonArray = JSONArray(Utils.getPreferenceData(context, C.Pref.fixed_number))
            for (i in 0 until jsonArray.length()) {
                fixedArrayList.add(jsonArray[i].toString())
            }
        }

        run {
            var jsonArray = JSONArray(Utils.getPreferenceData(context, C.Pref.except_number))
            for (i in 0 until jsonArray.length()) {
                exceptArrayList.add(jsonArray[i].toString())
            }
        }


    }

    override fun getFixedNumber(): ArrayList<String> {
        return fixedArrayList
    }

    override fun getExceptNumber(): ArrayList<String> {
        return exceptArrayList
    }

    override fun saveFixedNumber(number: ArrayList<String>) {
        fixedArrayList = number
    }

    override fun saveExceptNumber(number: ArrayList<String>) {
        exceptArrayList = number

    }

    override fun clearFixedNumber() {
        fixedArrayList.clear()
    }

    override fun clearExceptNumber() {
        exceptArrayList.clear()
    }


}