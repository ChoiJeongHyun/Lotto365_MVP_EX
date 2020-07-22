package kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Presenter

import android.content.Context
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract.FixedAndExceptAdapterContract
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Contract.RandomGenerateContract
import kr.co.example.lotto365_mvp.Model.LocalRandomDataSource

class RandomGeneratePresenter : RandomGenerateContract.Presenter {

    private lateinit var view: RandomGenerateContract.View
    private lateinit var dataSource: LocalRandomDataSource
    private lateinit var fixedModel: FixedAndExceptAdapterContract.Model
    private lateinit var exceptModel: FixedAndExceptAdapterContract.Model
    private lateinit var fixedAdapterView: FixedAndExceptAdapterContract.View
    private lateinit var exceptAdapterView: FixedAndExceptAdapterContract.View


    override fun setView(view: RandomGenerateContract.View) {
        super.setView(view)
        this.view = view
    }

    override fun setDataSource(context: Context) {
        super.setDataSource(context)
        this.dataSource = LocalRandomDataSource(context)
    }

    override fun releaseView() {
        super.releaseView()
    }

    override fun setAdaptersModel(fixedModel: FixedAndExceptAdapterContract.Model, exceptModel: FixedAndExceptAdapterContract.Model) {
        this.fixedModel = fixedModel
        this.exceptModel = exceptModel
    }

    override fun setAdaptersView(fixedView: FixedAndExceptAdapterContract.View, exceptView: FixedAndExceptAdapterContract.View) {
        this.fixedAdapterView = fixedView
        this.exceptAdapterView = exceptView
    }

    override fun setItems() {
        fixedModel.addItems(dataSource.getFixedNumber())
        fixedAdapterView.notifyAdapter()

        exceptModel.addItems(dataSource.getExceptNumber())
        exceptAdapterView.notifyAdapter()
    }

    fun getFixedNumber() = dataSource.getFixedNumber()
    fun getExceptNumber() = dataSource.getExceptNumber()

    fun saveFixedNumber(number: ArrayList<String>) = dataSource.saveFixedNumber(number)


    fun saveExceptNumber(number: ArrayList<String>) = dataSource.saveExceptNumber(number)


}