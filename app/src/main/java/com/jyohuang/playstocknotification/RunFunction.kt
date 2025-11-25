package com.jyohuang.playstocknotification

class RunFunction {
}

fun main(){
    val names = listOf("Amy", "Bob", "Cathrine")
    //print(names)
//    names.forEach { name ->
//        println("Hello $name")
//    }

//    val nameLengths = names.map{ it.length}
//    println(nameLengths)

//    val shortNames = names.filter { it.length <= 3 }
//    println(shortNames)

    fun greet(name : String) : String = "Hello $name"

    println(greet("ABC"))

}