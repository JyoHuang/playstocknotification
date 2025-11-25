package com.jyohuang.playstocknotification

class RunFunction {
}

fun main(){
    open class Vehicle{
        open fun drive() = println("...")
    }
    class Car : Vehicle(){
        override fun drive() {
            println("123")
        }
    }
    val car = Car()
    car.drive()
}