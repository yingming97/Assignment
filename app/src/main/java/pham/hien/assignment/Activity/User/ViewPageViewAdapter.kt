package pham.hien.assignment.Activity.User

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import pham.hien.assignment.R

class ViewPageViewAdapter(listGroupView: ArrayList<ViewGroup>) :
    RecyclerView.Adapter<ViewPageViewAdapter.Pager2ViewHolder>() {

    private var mListGroupView: ArrayList<ViewGroup> = listGroupView


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return Pager2ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.groupView.removeAllViews()
        val groupView = mListGroupView[position]
        holder.groupView.addView(groupView)
    }
 
    override fun getItemCount(): Int {
        return mListGroupView.size
    }

    class Pager2ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val groupView: RelativeLayout

        init {
                groupView = item.findViewById(R.id.group_view)
        }
    }
}