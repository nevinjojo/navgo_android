//
// THIS APPLICATION WAS DEVELOPED BY IURII DOLOTOV
//
// IF YOU HAVE ANY QUESTIONS PLEASE DO NOT TO HESITATE TO CONTACT ME VIA MARKETPLACE OR EMAIL: utilityman.development@gmail.com
//
// THE AUTHOR REMAINS ALL RIGHTS TO THE PROJECT
//
// THE ILLEGAL DISTRIBUTION IS PROHIBITED
//

package com.iuriidolotov.sleepytime.Fragments

import android.content.Intent
import android.net.Uri
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
import com.iuriidolotov.sleepytime.Activities.PlayerActivity
import com.iuriidolotov.sleepytime.Models.Model
import com.iuriidolotov.sleepytime.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_audios_layout.view.*
import kotlinx.android.synthetic.main.cardview_upper_audios_layout.view.*
import kotlinx.android.synthetic.main.menu_toolbar.*
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

        shareButton.setOnClickListener {
            val s = ("I am using an amazing Sleep Relaxation application to fall asleep very quickly - download now: https://play.google.com/store/apps/details?id=com.iuriidolotov.premiumsleepytime")
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s)
            startActivity(Intent.createChooser(shareIntent,"Share via:"))
        }

        rateButton.setOnClickListener {
            val rate = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.iuriidolotov.premiumsleepytime"))
            startActivity(rate)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ///---FIREBASE SET-UP FOR UPPER AND LOWER MENUS---///
        recycleView = tableView
        upperRecycleView = tableViewUpper

        ref = FirebaseDatabase.getInstance().getReference().child("content")
        refUpper = FirebaseDatabase.getInstance().getReference().child("featuredContent")

        recycleView.layoutManager = LinearLayoutManager(context)
        upperRecycleView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        val option = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(ref, Model::class.java)
            .build()

        val optionUpper = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(refUpper, Model::class.java)
            .build()

        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Model, MyViewHolder>(option) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemview = LayoutInflater.from(context).inflate(R.layout.cardview_audios_layout, parent, false)
                return MyViewHolder(
                    itemview
                )
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Model) {
                val refid = getRef(position).key.toString()

                ref.child(refid).addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        holder.mtitle.setText(model.caption)
                        Picasso.get().load(model.imageDownloadURL).into(holder.mimage)

                        holder.itemView.setOnClickListener {

                            audioUrlPass = model.audioURL!!

                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("Title", model.caption!!)
                            startActivity(intent)
                        }
                    }

                })
            }

        }

        val upperFirebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Model, UpperMyViewHolder>(optionUpper) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpperMyViewHolder {
                val itemview = LayoutInflater.from(context).inflate(R.layout.cardview_upper_audios_layout, parent, false)
                return UpperMyViewHolder(
                    itemview
                )
            }

            override fun onBindViewHolder(holder: UpperMyViewHolder, position: Int, model: Model) {
                val refid = getRef(position).key.toString()

                refUpper.child(refid).addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        holder.mtitle.setText(model.caption)
                        Picasso.get().load(model.imageDownloadURL).into(holder.mimage)
                        holder.itemView.setOnClickListener {

                            audioUrlPass = model.audioURL!!

                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("Title", model.caption!!)
                            startActivity(intent)
                        }
                    }

                })
            }

        }

        recycleView.adapter = firebaseRecyclerAdapter
        upperRecycleView.adapter = upperFirebaseRecyclerAdapter

        firebaseRecyclerAdapter.startListening()
        upperFirebaseRecyclerAdapter.startListening()
        ///---FIREBASE SET-UP FOR UPPER AND LOWER MENUS---///
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
