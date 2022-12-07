package pham.hien.assignment.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageManga {
    @SerializedName("id")
    @Expose
    var id: String = ""

    @SerializedName("tid")
    @Expose
    var idManga: String = ""

    @SerializedName("la")
    @Expose
    var linkAnh: String = ""

    constructor()
    constructor(id: String, idManga: String, linkAnh: String) {
        this.id = id
        this.idManga = idManga
        this.linkAnh = linkAnh
    }

    override fun toString(): String {
        return "ImageManga(id='$id', idManga='$idManga', linkAnh='$linkAnh')"
    }
}