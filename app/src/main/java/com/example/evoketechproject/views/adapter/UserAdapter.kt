package com.example.evoketechproject.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.evoketechproject.R
import com.example.evoketechproject.database.entity.UserEntity
import com.example.evoketechproject.databinding.RowUserBinding

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userArrayList: ArrayList<UserEntity> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_user, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel: UserEntity = userArrayList[position]
        holder.bind(dataModel)
    }

    class ViewHolder(var itemBinding: RowUserBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: UserEntity) {
            itemBinding.user = user
        }
    }

    fun setData(newData: List<UserEntity>) {
        if (userArrayList.size != 0) {
            val postDiffCallback: PostDiffCallback = PostDiffCallback(userArrayList, newData)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)
            userArrayList.clear()
            userArrayList.addAll(newData)
            diffResult.dispatchUpdatesTo(this)
        } else {
            // first initialization
            userArrayList.addAll(newData)
        }
        notifyDataSetChanged()
    }

    internal class PostDiffCallback(val oldLogs: List<UserEntity>, val newLogs: List<UserEntity>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldLogs.size
        }

        override fun getNewListSize(): Int {
            return newLogs.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLogs[oldItemPosition].id === newLogs[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLogs[oldItemPosition].equals(newLogs[newItemPosition])
        }

    }

}