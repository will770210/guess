package com.artribr.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret:${secretNumber.secret}")
    }

    fun check(view: View) {
        val n: Int = ed_number.text.toString().toInt()
        val diff: Int = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
        if (diff > 0) {
            message = getString(R.string.bigger)
        } else if (diff < 0) {
            message = getString(R.string.smaller)
        }

//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}
