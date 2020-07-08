package com.ccdhb.navgo.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.ccdhb.navgo.Models.Facility
import com.ccdhb.navgo.R
import kotlinx.android.synthetic.main.facilities_row.view.*


class FacilitiesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.facilities_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pointsOfInterestListView = activity!!.findViewById<ListView>(R.id.pointsOfInterestListView)
        pointsOfInterestListView.adapter = PointsOfInterestAdapter(activity!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private class PointsOfInterestAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        private val facilities = createFacilities()

        private fun createFacilities(): ArrayList<Facility> {
            val newFacilities = ArrayList<Facility>()
            newFacilities.add(Facility("Main Entrance", 2, R.color.colorGreen, R.drawable.ic_main_entrance))
            newFacilities.add(Facility("Eye Clinic", 9, R.color.colorBlue, R.drawable.ic_eye_clinic))
            newFacilities.add(Facility("Link Bridge", 3, R.color.colorGreen, R.drawable.ic_link_bridge))
            newFacilities.add(Facility("Intensive Care", 3, R.color.colorRed, R.drawable.ic_intensive_care_unit))
            newFacilities.add(Facility("Gift Shop", 2, R.color.colorBlue, R.drawable.ic_gift_shop))
            newFacilities.add(Facility("Cafe", 2, R.color.colorBlue, R.drawable.ic_cafe))
            newFacilities.add(Facility("Lift A", 1, R.color.colorGreen, R.drawable.ic_lift_a))
            newFacilities.add(Facility("Lift B", 1, R.color.colorGreen, R.drawable.ic_lift_b))
            newFacilities.add(Facility("Lift C", 1, R.color.colorGreen, R.drawable.ic_lift_c))

            return newFacilities
        }

        init {
            this.mContext = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val facilitiesRow: View
            // Checking if convertView is null, meaning we have to inflate a new row.
            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(mContext)
                facilitiesRow = layoutInflater.inflate(R.layout.facilities_row, parent, false)

                val viewHolder = ViewHolder(facilitiesRow.facilityNameTextView, facilitiesRow.facilityNameImageView)
                facilitiesRow.tag = viewHolder
            } else {
                // If convertView is not null, we already have our row as convertView, so we can set facilitiesRow as that view.
                facilitiesRow = convertView
            }

            val viewHolder = facilitiesRow.tag as ViewHolder
            viewHolder.nameTextView.text = facilities.get(position).name
            viewHolder.iconImageView.setImageResource(facilities.get(position).icon!!)

            return facilitiesRow
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // Responsible for how many rows there will be in the list
        override fun getCount(): Int {
            return facilities.size
        }

        /**
         * The ViewHolder is used for performance enhancement while fetching the ListView.
         */
        private class ViewHolder(val nameTextView: TextView, val iconImageView: ImageView)

    }
}
