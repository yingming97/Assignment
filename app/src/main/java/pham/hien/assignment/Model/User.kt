package pham.hien.assignment.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("ui")
    @Expose
    var id: String =""

    @SerializedName("na")
    @Expose
    var name: String = ""

    @SerializedName("em")
    @Expose
    var email: String = ""

    @SerializedName("av")
    @Expose
    var avatar: String = ""

    @SerializedName("fi")
    @Expose
    var firebaseId: String = ""

    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email', avatar='$avatar', firebaseId='$firebaseId')"
    }
}