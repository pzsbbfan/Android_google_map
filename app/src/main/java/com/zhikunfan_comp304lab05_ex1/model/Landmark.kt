package com.zhikunfan_comp304lab05_ex1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Landmark(
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable
