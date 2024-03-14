sealed class CategoriaTareas(var isSelected:Boolean = true) {

    object Personal:CategoriaTareas()
    object Trabajo:CategoriaTareas()
    object Escuela:CategoriaTareas()
    object Otros:CategoriaTareas()

}