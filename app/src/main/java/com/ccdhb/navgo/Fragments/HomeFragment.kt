//
// THIS APPLICATION WAS DEVELOPED BY IURII DOLOTOV
//
// IF YOU HAVE ANY QUESTIONS PLEASE DO NOT TO HESITATE TO CONTACT ME VIA MARKETPLACE OR EMAIL: utilityman.development@gmail.com
//
// THE AUTHOR REMAINS ALL RIGHTS TO THE PROJECT
//
// THE ILLEGAL DISTRIBUTION IS PROHIBITED
//

package com.ccdhb.navgo.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.ccdhb.navgo.Activities.PlayerActivity
import com.ccdhb.navgo.Models.Model
import com.ccdhb.navgo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_audios_layout.view.*
import kotlinx.android.synthetic.main.cardview_upper_audios_layout.view.*
import kotlinx.android.synthetic.main.table_layout.*
import kotlinx.android.synthetic.main.upper_table_layout.*

var audioUrlPass = ""

class HomeFragment : Fragment() {

    lateinit var recycleView: RecyclerView
    lateinit var upperRecycleView: RecyclerView
    lateinit var ref: DatabaseReference
    lateinit var refUpper: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    class MyViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var mtitle: TextView = itemView!!.titleText
        var mimage: ImageView = itemView!!.imageDisplay
    }

    class UpperMyViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var mimage: ImageView = itemView!!.imageDisplayUpper
        var mtitle: TextView = itemView!!.upperTitle
    }

}
