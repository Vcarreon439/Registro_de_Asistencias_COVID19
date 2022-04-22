package com.example.registrodeasistenciascovid_19.ClasesExternas

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment(val listener: (String)-> Unit):DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hora = calendar.get(Calendar.HOUR_OF_DAY)
        val minuto = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(activity as Context, this, hora, minuto, true)
    }

    override fun onTimeSet(view: TimePicker?, Hora: Int, Minuto: Int) {
        listener("$Hora:$Minuto")
    }
}