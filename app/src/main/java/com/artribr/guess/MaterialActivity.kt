package com.artribr.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    private val REQUEST_RECORD: Int = 100;
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            replay()

        }

        tv_count.setText(secretNumber.count.toString())

        Log.d(TAG, "onCreate:" + secretNumber.secret)

        val count = getSharedPreferences("guess", Context.MODE_PRIVATE).getInt("REC_COUNTER", -1)
        val nickName =
            getSharedPreferences("guess", Context.MODE_PRIVATE).getString("REC_NICKNAME", null);
        Log.d(TAG, "data: " + nickName + "/" + count)
    }

    private fun replay() {
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
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
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
//                    startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            })
            .show()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RECORD) {
            if (resultCode == Activity.RESULT_OK) {
                val nickname = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult nickname:"+nickname);
                replay()
            }
        }
    }
}
