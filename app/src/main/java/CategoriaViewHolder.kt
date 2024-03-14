import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.levp.todo.R

class CategoriaViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val tvCategoriaNombre: TextView = view.findViewById(R.id.tvCategoriaNombre)
    private val vDivision: View = view.findViewById(R.id.vDivision)
    private val cvCategoria: CardView = view.findViewById(R.id.cvCategoria)

    fun render(categoriaTarea: CategoriaTareas, onCategoriaSelected: (Int) -> Unit){

        val color = if(categoriaTarea.isSelected){
         R.color.colorPrimary
        }else{
            androidx.appcompat.R.color.material_grey_600
        }

        cvCategoria.setCardBackgroundColor(ContextCompat.getColor(cvCategoria.context, color))
        itemView.setOnClickListener{onCategoriaSelected(layoutPosition)}

        when(categoriaTarea){
            CategoriaTareas.Escuela -> {
                tvCategoriaNombre.text = "ESCUELA"
                vDivision.setBackgroundColor(ContextCompat.getColor(vDivision.context,R.color.colorEscuela))
            }
            CategoriaTareas.Otros -> {
                tvCategoriaNombre.text = "OTROS"
                vDivision.setBackgroundColor(ContextCompat.getColor(vDivision.context,R.color.colorOtros))
            }
            CategoriaTareas.Personal -> {
                tvCategoriaNombre.text = "PERSONAL"
                vDivision.setBackgroundColor(ContextCompat.getColor(vDivision.context,R.color.colorPersonal))
            }
            CategoriaTareas.Trabajo -> {
                tvCategoriaNombre.text = "TRABAJO"
                vDivision.setBackgroundColor(ContextCompat.getColor(vDivision.context,R.color.colorTrabajo))
            }
        }
    }
}