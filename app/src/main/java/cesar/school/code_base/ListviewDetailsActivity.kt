package cesar.school.code_base

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cesar.school.code_base.databinding.ActivityListviewDetailsBinding
import cesar.school.code_base.model.User

class ListviewDetailsActivity : AppCompatActivity() {

    private val photos: TypedArray by lazy {
        resources.obtainTypedArray(R.array.photos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListviewDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getParcelableExtra(ListviewActivity.USER_ID) ?: User("",0)
        binding.itemDetailUsername.text = user.name
        binding.itemDetailPhoto.setImageDrawable(photos.getDrawable(user.photo))
    }
}