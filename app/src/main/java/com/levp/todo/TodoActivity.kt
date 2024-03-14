package com.levp.todo

import CategoriaAdapter
import CategoriaTareas
import Tarea
import TareaAdapter
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private val listOfCategorias = listOf(
        CategoriaTareas.Trabajo,
        CategoriaTareas.Personal,
        CategoriaTareas.Escuela,
        CategoriaTareas.Otros
    )

    private val mListOfTarea = mutableListOf(
        Tarea("Trabajo", CategoriaTareas.Trabajo),
        Tarea("Personal", CategoriaTareas.Personal),
        Tarea("Escuela", CategoriaTareas.Escuela),
        Tarea("Otros", CategoriaTareas.Otros),
    )

    private lateinit var rvCateooria:RecyclerView
    private  lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var rvTarea: RecyclerView
    private lateinit var tareaAdapter: TareaAdapter
    private lateinit var fabAgregarTarea: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAgregarTarea.setOnClickListener{
            mostraDialago()
        }
    }

    private fun initComponent() {
        rvCateooria = findViewById(R.id.rvCateogria)
        rvTarea = findViewById(R.id.rvTareas)
        fabAgregarTarea = findViewById(R.id.fabAgregarTarea)
    }

    private fun initUI() {
        categoriaAdapter = CategoriaAdapter(listOfCategorias) { position -> actualizarCategorias(position)}
        rvCateooria.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        rvCateooria.adapter = categoriaAdapter

        tareaAdapter = TareaAdapter(mListOfTarea) { position -> onItemSelected(position) }
        rvTarea.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rvTarea.adapter = tareaAdapter
    }

    private fun mostraDialago() {
        val dialogo = Dialog(this)
        dialogo.setContentView(R.layout.dialog_tareas)

        val btAgragarTarea: Button = dialogo.findViewById(R.id.btAgregarTarea)
        val etAgregarTarea: EditText = dialogo.findViewById(R.id.etNombreTarea)
        val rgCategoria: RadioGroup = dialogo.findViewById(R.id.rgCategorias)

        btAgragarTarea.setOnClickListener {

            val tareaActual = etAgregarTarea.text.toString()

            if(tareaActual.isNotEmpty()){

                val selectedId = rgCategoria.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategoria.findViewById(selectedId)

                val currentCategoria:CategoriaTareas = when(selectedRadioButton.text){
                    getString(R.string.str_persona) -> CategoriaTareas.Personal
                    getString(R.string.str_trabajo) -> CategoriaTareas.Trabajo
                    getString(R.string.str_escuela) -> CategoriaTareas.Escuela
                    else -> CategoriaTareas.Otros

                }

                mListOfTarea.add(Tarea(etAgregarTarea.text.toString(), currentCategoria, false))
                actualizarTarea()
                dialogo.hide()

            }

        }

        dialogo.show()
    }

    private fun onItemSelected(position:Int){
        mListOfTarea[position].isSelected = !mListOfTarea[position].isSelected
        actualizarTarea()
    }

    private fun actualizarCategorias(position: Int){
        listOfCategorias[position].isSelected = !listOfCategorias[position].isSelected
        categoriaAdapter.notifyItemChanged(position)
        actualizarTarea()
    }

    private fun actualizarTarea(){
        val selectedCategorias:List<CategoriaTareas> = listOfCategorias.filter {  it.isSelected }
        val newTareas = mListOfTarea.filter { selectedCategorias.contains(it.categoria) }
        tareaAdapter.tarea = newTareas
        tareaAdapter.notifyDataSetChanged()
    }

}