package com.example.wildberriesweekone.contentprovider

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wildberriesweekone.databinding.ActivityContentProviderBinding
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat
import android.widget.Toast


class ContentProviderActivity : AppCompatActivity() {
    lateinit var binding: ActivityContentProviderBinding

    private val REQUEST_CODE_READ_CONTACTS = 1
    private var READ_CONTACTS_GRANTED = false

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Content provider"

        binding.rcViewContacts.layoutManager = LinearLayoutManager(this)

        with(binding){
            buttonRead.setOnClickListener {
                val hasReadContactPermission =
                    ContextCompat.checkSelfPermission(this@ContentProviderActivity, Manifest.permission.READ_CONTACTS)
                if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
                    READ_CONTACTS_GRANTED = true
                } else {
                    ActivityCompat.requestPermissions(
                        this@ContentProviderActivity,
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        REQUEST_CODE_READ_CONTACTS
                    )
                }
                if (READ_CONTACTS_GRANTED) {
                    loadContacts()
                }
            }
        }

    }

    @SuppressLint("Range")
    private fun loadContacts() {
        val contactList: MutableList<Contact> = ArrayList()
        val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null, null, null)
        while (contacts!!.moveToNext()){
            val name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val obj = Contact()
            obj.name = name
            obj.number = number

            contactList.add(obj)
        }
        binding.rcViewContacts.adapter = ContactAdapter(contactList, this@ContentProviderActivity)
        contacts.close()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_CONTACTS) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                READ_CONTACTS_GRANTED = true
            }
        }
        if (READ_CONTACTS_GRANTED) {
            loadContacts()
        } else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show()
        }
    }

}