package com.example.kihunahn.seoulapp2018

data class CourseDTO(var user : String, var title : String) {
    var userName = user
    var courseName = title
    var score = 0

    var PositionList : ArrayList<PositionDTO> = ArrayList()
    var PictureList : ArrayList<PictureDTO> = ArrayList()

    var stamp = 0
}

data class UserInfoDTO(var userID : String) {
    var id = userID
    var courseList = ArrayList<CourseDTO>()
}

data class PositionDTO(var lat: ArrayList<Double>? = null, var lon: ArrayList<Double>? = null)


data class PictureDTO(var position: PositionDTO? = null, var uri: ArrayList<String>? = null)