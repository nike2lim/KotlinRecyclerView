package kr.co.chunjae.android.kotlinrecyclerview

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class TestAdapter(val context : Context, var items : ArrayList<DataVO>) : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    interface itemClick {
        fun onItemClick(v : View, position : Int)
    }

    var onItemClickListener : itemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun deleteItem(pos : Int) {
        items.removeAt(pos)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.get(position).let {

            with(holder) {
                val drawable = it.imgId
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.background = context.getDrawable(drawable)
                }else {
                    imageView.background = context.resources.getDrawable(drawable)
                }
                textView.text = it.text
            }


            onItemClickListener.let {
                holder.itemView.setOnClickListener{ v-> onItemClickListener?.onItemClick(v, position)}
            }
        }
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var imageView = view.image_view
        var textView = view.text_view
    }
}