package cesar.school.code_base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import cesar.school.code_base.adapter.DogAdapter
import cesar.school.code_base.databinding.ActivityRecyclerviewBinding
import cesar.school.code_base.model.Dog

class RecyclerviewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerviewBinding

    private val listDogs = mutableListOf(
        Dog("Tom", 0),
        Dog("Piui", 1),
        Dog("Toto", 2),
        Dog("Pluto", 3),
        Dog("Mailow", 4)
    )

    private val dogAdapter = DogAdapter(this, listDogs, this::onDogClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup recyclerview
        binding.dogsRecyclerView.adapter = dogAdapter
        val layoutManager = GridLayoutManager(this, 2)
        // this code bellow can be used to show the first item expanded to 2 columns
//        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return if (position == 0) 2 else 1
//            }
//        }
        binding.dogsRecyclerView.layoutManager = layoutManager

        // setup insert button
        binding.buttonInsertDog.setOnClickListener {
            val name = binding.inputNewDogName.text.toString()
            if (!name.isNullOrEmpty()) {
                listDogs.add(Dog(name, (0..4).random()))
                dogAdapter.notifyItemInserted(listDogs.lastIndex)
            }
        }
    }

    private fun onDogClickListener(dog: Dog) {
        Toast.makeText(this, "Dog: ${dog.name} photo: ${dog.photo}", Toast.LENGTH_SHORT).show()
    }
}