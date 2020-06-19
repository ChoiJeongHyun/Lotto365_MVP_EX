package kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.Contract

import kr.co.factoryx.lotto365_mvp.BaseAdapterContract

interface ListPageAdapterContract {

    interface View : BaseAdapterContract.View {
        fun notifyAdapter()
    }

    interface Model : BaseAdapterContract.Model {

        fun addItems(items: ArrayList<String>)

    }

}