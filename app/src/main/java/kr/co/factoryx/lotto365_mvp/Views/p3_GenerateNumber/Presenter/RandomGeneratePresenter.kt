package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Presenter

import android.content.Context
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract.RandomGenerateContract
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Model.LocalRandomDataSource

class RandomGeneratePresenter : RandomGenerateContract.Presenter {

    private lateinit var view: RandomGenerateContract.View
    private lateinit var dataSource: LocalRandomDataSource

    override fun setView(view: RandomGenerateContract.View) {
        super.setView(view)
        this.view = view
    }

    override fun setDataSource(context: Context) {
        super.setDataSource(context)
        this.dataSource = LocalRandomDataSource(context, this@RandomGeneratePresenter)
    }

    override fun releaseView() {
        super.releaseView()
    }

    fun getFixedList() = dataSource.getFixedNumber()
    fun getExceptNumber() = dataSource.getExceptNumber()


}