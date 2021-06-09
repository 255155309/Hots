package com.example.qimo.Msg

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qimo.R

sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)
//获取msg_left和msg_right中的消息控件
class LeftViewHolder(view: View) : MsgViewHolder(view) {
    val leftMsg: TextView = view.findViewById(R.id.leftMsg)
}

class RightViewHolder(view: View) : MsgViewHolder(view) {
    val rightMsg: TextView = view.findViewById(R.id.rightMsg)
}