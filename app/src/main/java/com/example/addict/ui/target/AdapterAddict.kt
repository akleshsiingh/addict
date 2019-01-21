package com.example.addict.ui.target

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.example.addict.R
import com.example.addict.data.entity.Target
import com.example.addict.ui.base.BaseViewHolder
import com.example.addict.utils.afterTextChanged
import com.example.addict.utils.inflateView
import kotlinx.android.synthetic.main.target_row.view.*

class AdapterAddict : RecyclerView.Adapter<BaseViewHolder>() {

    var list = listOf<Target>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = MyViewHolder(p0.inflateView(R.layout.target_row))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) = p0.onBind()

    inner class MyViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun onBind() {
            val target = list[adapterPosition]
            itemView.tvTitle.text = target.title
            itemView.etTarget.setText(target.target.toString())
            itemView.etTarget.afterTextChanged {
                if (!it.isEmpty() && TextUtils.isDigitsOnly(it))
                    target.target = it.toInt()
            }
        }
    }
}



