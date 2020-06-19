package kr.co.factoryx.lotto365_mvp.Views.p2_Main.Activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.factoryx.lotto365.commonset.components.LottoActivity
import kr.co.factoryx.lotto365_mvp.Common.commonset.C
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.ListPageAdapter
import kr.co.factoryx.lotto365_mvp.Views.p2_Main.Contract.MainContract
import kr.co.factoryx.lotto365_mvp.Views.p2_Main.Presenter.MainPresenter
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Activity.GenerateActivity
import kr.co.factoryx.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.factoryx.lotto365_mvp.Widgets.ToolBar

class MainActivity : LottoActivity(), MainContract.View, ToolBar.OnToolBarClickListener,
    OnItemClickListener {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: ListPageAdapter

    override fun initPresenter() {
        presenter = MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.loadAdView(view_activity_main_adView)

        presenter.setView(this)

        viewInit()
        setData()
    }

    override fun viewInit() {
        super.viewInit()
        view_activity_main_toolbar.setRightButton(ToolBar.ButtonItem().setTag(C.Tag.TOOLBAR_MORE))
        view_activity_main_toolbar.setOnToolBarClickListener(this)

        adapter = ListPageAdapter(this)
        presenter.setListAdapterModel(adapter)
        presenter.setListAdapterView(adapter)

        view_activity_main_listView.layoutManager = LinearLayoutManager(this)
        view_activity_main_listView.adapter = this.adapter
        adapter.setOnItemClickListener(this)

        presenter.setItems()

    }

    override fun setData() {
        super.setData()
    }

    override fun onToolBarClick(tag: Any?, view: View?) {
        if (Utils.equals(tag, C.Tag.TOOLBAR_MORE)) {

        }

    }

    override fun onItemClick(recyclerView: RecyclerView, view: View, position: Int) {
        when (position) {
            0 -> {
                sendAction(GenerateActivity::class.java)
            }
            2 -> requestPermissions(C.RequestCode.CAMERA, android.Manifest.permission.CAMERA)
        }
    }

    override fun neverNotAllowed(code: Int, permission: String?) {
        super.neverNotAllowed(code, permission)
        Toast.makeText(this, "QR코드 인식을 위해 카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
    }

    override fun permissionNotAllowed(code: Int, permission: String?) {
        super.permissionNotAllowed(code, permission)
        Toast.makeText(this, "QR코드 인식을 위해 카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
    }

    override fun permissionsAllowed(code: Int) {
        super.permissionsAllowed(code)
        if (Utils.equals(code, C.RequestCode.CAMERA)) {
            //Todo Scan QRCode
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.releaseView()
    }

}