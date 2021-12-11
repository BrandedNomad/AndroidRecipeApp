package com.example.foody

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.net.URL
import kotlin.reflect.typeOf

data class Id(
    @SerializedName("id")
    val id:Int
)


fun main(){
    println("hello world")

    var apiKey:String = "104370adb0394ea0b9f106b488363186"
    var endpoint:String = "https://api.spoonacular.com/recipes/complexSearch?apiKey=${apiKey}&query=pasta&maxFat=25&number=2"


    val json = try { URL(endpoint).readText() } catch (e: Exception) { println(e) }
    if(json is String){
        println("Its a string")
        var gson:Gson = Gson()

        var result = gson.fromJson<Id>(json,Id::class.java)
        println(result.id)
    } else {
        println("Nope")
    }

}

