package com.grishma.searchimage.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grishma.searchimage.R
import com.grishma.searchimage.model.ImageSearch
import com.grishma.searchimage.ui.ImageDetailsActivity
import com.grishma.searchimage.utils.Utilities
import kotlinx.android.synthetic.main.item_image_search.view.*

/**
 * User Page List Adapter
 */
class ImageSearchListAdapter (private var imagesList :MutableList<ImageSearch.Data>): RecyclerView.Adapter<ImageSearchListAdapter.ImageSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_search, parent, false)
        return ImageSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageSearchViewHolder, position: Int) {
        val imageSearchDataItem = imagesList[position]
        val resources = holder.itemView.context.resources
        Log.d("Position::", "onBindViewHolder:  $position")
            if(imageSearchDataItem.images !=null) {
                Glide.with(holder.itemView.context).load(imageSearchDataItem.images!![0].link)
                    .placeholder(R.drawable.user_placeholder)
                    .into(holder.itemView.ivSearch)
            } else{
                Glide.with(holder.itemView.context).load(imageSearchDataItem.adUrl)
                    .placeholder(R.drawable.user_placeholder)
                    .into(holder.itemView.ivSearch)
            }

        holder.itemView.ivSearch.setOnClickListener {
            //open image details activity
            val intent = Intent (holder.itemView.ivSearch.context , ImageDetailsActivity::class.java)
            intent.putExtra(Utilities.DATA, imageSearchDataItem)
            intent.putExtra(Utilities.POSITION,position)
            (holder.itemView.ivSearch.context as Activity).startActivityForResult(intent, Utilities.LAUNCH_SECOND_ACTIVITY)
        }
    }

    class ImageSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return imagesList.size
    }

    //Add images list and notify adapter
    fun addImagesList(imageSearchList: List<ImageSearch.Data>?) {
        imagesList.clear()
        imageSearchList?.let { imagesList.addAll(it) }
        notifyDataSetChanged()
    }

    //update image list item
    fun updateItem(data: ImageSearch.Data, position: Int?) {
        position?.let { imagesList.removeAt(it) }
        position?.let { imagesList.add(it,data) }
        position?.let { notifyItemChanged(it) }
    }
}