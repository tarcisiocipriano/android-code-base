package cesar.school.code_base

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}