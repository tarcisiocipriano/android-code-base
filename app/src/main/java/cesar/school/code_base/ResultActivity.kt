package cesar.school.code_base

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val value1 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_VALUE01_EXTRA_ID)
        val value2 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_VALUE02_EXTRA_ID)

        var result = "0"
        if (value1 != null && value2 != null) {
            result = (value1.toDouble() + value2.toDouble()).toString()
            findViewById<TextView>(R.id.label_text_result).text = result
        }

        findViewById<Button>(R.id.button_return_value).setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_RESULT_EXTRA_ID, result)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}