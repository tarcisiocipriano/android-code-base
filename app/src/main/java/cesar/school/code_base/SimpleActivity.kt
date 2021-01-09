package cesar.school.code_base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cesar.school.code_base.databinding.ActivitySimpleBinding

class SimpleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_NAME_EXTRA_ID)
        binding.greetings.text = if (!name.isNullOrEmpty()) {
            getString(R.string.username_greetings, name)
        } else getString(R.string.username_message_empty)
    }
}