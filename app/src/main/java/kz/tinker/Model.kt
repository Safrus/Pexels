package kz.tinker

data class Model(val name:String, val user:Int,  val age: Int) {
    val imageUrl="https://picsum.photos/150?random=$age"
    val userUrl="https://picsum.photos/150?random=$user"
}