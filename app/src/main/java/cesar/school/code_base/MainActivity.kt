package cesar.school.code_base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cesar.school.code_base.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_ACTIVITY_NAME_EXTRA_ID = "name"
    }

    private lateinit var binding: ActivityMainBinding // var to later initiate the binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // why need to inflate?
        setContentView(binding.root)  // changed from R.layout.activity_main to binding.root, why?

        binding.buttonViewBinding.setOnClickListener {
            Toast.makeText(this, this.getString(R.string.button_view_binding_working), Toast.LENGTH_SHORT).show()
        }

        binding.buttonOpenActivity.setOnClickListener {
            val simpleActivity = Intent(this, SimpleActivity::class.java)
            startActivity(simpleActivity)
        }

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
    }
}