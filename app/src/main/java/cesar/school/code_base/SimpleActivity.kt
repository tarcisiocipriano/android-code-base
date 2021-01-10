package cesar.school.code_base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cesar.school.code_base.databinding.ActivitySimpleBinding
import cesar.school.code_base.model.Car

class SimpleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.greetings.text = this.getString(R.string.username_message_empty)

        val name = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_NAME_EXTRA_ID)
        val car = intent.getParcelableExtra<Car>(MainActivity.MAIN_Activity_CAR_EXTRA_ID)

        if (!name.isNullOrEmpty()) {
            binding.greetings.text = getString(R.string.username_greetings, name)
        }

        if (car != null) {
            binding.greetings.text = getString(R.string.car_greetings, car.name, car.model, car.year.toString())
        }
    }
}