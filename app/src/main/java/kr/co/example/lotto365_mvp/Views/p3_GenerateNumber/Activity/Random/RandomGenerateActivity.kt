package kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Activity.Random

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_random_generate.*
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365.commonset.components.abspopupview.PopupView
import kr.co.example.lotto365_mvp.Common.commonset.C
import kr.co.example.lotto365_mvp.R
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter.FixedAndExceptAdapter
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Contract.RandomGenerateContract
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Popup.NumberPopup
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Presenter.RandomGeneratePresenter
import kr.co.example.lotto365_mvp.Widgets.OnPopupClickListener
import kr.co.example.lotto365_mvp.Widgets.ToolBar

class RandomGenerateActivity : RandomGenerateContract.View, LottoActivity(),
    ToolBar.OnToolBarClickListener, OnPopupClickListener {

    private lateinit var presenter: RandomGeneratePresenter
    private lateinit var fixedAdapter: FixedAndExceptAdapter
    private lateinit var exceptAdapter: FixedAndExceptAdapter

    override fun initPresenter() {
        presenter = RandomGeneratePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_generate)
        Utils.loadAdView(view_random_generate_adView)

        presenter.setView(this)
        presenter.setDataSource(this)

        viewInit()
        setData()
    }

    override fun viewInit() {
        super.viewInit()
        view_random_generate_toolbar.setLeftButton(ToolBar.ButtonItem().setTag(C.Tag.TOOLBAR_BACK))
        view_random_generate_toolbar.setOnToolBarClickListener(this)

        view_random_generate_group_fixed_number.setOnClickListener(this)
        view_random_generate_group_except_number.setOnClickListener(this)
        view_random_generate_btn_generate_number.setOnClickListener(this)

        view_random_generate_group_fixed_number.setAlWaysMyTouch(true)
        view_random_generate_group_except_number.setAlWaysMyTouch(true)

        view_random_generate_except_number_listView.layoutManager = GridLayoutManager(this, 6)
        view_random_generate_fixed_number_listView.layoutManager = GridLayoutManager(this, 6)
        fixedAdapter = FixedAndExceptAdapter(this)
        exceptAdapter = FixedAndExceptAdapter(this)

        presenter.setAdaptersModel(fixedAdapter, exceptAdapter)
        presenter.setAdaptersView(fixedAdapter, exceptAdapter)

        view_random_generate_fixed_number_listView.adapter = this.fixedAdapter
        view_random_generate_except_number_listView.adapter = this.exceptAdapter
    }

    override fun setData() {
        super.setData()

        presenter.setItems()
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v) {
            view_random_generate_group_fixed_number -> {
                showPopup("고정번호", true)
            }
            view_random_generate_group_except_number -> {
                showPopup("제외번호", false)
            }
            view_random_generate_btn_generate_number -> {
                sendAction(RandomNumberActivity::class.java)
            }

        }
    }

    override fun onToolBarClick(tag: Any?, view: View?) {
        if (Utils.equals(tag, C.Tag.TOOLBAR_BACK)) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.releaseView()
    }

    private fun showPopup(s: String, b: Boolean) {
        NumberPopup(this).setTitle(s).setFixed(b).setPresenter(presenter).setOnPopupClickListener(this).show()
    }

    override fun onPopupClick(view: PopupView?, position: Int, item: Any?) {
        if (position == C.Popup.RIGHT) {
            presenter.setItems()
        }
    }


}