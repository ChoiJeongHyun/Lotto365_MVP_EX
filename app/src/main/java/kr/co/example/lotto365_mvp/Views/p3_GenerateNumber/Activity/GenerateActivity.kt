package kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_generate.*
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Common.commonset.C
import kr.co.example.lotto365_mvp.R
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvp.Views.In_Common.Adapter.ListPageAdapter
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Activity.Random.RandomGenerateActivity
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Contract.GenerateContract
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Presenter.GeneratePresenter
import kr.co.example.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.example.lotto365_mvp.Widgets.ToolBar

class GenerateActivity : LottoActivity(), GenerateContract.View, ToolBar.OnToolBarClickListener,
    OnItemClickListener {

    private lateinit var presenter: GeneratePresenter
    private lateinit var adapter: ListPageAdapter

    override fun initPresenter() {
        presenter = GeneratePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate)
        Utils.loadAdView(view_activity_generate_adView)
        presenter.setView(this)

        viewInit()
        setData()

    }

    override fun viewInit() {
        super.viewInit()
        view_activity_generate_toolbar.setLeftButton(
            ToolBar.ButtonItem().setTag(C.Tag.TOOLBAR_BACK)
        )
        view_activity_generate_toolbar.setOnToolBarClickListener(this)

        adapter = ListPageAdapter(this)
        presenter.setListAdapterModel(adapter)
        presenter.setListAdapterView(adapter)

        view_activity_generate_listView.layoutManager = LinearLayoutManager(this)
        view_activity_generate_listView.adapter = this.adapter
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

    override fun onItemClick(recyclerView: RecyclerView, view: View, position: Int) {
        when (position) {
            1 -> sendAction(RandomGenerateActivity::class.java)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.releaseView()
    }

}