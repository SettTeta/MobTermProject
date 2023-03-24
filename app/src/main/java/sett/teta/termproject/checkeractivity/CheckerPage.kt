package sett.teta.termproject.checkeractivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_checker_page.*
import kotlinx.android.synthetic.main.checklist_row.view.*
import sett.teta.termproject.Check
import sett.teta.termproject.R
import sett.teta.termproject.checklistpackage.ChecklistRepository

class CheckerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checker_page)

        checklistView.layoutManager = LinearLayoutManager(this)
        getChecklistFromDatabaseToListView()

    }

    override fun onResume() {
        super.onResume()
        getChecklistFromDatabaseToListView()
    }

    private fun getChecklistFromDatabaseToListView(){
        return ChecklistRepository.get().getUnChecked().observe(this){ checklist ->
            checklist.let {
                checklistView.adapter = ChecklistAdapter(checklist)
            }
        }
    }

    inner class ChecklistViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        val roomCheck: TextView = itemView.roomText
        val dateCheck: TextView = itemView.dateText
        val checkoutCheck: TextView = itemView.checkoutText
        val noteCheck: TextView = itemView.noteText

        var idCheck = ""

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val intent = Intent(p0!!.context, ViewCheck::class.java)
            intent.putExtra("ID", idCheck)

            startActivity(intent)
        }
    }

    inner class ChecklistAdapter(var checks: List<Check>): RecyclerView.Adapter<ChecklistViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
            val view = layoutInflater.inflate(R.layout.checklist_row, parent,false)
            return ChecklistViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
            holder.roomCheck.text = checks[position].room
            holder.dateCheck.text = checks[position].date
            holder.checkoutCheck.text = checks[position].checkout
            holder.noteCheck.text = checks[position].notes

            holder.idCheck = checks[position].id.toString()

        }

        override fun getItemCount(): Int {
            return checks.size
        }

    }
}