import java.util.*

fun main(args: Array<String>) {


    val shopping_list = mutableListOf<String>()


    while (true) {
        println("Enter action (add/display/remove/exit): ")
        val action = readLine()?.lowercase(Locale.getDefault()) ?: ""

        when(action){
            "add"->{
                print("Enter Item: ")
                val item= readLine()?:""
                add(shopping_list,item)

            }
            "remove"-> {
                println("Enter Items to remove: ")
                val removeItem= readLine()?:""
                remove(shopping_list, removeItem)
            }
            "display"->{
                println("Items in the Shopping List")
                display(shopping_list)

            }

            "exit"->{
                println("Exit the Program")
                break
            }
            else-> println("Invalid Action ")
        }


    }
}


fun add(list: MutableList<String>,item:String){
    list.add(item)
    println("$item added to the shopping list.")

}
fun remove(list: MutableList<String>,item:String){
    if (list.remove(item)) {
        println("$item removed from the shopping list.")
    } else {
        println("$item not found in the shopping list.")
    }

}

fun display(list: MutableList<String>){
    if(list.isEmpty()){
        println("Shopping List is Empty")
    }
    else {
        for (i in list) {
            print(" $i ")
            println()
        }
    }
}

