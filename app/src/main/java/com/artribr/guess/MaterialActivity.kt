package com.artribr.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_replay_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.ok), { dialog, which ->
                    secretNumber.reset()
                    tv_count.setText(secretNumber.count.toString())
                    ed_number.setText("")
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .show()

        }

        tv_count.setText(secretNumber.count.toString())

        Log.d(TAG, "secret:${secretNumber.secret}")
    }

    fun check(view: View) {
        val n: Int = ed_number.text.toString().toInt()
        val diff: Int = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)

        if (diff > 0) {
            message = getString(R.string.smaller)
        } else if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff == 0 && secretNumber.count < 3) {
            message = getString(R.string.excellent_the_number_is) + secretNumber.secret.toString()
        }


        tv_count.setText(secretNumber.count.toString())

//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()


    }

}
