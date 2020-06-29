package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Presenter

import android.content.Context
import io.realm.RealmList
import kr.co.factoryx.lotto365_mvp.MiniFrameWork.DataBase.Entity.History
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract.RandomORDreamAdapterContract
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract.RandomNumberContract
import kr.co.factoryx.lotto365_mvp.Model.LocalRandomDataSource

class RandomNumberPresenter : RandomNumberContract.Presenter {

    private lateinit var view: RandomNumberContract.View
    private lateinit var dataSource: LocalRandomDataSource
    private lateinit var model: RandomORDreamAdapterContract.Model
    private lateinit var adapterView: RandomORDreamAdapterContract.View
    private var generateList = ArrayList<ArrayList<String>>()

    override fun setView(view: RandomNumberContract.View) {
        super.setView(view)
        this.view = view
    }

    override fun setDataSource(context: Context) {
        super.setDataSource(context)
        dataSource = LocalRandomDataSource(context)
    }

    override fun releaseView() {
        super.releaseView()
    }

    override fun setAdapterModel(model: RandomORDreamAdapterContract.Model) {
        this.model = model
    }

    override fun setAdapterView(view: RandomORDreamAdapterContract.View) {
        this.adapterView = view
    }

    override fun setItems() {
        var randomArray: ArrayList<String>
        val fixedArray = dataSource.getFixedNumber()
        val exceptArray = dataSource.getExceptNumber()

        generateList.clear()


        loopMain@ while (generateList.size < 5) {
            randomArray = ArrayList()
            randomArray.addAll(fixedArray)
            loopSub@ while (randomArray.size < 6) {
                val randomInteger = (1..45).shuffled().first()
                if (!randomArray.contains(randomInteger.toString()) && !exceptArray.contains(randomInteger.toString())) {
                    randomArray.add(randomInteger.toString())
                }
            }
            randomArray.sortWith(Comparator { o1, o2 -> o1.toInt().compareTo(o2.toInt()) })

            loopFor@ for (i in 0 until generateList.size) {
                if (generateList[i].containsAll(randomArray)) {
                    continue@loopMain
                }
            }

            generateList.add(randomArray)
        }

        model.addItems(generateList)
        adapterView.notifyAdapter()
    }

    fun saveNumber(position: Int) {
        val history = History()
        val currentTime = System.currentTimeMillis()
        history.uniqueId = Utils.sha256(Utils.dataFormat(currentTime, "yyyyMMdd") + generateList[position])!!
        history.historyDate = currentTime

        val list: RealmList<String> = RealmList()
        for (number in generateList[position]) {
            list.add(number)
        }

        history.numbers = list
        history.type = "Random"

        dataSource.saveHistory(history)
    }

    fun saveAllNumber() {
        val historyArray = ArrayList<History>()
        val currentTime = System.currentTimeMillis()

        for (i in 0 until generateList.size) {
            val list: RealmList<String> = RealmList()
            val history = History()
            for (number in generateList[i]){
                list.add(number)
            }

            history.uniqueId = Utils.sha256(Utils.dataFormat(currentTime , "yyyyMMdd") + generateList[i])!!
            history.historyDate = currentTime
            history.numbers = list
            history.type = "Random"
            historyArray.add(history)
        }

        dataSource.saveAllHistory(historyArray)

    }
}