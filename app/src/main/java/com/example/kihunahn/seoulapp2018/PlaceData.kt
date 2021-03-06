package com.example.kihunahn.seoulapp2018

import java.util.*


object PlaceData {
    // 여행 이름으로 저장

    var placeNameArray = ArrayList<String>()
    var placeArray = ArrayList<PictureDTO>()
    fun placeList(): ArrayList<Place> {
        val list = ArrayList<Place>()

        for (i in placeNameArray.indices) {
            val place = Place(placeNameArray.get(i), placeNameArray.get(i).replace("\\s+".toRegex(), "").toLowerCase(), false)

            list.add(place)
        }
        /*
        for (i in placeNameArray.indices) {
            var isFav = false
            if (i == 2 || i == 5) {
                isFav = true
            }
            val place = Place(placeNameArray[i], placeNameArray[i].replace("\\s+".toRegex(), "").toLowerCase(), isFav)

            list.add(place)
        }
        */
        return list
    }
}