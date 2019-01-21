package com.example.healthnode.ui.dashboard

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.healthnode.R
import com.example.healthnode.data.entity.Target
import com.example.healthnode.ui.base.BaseViewHolder
import com.example.healthnode.utils.click
import com.example.healthnode.utils.inflateView
import kotlinx.android.synthetic.main.addict_grid_row.view.*

class AdapterAddictionGrid(private val callback: (Target) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {

    var list = listOf<Target>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var height: Int = 9

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = MyViewHolder(p0.inflateView(R.layout.addict_grid_row))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) = p0.onBind()

    inner class MyViewHolder(itemView: View) : BaseViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        override fun onBind() {
            val target = list[adapterPosition]

            itemView.tvTitle.text = target.title
            itemView.tvCurrent.text = "C: ${target.current}"
            itemView.tvTarget.text = "T: ${target.target}"

            itemView.click { callback.invoke(target) }
        }
    }
}