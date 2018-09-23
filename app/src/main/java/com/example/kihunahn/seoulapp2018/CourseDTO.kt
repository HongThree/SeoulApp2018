package com.example.kihunahn.seoulapp2018

data class CourseDTO(var userId : String? = null,
                     var CourseName : String? = null,
                     var thumbnail : String? = null,
                     var PositionsList : PositionDTO? = null,
                     var PictureList : ArrayList<PositionDTO>? = null)//,
//var stampList : ArrayList<String>? = null)
//var Picture_uriList : ArrayList<String>? = null: Serializable

data class UserInfoDTO(var userID : String) {
    var id = userID
    var courseList = ArrayList<CourseDTO>()
}

data class PositionDTO(var lat: ArrayList<Double>? = null, var lon: ArrayList<Double>? = null)


data class PictureDTO(var position: PositionDTO? = null, var uri: ArrayList<String>? = null)