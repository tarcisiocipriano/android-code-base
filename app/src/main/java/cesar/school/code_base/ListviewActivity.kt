package cesar.school.code_base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cesar.school.code_base.adapter.UserAdapter
import cesar.school.code_base.databinding.ActivityListviewBinding
import cesar.school.code_base.model.User

class ListviewActivity : AppCompatActivity() {

    companion object {
        const val USER_ID = "user_id"
    }

    private val listUsers = mutableListOf(
        User("Tarcisio", 0),
        User("Daivid", 1),
        User("Giulia", 2),
        User("Gabriel", 3)
    )

    private val userAdapter by lazy {
        UserAdapter(this, listUsers) { user ->
            val intent = Intent(this, ListviewDetailsActivity::class.java)
            intent.putExtra(USER_ID, user)
            startActivity(intent)
        }
    }

    private lateinit var binding: ActivityListviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup listview
        binding.usersListView.adapter = userAdapter

        // insert new item
        binding.buttonInsertUser.setOnClickListener {
            val name = binding.inputNewUsername.text.toString()
            if (!name.isNullOrEmpty()) {
                listUsers.add(User(name, (0..3).random()))
                userAdapter.notifyDataSetChanged()
            }
        }
    }
}