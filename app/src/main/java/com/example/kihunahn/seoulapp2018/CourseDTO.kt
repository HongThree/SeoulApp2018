package com.example.kihunahn.seoulapp2018

import java.io.Serializable

data class CourseDTO(var userId : String? = null,
                     var CourseName : String? = null,
                     var lat : ArrayList<Double>? = null,
                     var lng : ArrayList<Double>? = null,
                     var PictureList : ArrayList<String>? = null,
                     var stampList : ArrayList<Boolean>? = null )
    : Serializable

data class PictureDTO(var name: String? = null, var uris: ArrayList<String>? = null)