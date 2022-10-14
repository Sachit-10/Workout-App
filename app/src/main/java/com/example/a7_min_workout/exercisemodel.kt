package com.example.a7_min_workout

import android.widget.ImageButton
import android.widget.ImageView

class exercisemodel(

    var id: Int,
    var name:String,
    var image: Int,
    var iscompleted:Boolean,
    var isselected:Boolean
){
    fun getid() :Int{
        return id
    }

    fun setid(id: Int) {
        this.id= id
    }

    fun getname() :String{
        return name
    }

    fun setname(name: String) {
        this.name= name
    }

    fun getimage() :Int{
        return image
    }

    fun setimage(image: Int) {
        this.image= image
    }

    fun getiscompleted() :Boolean{
        return iscompleted
    }

    fun setiscompleted(iscompleted: Boolean) {
        this.iscompleted= iscompleted
    }

    fun getiselected() :Boolean{
        return isselected
    }

    fun setisselected(isselected: Boolean) {
        this.isselected= isselected
    }
}