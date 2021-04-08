package com.lepu.libbase.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lepu.android.common.data.model.UserGithub
import com.lepu.libbase.R

class UserListAdapter() : PagedListAdapter<UserGithub, UserListAdapter.UserViewHolder>(object :
    DiffUtil.ItemCallback<UserGithub>() {
    override fun areItemsTheSame(oldItem: UserGithub, newItem: UserGithub): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserGithub, newItem: UserGithub): Boolean {
        return oldItem.login == newItem.login
    }

}) {


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = null

        init {
            tv = itemView.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.tv?.text = getItem(position)?.login ?: "loading..."
    }

}