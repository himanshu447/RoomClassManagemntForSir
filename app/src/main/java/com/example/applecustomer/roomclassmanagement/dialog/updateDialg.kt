package com.example.applecustomer.roomclassmanagement.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import com.example.applecustomer.roomclassmanagement.R

class updateDialg : DialogFragment() {

    companion object {
        fun newInstance(name: String?, sid: Int?): updateDialg {
            val arg = Bundle()
            Log.e("in arg name", name)
            arg.putString("sname", name)
            arg.putInt("sid", sid!!)
            val dialg = updateDialg()
            dialg.arguments = arg
            return dialg
        }
    }

    var callback: CallBack? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as CallBack
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = LayoutInflater.from(context).inflate(R.layout.item_update_dialog, null, false)

        val name = arguments?.getString("sname")
        val sid = arguments?.getInt("sid")

        Log.e("name", name)

        val editIB = v.findViewById<ImageButton>(R.id.updateIB)
        val deleteIB = v.findViewById<ImageButton>(R.id.deleteIB)
        val et = v.findViewById<EditText>(R.id.update)

        val dialog = AlertDialog.Builder(context!!).setView(v)

        et.setText(name)

        editIB.setOnClickListener {
            callback?.onUpdate(et.text.toString(), sid)
            dismiss()
        }

        deleteIB.setOnClickListener {
            callback?.onDelete(sid)
            dismiss()
        }
        return dialog.create()
    }

    interface CallBack {
        fun onUpdate(name: String, sid: Int?)

        fun onDelete(sid: Int?)
    }
}