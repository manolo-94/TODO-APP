import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.levp.todo.R

class TareaViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val cbTarea:CheckBox = view.findViewById(R.id.cbTarea)
    private val tvTarea:TextView = view.findViewById(R.id.tvNombreTarea)


    fun render(tarea: Tarea){
        if(tarea.isSelected){
            tvTarea.paintFlags = tvTarea.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTarea.paintFlags = tvTarea.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        val color = when(tarea.categoria){
            CategoriaTareas.Escuela -> R.color.colorEscuela
            CategoriaTareas.Otros -> R.color.colorOtros
            CategoriaTareas.Personal -> R.color.colorPersonal
            CategoriaTareas.Trabajo -> R.color.colorTrabajo
        }

        cbTarea.isChecked = tarea.isSelected
        cbTarea.buttonTintList = ColorStateList.valueOf(
         ContextCompat.getColor(tvTarea.context, color)
        )

        tvTarea.text = tarea.nombre
    }
}