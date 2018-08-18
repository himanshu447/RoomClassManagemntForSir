package com.example.applecustomer.roomclassmanagement

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telecom.Connection
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.example.applecustomer.roomclassmanagement.classes.Classes
import com.example.applecustomer.roomclassmanagement.classes.ClassesViewModel

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration


private const val RESULT_CODE = 1

class MainActivity : AppCompatActivity() {

    var classVM: ClassesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val classAdapater = AddNewClassesAdapater(this)

        val recy = findViewById<RecyclerView>(R.id.recyforclass)

        classVM = ViewModelProviders.of(this).get(ClassesViewModel::class.java)

        classVM!!.getallClasses().observe(this, Observer<List<Classes>> { words ->
            // Update the cached copy of the words in the adapter.
            if (words != null) {
                classAdapater.setClasses(words)
            }
        })

        recy.layoutManager = LinearLayoutManager(this)
        recy.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recy.adapter = classAdapater

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddNewClassesActivity::class.java)
            startActivityForResult(intent, RESULT_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == RESULT_CODE && data != null) {
            val name = data.getStringExtra("name")
            val desc = data.getStringExtra("desc")

            Log.e("tag", "name $name")
            Log.e("tag", "descerfdf $desc")

            val classes = Classes(name, desc)
            classVM?.insert(classes)
        } else {
            Toast.makeText(baseContext, "this is error ", Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    class AddNewClassesAdapater(context: Context) : RecyclerView.Adapter<AddNewClassesVH>() {

        var list: List<Classes> = ArrayList()

        private val context = context

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AddNewClassesVH {
            val view = LayoutInflater.from(context).inflate(R.layout.item_class, p0, false)
            return AddNewClassesVH(view)
        }

        override fun getItemCount(): Int {
            if (list != null)
                return list.size
            else
                return 0
        }

        override fun onBindViewHolder(p0: AddNewClassesVH, p1: Int) {
            val classes: Classes = list[p1]
            p0.bindData(classes)
        }

        fun setClasses(classes: List<Classes>) {
            list = classes
            notifyDataSetChanged()
        }

        interface OnClick {
            fun click(pos: Int, cid: Int)

        }

    }

    class AddNewClassesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val classname = itemView.findViewById<TextView>(R.id.classNameTV)
        val classdesc = itemView.findViewById<TextView>(R.id.classDescTV)

        val onclick: AddNewClassesAdapater.OnClick? = null

        fun bindData(classes: Classes) {
            itemView.setOnClickListener {
                val context = itemView.context
                val i = Intent(context, StudentActivity::class.java)
                i.putExtra("cid", classes.id)
                context.startActivity(i)
                //  onclick?.click(adapterPosition, classes.id!!)
            }
            classname.text = classes.name
            classdesc.text = classes.description
        }
    }


}
