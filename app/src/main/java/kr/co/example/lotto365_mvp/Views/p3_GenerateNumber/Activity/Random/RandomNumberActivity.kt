package kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Activity.Random

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_random_number.*
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Common.commonset.C
import kr.co.example.lotto365_mvp.R
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter.RandomORDreamNumberAdapter
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Contract.RandomNumberContract
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Presenter.RandomNumberPresenter
import kr.co.example.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.example.lotto365_mvp.Widgets.ToolBar

class RandomNumberActivity : LottoActivity(), ToolBar.OnToolBarClickListener, OnItemClickListener,
    RandomNumberContract.View {

    private lateinit var presenter: RandomNumberPresenter
    private lateinit var adapter: RandomORDreamNumberAdapter

    override fun initPresenter() {
        presenter = RandomNumberPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)

        Utils.loadAdView(view_random_number_adView)
        presenter.setView(this)
        presenter.setDataSource(this)

        viewInit()
        setData()
    }

    override fun viewInit() {
        super.viewInit()
        view_random_number_toolbar.setLeftButton(ToolBar.ButtonItem().setTag(C.Tag.TOOLBAR_BACK))
        view_random_number_toolbar.setOnToolBarClickListener(this)

        view_random_number_btn_again_generate_number.setOnClickListener(this)
        view_random_number_btn_all_save_number.setOnClickListener(this)

        view_random_number_listView.layoutManager = LinearLayoutManager(this)
        adapter = RandomORDreamNumberAdapter(this)

        presenter.setAdapterModel(adapter)
        presenter.setAdapterView(adapter)

        view_random_number_listView.adapter = this.adapter
        adapter.setOnItemClickListener(this)

    }

    override fun setData() {
        super.setData()
        presenter.setItems()
    }

    override fun onToolBarClick(tag: Any?, view: View?) {
        if (Utils.equals(tag, C.Tag.TOOLBAR_BACK)) {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v) {
            view_random_number_btn_again_generate_number -> {
                presenter.setItems()
            }
            view_random_number_btn_all_save_number -> {
                presenter.saveAllNumber()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.releaseView()
    }

    override fun onItemClick(recyclerView: RecyclerView, view: View, position: Int) {
        presenter.saveNumber(position)
    }


}