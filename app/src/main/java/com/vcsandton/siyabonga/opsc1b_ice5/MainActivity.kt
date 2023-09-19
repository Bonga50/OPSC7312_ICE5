package com.vcsandton.siyabonga.opsc1b_ice5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addEntry = findViewById<Button>(R.id.btnEterDiary)
        val getEntry = findViewById<Button>(R.id.btnShowDiary)
        val enterDiary = findViewById<EditText>(R.id.enterDiary)
        val showDiary = findViewById<TextView>(R.id.txtShowDiary)

        addEntry.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            var diary = enterDiary.text.toString()

            // calling method to add
            // name to our database
            db.addName(diary)

            // Toast to message on the screen
            Toast.makeText(this, diary + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterDiary.text.clear()

        }

        getEntry.setOnClickListener{
            showDiary.text = "";
            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)
            val cursor = db.getName()

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val nameIndex = cursor.getColumnIndex(DBHelper.DIARY_ENTRY)
                    if (nameIndex != -1) {
                        showDiary.append(cursor.getString(nameIndex) + "\n")
                    }
                } while (cursor.moveToNext())
                cursor.close()
            }
        }

    }
}