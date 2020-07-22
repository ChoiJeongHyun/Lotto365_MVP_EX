package kr.co.example.lotto365_mvp

import android.content.Context

class BaseContract {

    interface Presenter<T> {

        fun setView(view: T)
        fun setDataSource(context: Context)
        fun releaseView()

    }

    interface View {

    }


}