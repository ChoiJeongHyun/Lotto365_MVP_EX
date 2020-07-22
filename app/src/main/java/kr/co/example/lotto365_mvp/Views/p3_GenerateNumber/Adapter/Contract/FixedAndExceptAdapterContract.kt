package kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract

import kr.co.example.lotto365_mvp.BaseAdapterContract

interface FixedAndExceptAdapterContract {

    interface View : BaseAdapterContract.View {
        fun notifyAdapter()
    }

    interface Model : BaseAdapterContract.Model {
        fun addItems(items: ArrayList<String>)
    }

}