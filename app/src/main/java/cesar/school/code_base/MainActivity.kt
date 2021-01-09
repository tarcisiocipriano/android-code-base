package cesar.school.code_base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cesar.school.code_base.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
    }
}