package com.example.kihunahn.seoulapp2018

data class CourseDTO(var title : String) {
    var courseName = title
    var positions = PositionDTO(null, null)
    var pictures = PictureDTO(null, null)
}


data class UserInfoDTO(var userID : String) {
    var id = userID
    var courseList = ArrayList<CourseDTO>()
}