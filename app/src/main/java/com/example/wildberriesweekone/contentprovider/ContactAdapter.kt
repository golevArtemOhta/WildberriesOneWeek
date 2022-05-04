package com.example.wildberriesweekone.contentprovider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wildberriesweekone.R
import com.example.wildberriesweekone.databinding.ContactItemBinding

class ContactAdapter(
    private var items: List<Contact>,
    contentProviderActivity: ContentProviderActivity
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.contact_item, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.number.text = items[position].number
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ContactItemBinding.bind(itemView)

        val name = binding.tvName
        val number = binding.tvPhoneNumber
    }

}