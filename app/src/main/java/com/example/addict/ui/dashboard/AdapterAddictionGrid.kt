package com.example.addict.ui.dashboard

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.addict.R
import com.example.addict.data.entity.Target
import com.example.addict.ui.base.BaseViewHolder
import com.example.addict.utils.click
import com.example.addict.utils.inflateView
import com.example.addict.utils.longToast
import kotlinx.android.synthetic.main.addict_grid_row.view.*

class AdapterAddictionGrid(private val callback: (Target) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {

    var list = listOf<Target>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var height: Float = 0f

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

            itemView.click {
                if (target.current < target.target) {
                    target.current = target.current + 1
                    itemView.tvCurrent.text =
                            "C: ${target.current}" // by updating from here we dont have to notify our list
                    callback.invoke(target)
                } else
                    itemView.context.longToast("You are exceeding your limit")
            }
            if (height != 0f)
                itemView.layoutParams.height = height.toInt()
        }
    }
}