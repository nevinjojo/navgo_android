package com.ccdhb.navgo.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.ccdhb.navgo.Activities.PlayerActivity
import com.ccdhb.navgo.Models.Model
import com.ccdhb.navgo.R
import kotlinx.android.synthetic.main.cardview_audios_layout.view.*
import kotlinx.android.synthetic.main.table_layout.*

class SoundsFragment : Fragment() {

    lateinit var recycleView: RecyclerView
    lateinit var ref: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.sounds_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView = tableView

        ref = FirebaseDatabase.getInstance().getReference().child("soundsContent")

        recycleView.layoutManager = LinearLayoutManager(context)

        val option = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(ref, Model::class.java)
            .build()

        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<Model, MyViewHolder>(option) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemview = LayoutInflater.from(context).inflate(R.layout.cardview_sounds_layout, parent, false)
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
        firebaseRecyclerAdapter.startListening()

    }

    class MyViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var mtitle: TextView = itemView!!.titleText
    }
}