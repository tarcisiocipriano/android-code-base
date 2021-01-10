package cesar.school.code_base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.Toast
import cesar.school.code_base.databinding.ActivityMainBinding
import cesar.school.code_base.model.Car

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_ACTIVITY_NAME_EXTRA_ID = "name"
        const val MAIN_Activity_CAR_EXTRA_ID = "car"

        const val MAIN_ACTIVITY_VALUE01_EXTRA_ID = "value01"
        const val MAIN_ACTIVITY_VALUE02_EXTRA_ID = "value02"
        const val MAIN_ACTIVITY_SUM_REQUEST_CODE = 1
        const val MAIN_ACTIVITY_RESULT_EXTRA_ID = ""

        const val TAG = "lifecycle"
        const val SAVED_INSTANCE_EXTRA_ID = "saved_instance_extra_id"
    }

    private lateinit var binding: ActivityMainBinding // var to later initiate the binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // why need to inflate?
        setContentView(binding.root)  // changed from R.layout.activity_main to binding.root, why?

        // test binding
        binding.buttonViewBinding.setOnClickListener {
            Toast.makeText(this, this.getString(R.string.button_view_binding_working), Toast.LENGTH_SHORT).show()
        }

        // set the state restored in the view
        if (savedInstanceState != null) {
            val saved = savedInstanceState.getString(SAVED_INSTANCE_EXTRA_ID)
            binding.labelState.text = saved
            Log.d(TAG, "onCreate not null: $saved")
        }

        // increment state
        binding.buttonSetContent.setOnClickListener {
            var current = binding.labelState.text.toString().toInt()
            current++
            binding.labelState.text = current.toString()
        }

        // open activity
        binding.buttonOpenActivity.setOnClickListener {
            val simpleActivity = Intent(this, SimpleActivity::class.java)
            startActivity(simpleActivity)
        }

        // open activity with parameters
        binding.buttonActivityParams.setOnClickListener {
            val name = binding.editTextUsername.text.toString()
            if (!name.isNullOrEmpty()) {
                val simpleActivity = Intent(this, SimpleActivity::class.java)
                simpleActivity.putExtra(MAIN_ACTIVITY_NAME_EXTRA_ID, name)
                startActivity(simpleActivity)
            } else {
                Toast.makeText(this, getString(R.string.username_empty), Toast.LENGTH_LONG).show()
            }
        }

        // open activity with objects
        binding.buttonActivityObject.setOnClickListener {
            val simpleActivity = Intent(this, SimpleActivity::class.java)
            simpleActivity.putExtra(MAIN_Activity_CAR_EXTRA_ID, Car("Unão", "Fiat Uno", 2005))
            startActivity(simpleActivity)
        }

        // open activity result
        binding.buttonSum.setOnClickListener {
            val value1 = binding.inputValue1.text.toString()
            val value2 = binding.inputValue2.text.toString()
            if (!value1.isNullOrEmpty() && !value2.isNullOrEmpty()) {
                val resultActivity = Intent(this, ResultActivity::class.java)
                resultActivity.putExtra(MAIN_ACTIVITY_VALUE01_EXTRA_ID, value1)
                resultActivity.putExtra(MAIN_ACTIVITY_VALUE02_EXTRA_ID, value2)
                startActivityForResult(resultActivity, MAIN_ACTIVITY_SUM_REQUEST_CODE)
            } else {
                Toast.makeText(this, this.getString(R.string.no_values_message), Toast.LENGTH_SHORT).show()
            }
        }

        // implicit intent to open the browser
        binding.buttonBrowser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.google.com")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, getString(R.string.implicit_intent_browser)))
            } else {
                Toast.makeText(this, getString(R.string.implicit_intent_browser_option_not_found), Toast.LENGTH_SHORT).show()
            }
        }

        // implicit intent to share content
        binding.buttonShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.implicit_intent_share))
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, getString(R.string.implicit_intent_share_option)));
            } else {
                Toast.makeText(this, getString(R.string.implicit_intent_share_option_not_found), Toast.LENGTH_SHORT).show()
            }
        }

        // implicit intent to set alarms
        binding.buttonAlarm.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.implicit_intent_alarm))
                putExtra(AlarmClock.EXTRA_HOUR, 14)
                putExtra(AlarmClock.EXTRA_MINUTES, 30)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.implicit_intent_alarm_option_not_found),Toast.LENGTH_SHORT).show()
            }
        }
    }

    // activity result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (MAIN_ACTIVITY_SUM_REQUEST_CODE == requestCode) {
                val result = data?.getStringExtra(MAIN_ACTIVITY_RESULT_EXTRA_ID)
                Toast.makeText(this, this.getString(R.string.sum_result, result), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_INSTANCE_EXTRA_ID, this.binding.labelState.text.toString())
        Log.d(TAG, "onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val saved = savedInstanceState.getString(SAVED_INSTANCE_EXTRA_ID)
        Log.d(TAG, "onRestoreInstanceState(): $saved")
    }
}