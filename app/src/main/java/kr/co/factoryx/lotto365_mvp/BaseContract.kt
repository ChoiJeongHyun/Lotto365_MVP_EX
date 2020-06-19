package kr.co.factoryx.lotto365_mvp

import android.content.Context
import android.view.View

class BaseContract {

    interface Presenter<T> {

        fun setView(view: T)
        fun setDataSource(context: Context)
        fun releaseView()

    }

    interface View {

    }


}