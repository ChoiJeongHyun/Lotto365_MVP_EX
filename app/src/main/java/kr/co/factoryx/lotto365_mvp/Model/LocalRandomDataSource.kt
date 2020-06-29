package kr.co.factoryx.lotto365_mvp.Model

import android.content.Context
import android.widget.Toast
import kr.co.factoryx.lotto365_mvp.Common.commonset.C
import kr.co.factoryx.lotto365_mvp.MiniFrameWork.DataBase.Entity.History
import kr.co.factoryx.lotto365_mvp.Store.HistoryStore
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Presenter.RandomNumberPresenter
import kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Presenter.HistoryPresenter
import org.json.JSONArray
import java.lang.Exception

class LocalRandomDataSource constructor(val context: Context) : RandomDataSource {

    private var fixedArrayList: ArrayList<String> = ArrayList()
    private var exceptArrayList: ArrayList<String> = ArrayList()
    private lateinit var historyPresenter: HistoryPresenter

    constructor(context: Context, historyPresenter: HistoryPresenter) : this(context) {
        this.historyPresenter = historyPresenter
    }

    init {

        try {
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getFixedNumber(): ArrayList<String> {
        return fixedArrayList
    }

    override fun getExceptNumber(): ArrayList<String> {
        return exceptArrayList
    }

    override fun saveFixedNumber(number: ArrayList<String>) {
        Utils.setPreferenceData(context, C.Pref.fixed_number, number.toString())
        fixedArrayList = number
    }

    override fun saveExceptNumber(number: ArrayList<String>) {
        Utils.setPreferenceData(context, C.Pref.except_number, number.toString())
        exceptArrayList = number
    }

    override fun clearFixedNumber() {
        fixedArrayList.clear()
    }

    override fun clearExceptNumber() {
        exceptArrayList.clear()
    }

    override fun saveHistory(history: History) {
        HistoryStore.insert(history, object : HistoryStore.queryListener {
            override fun queryResult(success: Boolean, exist: Boolean) {
                if (success && exist) {
                    Toast.makeText(context, "이미 저장되어 있습니다.", Toast.LENGTH_SHORT).show()
                } else if (success && !exist) {
                    Toast.makeText(context, "번호를 저장 하였습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "번호저장에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun saveAllHistory(historyArray: ArrayList<History>) {
        HistoryStore.insertAll(historyArray, object : HistoryStore.queryListener {
            override fun queryResult(success: Boolean, exist: Boolean) {
                if (success && exist) {
                    Toast.makeText(context, "이미 모든번호가 저장되있거나, 일부분 저장되어 있습니다\n모두 저장하려면 리스트 옆 저장버튼을 눌러주세요.", Toast.LENGTH_LONG).show()
                } else if (success && !exist) {
                    Toast.makeText(context, "모든 번호를 저장하였습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "번호저장에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    override fun getAllHistory(): ArrayList<History> {
        return HistoryStore.selectAll()
    }

    override fun removeHistory(uniqueId: String) {
        HistoryStore.delete(uniqueId, object : HistoryStore.queryListener {
            override fun queryResult(success: Boolean, exist: Boolean) {
                when (success) {
                    true -> {
                        Toast.makeText(context, "삭제 하였습니다.", Toast.LENGTH_SHORT).show()
                        historyPresenter.dbQuerySuccessing()
                    }
                    false -> Toast.makeText(context, "삭제에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    override fun removeAllHistory() {
        HistoryStore.deleteAll(object : HistoryStore.queryListener {
            override fun queryResult(success: Boolean, exist: Boolean) {
                when (success) {
                    true -> {
                        Toast.makeText(context, "전체 삭제하였습니다.", Toast.LENGTH_SHORT).show()
                        historyPresenter.dbQuerySuccessing()
                    }
                    false -> Toast.makeText(context, "전체삭제를 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}