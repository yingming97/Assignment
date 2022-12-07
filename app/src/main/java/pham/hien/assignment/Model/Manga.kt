package pham.hien.assignment.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Manga : Serializable {
    @SerializedName("id")
    @Expose
    var id: String = ""

    @SerializedName("ten_truyen")
    @Expose
    var mangaName: String = ""

    @SerializedName("mtn")
    @Expose
    var des: String = ""

    @SerializedName("user")
    @Expose
    var user: User = User()

    @SerializedName("ab")
    @Expose
    var link: String =
        "https://firebasestorage.googleapis.com/v0/b/networkinglab18604.appspot.com/o/book_default.png?alt=media&token=28f93820-9d45-48b6-8087-46730bc85670"

    @SerializedName("arrI")
    @Expose
    var listAnh: ArrayList<ImageManga>? = ArrayList()

    constructor()
    constructor(
        id: String,
        mangaName: String,
        des: String,
        user: User,
        link: String,
        listAnh: ArrayList<ImageManga>?,
    ) {
        this.id = id
        this.mangaName = mangaName
        this.des = des
        this.user = user
        this.link = link
        this.listAnh = listAnh
    }

    override fun toString(): String {
        return "Manga(id='$id', mangaName='$mangaName', des='$des', user=$user, link='$link', listAnh=$listAnh)"
    }
}