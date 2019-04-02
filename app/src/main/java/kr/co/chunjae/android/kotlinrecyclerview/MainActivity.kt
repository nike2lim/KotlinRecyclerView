package kr.co.chunjae.android.kotlinrecyclerview

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mData : ArrayList<DataVO>
    lateinit var mRecyclerView : RecyclerView
    lateinit var mAdapter : TestAdapter
    val mContext : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addDummyListData()

        mAdapter = TestAdapter(this, mData)
        mRecyclerView = recycler_view.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(mContext)
            mAdapter.onItemClickListener = object : TestAdapter.itemClick {

                override fun onItemClick(v: View, position: Int) {
                    val str = mData.get(position).text
                    Toast.makeText(mContext, "$str 이 눌렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val swipeCallback = object : SwipeCallback(this) {
            override fun onSwiped(p0: RecyclerView.ViewHolder, position: Int) {
                mAdapter.deleteItem(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    fun addDummyListData() {
        mData = mutableListOf<DataVO>(
            DataVO(R.drawable.menu1, "메뉴1"),
            DataVO(R.drawable.menu2, "메뉴2"),
            DataVO(R.drawable.menu3, "메뉴3"),
            DataVO(R.drawable.menu4, "메뉴4"),
            DataVO(R.drawable.menu5, "메뉴5"),
            DataVO(R.drawable.menu6, "메뉴6"),
            DataVO(R.drawable.menu7, "메뉴7")
        ) as ArrayList<DataVO>


    }

}
