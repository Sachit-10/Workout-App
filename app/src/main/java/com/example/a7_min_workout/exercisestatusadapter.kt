package com.example.a7_min_workout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7_min_workout.databinding.ItemExerciseStatusBinding

class exercisestatusadapter(val items: ArrayList<exercisemodel>): RecyclerView.Adapter<exercisestatusadapter.ViewHolder>() {




    /**
     * Inflates the item view which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: exercisemodel = items[position]
        holder.tvitem.text = model.getid().toString()
        // TODO(Step 4 : Updating the current item and the completed item in the UI and changing the background and text color according to it..)
        // START
        // Updating the background and text color according to the flags that is in the list.
        // A link to set text color programmatically and same way we can set the drawable background also instead of color.
        // https://stackoverflow.com/questions/8472349/how-to-set-text-color-to-a-text-view-programmatically

        when {
            model.getiselected() -> {
                holder.tvitem.background =
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.item_circular_grey_selected_background
                    )
                holder.tvitem.setTextColor(Color.parseColor("#212121")) // Parse the color string, and return the corresponding color-int.
            }
            model.getiscompleted() -> {
                holder.tvitem.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_color_accent_background)
                holder.tvitem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tvitem.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_grey_background)
                holder.tvitem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */

    class ViewHolder(binding:ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root){
        // Holds the TextView that will add each item to
        val tvitem = binding.tvitem

    }

}