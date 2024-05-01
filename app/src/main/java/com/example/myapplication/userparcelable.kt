package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class userparcelable(var namp: String,var code : String, var status: String ) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namp)
        parcel.writeString(code)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<userparcelable> {
        override fun createFromParcel(parcel: Parcel): userparcelable {
            return userparcelable(parcel)
        }

        override fun newArray(size: Int): Array<userparcelable?> {
            return arrayOfNulls(size)
        }
    }
}

private fun <R1> (() -> R1).readString(): Any {
return 0
}
