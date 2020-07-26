package com.ccdhb.navgo.Fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.ccdhb.navgo.Activities.MapActivity
import com.ccdhb.navgo.Models.Appointment
import com.ccdhb.navgo.Models.Facility
import com.ccdhb.navgo.Models.NavGoApplication
import com.ccdhb.navgo.R
import kotlinx.android.synthetic.main.appointments_row.view.*
import kotlinx.android.synthetic.main.home_layout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val appointmentsListView = activity!!.findViewById<ListView>(R.id.appointmentsListView)
        appointmentsListView.adapter = AppointmentsAdapter(activity!!)
        appointmentsListView.setOnItemClickListener { _: AdapterView<*>, _: View, position: Int, _: Long ->
            val intent = Intent(activity, MapActivity::class.java)
            val appointment = appointmentsListView.getItemAtPosition(position) as Appointment
            intent.putExtra("Name", appointment.facility!!.name)
            intent.putExtra("Floor", appointment.facility!!.floor)
            intent.putExtra("DestinationID", appointment.facility!!.placeId)
            startActivity(intent)
        }

        voiceCommandButton.setOnClickListener {
            speak()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    // get text from result
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    // set initiate command based on result
                    val command = fetchCommand(result[0])
                    processCommand(command)
                }
            }
        }
    }

    private fun processCommand(command: String) {
        val intent = Intent(activity, MapActivity::class.java)
        val chosenFacility: Facility? = NavGoApplication.facilities.find { it.name!!.toLowerCase().contains(command) }
        try {
            if (chosenFacility != null) {
                intent.putExtra("Name", chosenFacility.name)
                intent.putExtra("Floor", chosenFacility.floor)
                intent.putExtra("DestinationID", chosenFacility.placeId)
                startActivity(intent)
            } else {
                // If there command does not match, show message in toast
                Toast.makeText(activity, "Voice command does not exist. Try again", Toast.LENGTH_SHORT).show()
                return
            }
        } catch (e: Exception) {
            // If there is an error, get error message and show in toast
            Toast.makeText(activity, "Voice command does not exist. Try again", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun fetchCommand(speechText: String): String {
        var commands: Array<String> = arrayOf("eye clinic", "intensive care", "main entrance", "map", "link bridge", "cafe", "underground parking", "gift shop", "restroom", "lift green", "lift orange", "lift red", "breastfeeding room", "disabled restroom")

        commands.forEach { str ->
            str.split(" ").forEach { subStr ->
                if (speechText.contains(subStr)) {
                    return subStr
                }
            }
        }
        return ""
    }

    private fun speak() {
        // Intent to show SpeechToText dialog
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Name a facility to start navigation")

        try {
            // If there is no error, show SpeechToText dialog
            startActivityForResult(mIntent, REQUEST_CODE_SPEECH_INPUT)
        } catch (e: Exception) {
            // If there is an error, get error message and show in toast
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private class AppointmentsAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        private val appointments = createAppointments()

        init {
            this.mContext = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val appointmentsRow: View
            // Checking if convertView is null, meaning we have to inflate a new row.
            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(mContext)
                appointmentsRow = layoutInflater.inflate(R.layout.appointments_row, parent, false)

                val viewHolder = ViewHolder(appointmentsRow.appointmentNameTextView, appointmentsRow.appointmentDetailsTextView, appointmentsRow.appointmentImageView)
                appointmentsRow.tag = viewHolder
            } else {
                // If convertView is not null, we already have our row as convertView, so we can set appointmentsRow as that view.
                appointmentsRow = convertView
            }

            val viewHolder = appointmentsRow.tag as ViewHolder
            viewHolder.nameTextView.text = appointments[position].name
            viewHolder.detailsTextView.text = appointments[position].dateTime
            viewHolder.imageTextView.setImageResource(appointments[position].icon!!)
            viewHolder.nameTextView.contentDescription = appointments[position].name
            viewHolder.detailsTextView.contentDescription = appointments[position].dateTime

            return appointmentsRow
        }

        override fun getItem(position: Int): Any {
            return appointments[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // Responsible for how many rows there will be in the list
        override fun getCount(): Int {
            return appointments.size
        }

        /**
         * Returns an ArrayList of Appointment objects.
         */
        fun createAppointments(): ArrayList<Appointment> {
            var simpleDateFormat = SimpleDateFormat("dd MMMM hh.mm aa")
            val newAppointments = ArrayList<Appointment>()
            newAppointments.add(Appointment("Annual Eye Checkup", Facility("Eye Clinic", 9, R.color.colorBlue, R.drawable.ic_eye_clinic, "5d54c846e7a9e8001697a213"), simpleDateFormat.format(Date(2020, 9, 5, 15, 30)), R.drawable.ic_eye_clinic))
            newAppointments.add(Appointment("Blood Donation", Facility("Intensive Care", 3, R.color.colorRed, R.drawable.ic_intensive_care_unit, "5d78e53aaa0a83002c1a4deb"), simpleDateFormat.format(Date(2020, 6, 5, 13, 15)), R.drawable.ic_intensive_care_unit))

            return newAppointments
        }

        /**
         * The ViewHolder is used for performance enhancement while fetching the ListView.
         */
        private class ViewHolder(val nameTextView: TextView, val detailsTextView: TextView, val imageTextView: ImageView)

    }

}
