package com.artribr.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    val TAG = "RecordActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count: Int = intent.getIntExtra("COUNTER", -1)
        tv_counter.setText(count.toString())

        save.setOnClickListener { view ->
            val nickname: String = nickname.text.toString();
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER",count)
                .putString("REC_NICKNAME",nickname)
                .commit()

            val intent = Intent()
            intent.putExtra("NICK", nickname)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
