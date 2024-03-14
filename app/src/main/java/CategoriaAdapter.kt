import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.levp.todo.R

class CategoriaAdapter(private val categorias: List<CategoriaTareas>, private val onCategoriaSelected: (Int) -> Unit) :
    RecyclerView.Adapter<CategoriaViewHolder>() {
    /*monta una vista para que se pueda pintar la informacion*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea_categoria, parent, false)

        return CategoriaViewHolder(view)
    }

    /*Retorna la cantidad items que se mostrara en la lista*/
    override fun getItemCount() = categorias.size

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.render(categorias[position], onCategoriaSelected)
    }
}