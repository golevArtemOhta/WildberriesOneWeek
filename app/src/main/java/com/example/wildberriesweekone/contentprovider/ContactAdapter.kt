package com.example.wildberriesweekone.contentprovider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wildberriesweekone.R
import com.example.wildberriesweekone.databinding.ContactItemBinding

class ContactAdapter(items: List<Contact>, ctx: Context) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    private var list = items
    private var context = ctx

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ContactItemBinding.bind(itemView)

        val name = binding.tvName
        val number = binding.tvPhoneNumber
    }

}