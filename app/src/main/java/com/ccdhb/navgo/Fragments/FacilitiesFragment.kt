package com.ccdhb.navgo.Fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.ccdhb.navgo.Activities.MapActivity
import com.ccdhb.navgo.Models.Facility
import com.ccdhb.navgo.Models.NavGoApplication
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
        pointsOfInterestListView.setOnItemClickListener { _: AdapterView<*>, _: View, position: Int, _: Long ->
            val intent = Intent(activity, MapActivity::class.java)
            val facility = pointsOfInterestListView.getItemAtPosition(position) as Facility
            intent.putExtra("Name", facility.name)
            intent.putExtra("Floor", facility.floor)
            intent.putExtra("DestinationID", facility.placeId)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private class PointsOfInterestAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        private val facilities = NavGoApplication.facilities

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
            viewHolder.nameTextView.text = facilities[position].name
            viewHolder.iconImageView.setImageResource(facilities[position].icon!!)
            viewHolder.nameTextView.contentDescription = facilities[position].name

            return facilitiesRow
        }

        override fun getItem(position: Int): Any {
            return facilities.get(position)
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
