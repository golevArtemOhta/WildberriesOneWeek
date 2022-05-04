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

    /*Комментарии к экрану:
    1.3. На данном экране происходит показ контактов. После нажатия кнопки "READ CONTACTS"
    на экране появляются все контакты, забитые в смартфон.

    1.4. Content provider используется в приложениях, где необходим обмен информацией.
    Популярные приложения использующие Content provider:
    - Мессенджеры (Whats'up, telegram)
    - Банковские приложения (Сбербанк, Тинькофф, Альфа-банк, Тинькофф инвестиции)
    [доступ к контактам необходим например для совершения перевода]
     */

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

        with(binding) {
            buttonRead.setOnClickListener {
                val hasReadContactPermission =
                    ContextCompat.checkSelfPermission(
                        this@ContentProviderActivity,
                        Manifest.permission.READ_CONTACTS
                    )
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
        val contacts = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        while (contacts!!.moveToNext()) {
            val name =
                contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number =
                contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val obj = Contact()
            obj.name = name
            obj.number = number

            contactList.add(obj)
        }
        binding.rcViewContacts.adapter = ContactAdapter(contactList, this)
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