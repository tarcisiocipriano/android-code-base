package cesar.school.code_base.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import cesar.school.code_base.R
import cesar.school.code_base.databinding.ItemListviewBinding
import cesar.school.code_base.model.User

class UserAdapter(private val context: Context,
                  private val users: MutableList<User>,
                  private val onClickListener: (User?) -> Unit): BaseAdapter() {

    private val photos: TypedArray by lazy {
        context.resources.obtainTypedArray(R.array.photos)
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val user = users[position]
        val holder: ViewHolder
        val linha: View
        if (view == null) {
            val binding = ItemListviewBinding.inflate(LayoutInflater.from(context))
            linha = binding.root
            holder = ViewHolder(binding)
            linha.tag = holder
        } else {
            linha = view
            holder = view.tag as ViewHolder
        }
        holder.name.text = user.name
        holder.photo.setImageDrawable(photos.getDrawable(user.photo))
        holder.photo.setOnClickListener {
            onClickListener(user)
        }
        holder.buttonRemove.setOnClickListener {
            users.removeAt(position)
            notifyDataSetChanged()
        }
        return linha
    }

    override fun getItem(position: Int) = users[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = users.size

    companion object {
        data class ViewHolder(val view: ItemListviewBinding) {
            val name: TextView = view.itemUsername
            val photo: ImageView = view.itemPhoto
            val buttonRemove: Button = view.buttonRemove
        }
    }
}