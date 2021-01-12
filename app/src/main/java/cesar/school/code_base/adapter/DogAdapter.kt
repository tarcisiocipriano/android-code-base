package cesar.school.code_base.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cesar.school.code_base.R
import cesar.school.code_base.databinding.ItemRecyclerviewBinding
import cesar.school.code_base.model.Dog


class DogAdapter(private val context: Context,
                 private val dogs: List<Dog>,
                 private val callback: (Dog) -> Unit) : RecyclerView.Adapter<DogAdapter.VH>() {

    private val dogsPhotos: TypedArray by lazy {
        context.resources.obtainTypedArray(R.array.dog_photos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context))
        val vh = VH(binding)
        vh.itemView.setOnClickListener {
            val dog = dogs[vh.adapterPosition]
            callback(dog)
        }
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (name, photo) = dogs[position]
        holder.dogName.text = name
        holder.dogPhoto.setImageDrawable(dogsPhotos.getDrawable(photo))
    }

    override fun getItemCount(): Int = dogs.size

    class VH(itemView: ItemRecyclerviewBinding): RecyclerView.ViewHolder(itemView.root) {
        val dogName: TextView = itemView.itemDogName
        val dogPhoto: ImageView = itemView.itemDogPhoto
    }
}