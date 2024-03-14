import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.levp.todo.R

class TareaAdapter(var tarea: List<Tarea>, private val onTareaSelected: (Int) -> Unit) :
    RecyclerView.Adapter<TareaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun getItemCount() = tarea.size

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        holder.render(tarea[position])
        holder.itemView.setOnClickListener{onTareaSelected(position)}

    }

}