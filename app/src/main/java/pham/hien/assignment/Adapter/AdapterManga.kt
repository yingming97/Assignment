package pham.hien.assignment.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pham.hien.assignment.Activity.Manga.MangaDetailActivity
import pham.hien.assignment.Activity.Manga.MangaEditActivity
import pham.hien.assignment.Model.Manga
import pham.hien.assignment.Model.User
import pham.hien.assignment.R
import pham.hien.assignment.Utils.SharedPrefUtils
import pham.yingming.honeylibrary.Dialog.XacNhanDialog
import ppham.hien.assignment.Utils.gone
import ppham.hien.assignment.Utils.visible

class AdapterManga(
    context: Context,
    listManga: ArrayList<Manga>, private val deleteManga: (Manga) -> Unit,
) :
    RecyclerView.Adapter<AdapterManga.ViewItemManga>() {

    private val TAG = "YingMing"
    private var mContext: Context = context
    private var mListManga: ArrayList<Manga> = listManga
    private lateinit var mUser: User

    @SuppressLint("NotifyDataSetChanged")
    fun setListManga(listManga: ArrayList<Manga>) {
        mListManga = listManga
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewItemManga {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_manga, parent, false)
        return ViewItemManga(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewItemManga, position: Int) {
//        mUser = SharedPrefUtils.getLoginInformationUser(mContext)
        val b = mListManga[position]
        Glide.with(mContext).load(b.link).placeholder(R.drawable.img_book_default)
            .into(holder.imvManga)
        holder.tv_name.text = "${b.id} - ${b.mangaName}"
        holder.tv_tac_gia.text = "${b.user.name}"
        holder.layoutItemManga.setOnClickListener {
            showPopupMenu(holder.imv_menu, b)
        }
        Glide.with(mContext).load(b.user.avatar).placeholder(R.drawable.img_avatar_default)
            .into(holder.imv_avatar)
        if (b.user.id .equals("19") ) {
            holder.imvManga.visible()
            holder.imv_menu.setOnClickListener {
                showPopupMenu(holder.imv_menu, b)
            }
        } else {
            holder.imvManga.gone()
        }
    }

    override fun getItemCount(): Int {
        return mListManga.size
    }

    class ViewItemManga(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imvManga: ImageView
        val tv_name: TextView
        val tv_tac_gia: TextView
        val imv_menu: ImageView
        val imv_avatar: ImageView
        val layoutItemManga: RelativeLayout

        init {
            imvManga = itemView.findViewById(R.id.imv_book)
            tv_name = itemView.findViewById(R.id.tv_name)
            tv_tac_gia = itemView.findViewById(R.id.tv_tac_gia)
            imv_menu = itemView.findViewById(R.id.imv_menu)
            imv_avatar = itemView.findViewById(R.id.imv_avatar)
            layoutItemManga = itemView.findViewById(R.id.layout_item_book)
        }
    }

    private fun showPopupMenu(button: View, b: Manga) {
        val popupWindow: PopupWindow
        val inflater = mContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView: View = inflater.inflate(R.layout.layout_menu, null)
        val tvChiTiet = rootView.findViewById(R.id.tv_chi_tiet) as TextView
        val tvEdit = rootView.findViewById(R.id.tv_edit) as TextView
        val tvDelete = rootView.findViewById(R.id.tv_delete) as TextView
        popupWindow = PopupWindow(
            rootView, 300, RelativeLayout.LayoutParams.WRAP_CONTENT, true)
        popupWindow.showAsDropDown(button)
        tvChiTiet.setOnClickListener {
            val intent = Intent(mContext, MangaDetailActivity::class.java)
//            intent.putExtra("Manga", b)
            mContext.startActivity(intent)
            popupWindow.dismiss()
        }
        tvEdit.setOnClickListener {
            val intent = Intent(mContext, MangaEditActivity::class.java)
//            intent.putExtra("Manga", b)
            mContext.startActivity(intent)
            popupWindow.dismiss()
        }
        tvDelete.setOnClickListener {
            XacNhanDialog(mContext, b.link,
                "Bạn muốn xóa \"${b.mangaName}\"",
                "Dữ liệu đã xóa không thể khôi phục",
                dongY = {
                    deleteManga(b)
                    popupWindow.dismiss()
                },
                huy = {
                    popupWindow.dismiss()
                }).show()
        }


    }
}