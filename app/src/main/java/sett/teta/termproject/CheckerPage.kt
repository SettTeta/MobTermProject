package sett.teta.termproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_checker_page.*
import kotlinx.android.synthetic.main.checklist_row.*
import kotlinx.android.synthetic.main.checklist_row.view.*

class CheckerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checker_page)

        checklistView.layoutManager = LinearLayoutManager(this)


        checklistView.adapter = ChecklistAdapter(AppData.checklists)
    }

    private fun getChecklistFromDatabaseToListView(){
        return ChecklistRepository.get().getChecklist().observe(this){ checklist ->
            checklist.let {
                checklistView.adapter = ChecklistAdapter(checklist)
            }
        }
    }

    inner class ChecklistViewHolder(view: View): RecyclerView.ViewHolder(view){
        val roomCheck = itemView.roomText
        val dateCheck = itemView.dateText
        val checkoutCheck = itemView.checkoutText
        val noteCheck = itemView.noteText
        val checkCheck = itemView.checkBoxButton

        init {
            checkCheck.setOnCheckedChangeListener { _, isChecked ->
                AppData.checklists[adapterPosition].checked = isChecked
            }
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
            holder.checkCheck.isChecked = checks[position].checked
        }

        override fun getItemCount(): Int {
            return checks.size
        }

    }
}