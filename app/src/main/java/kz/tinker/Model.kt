package kz.tinker

class Model(val name:String, user:Int, age: Int) {
    val imageUrl="https://picsum.photos/150?random=$age"
    val userUrl="https://picsum.photos/150?random=$user"
}