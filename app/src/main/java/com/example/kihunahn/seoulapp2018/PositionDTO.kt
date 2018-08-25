package com.example.kihunahn.seoulapp2018

import java.util.*

data class PositionDTO(var lat: LinkedList<Double>?, var lon: LinkedList<Double>?)


data class PictureDTO(var lat: LinkedList<Double>? = null, var lon: LinkedList<Double>? = null, var uri: LinkedList<String>? = null)