package sett.teta.termproject.owneractivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_checker_page.*
import kotlinx.android.synthetic.main.checklist_row.view.*
import kotlinx.android.synthetic.main.fragment_view_history.*
import sett.teta.termproject.Check
import sett.teta.termproject.R
import sett.teta.termproject.checklistpackage.ChecklistRepository

class ViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_history, container, false)

//        histOrOngoRecView.layoutManager = LinearLayoutManager(this.context)
//        getChecklistFromDatabaseToListView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        histOrOngoRecView.apply {
            layoutManager = LinearLayoutManager(activity)
            getChecklistFromDatabaseToListView()
        }

        historyButton.setOnClickListener {
            getAllChecklistFromDatabaseToListView()
        }

        ongoingButton.setOnClickListener {
            getUnChecklistFromDatabaseToListView()
        }

        completedButton.setOnClickListener {
            getChecklistFromDatabaseToListView()
        }
    }

    private fun getChecklistFromDatabaseToListView(){
        return ChecklistRepository.get().getChecked().observe(viewLifecycleOwner){ checklist ->
            checklist.let {
                histOrOngoRecView.adapter = ChecklistAdapter(checklist)
            }
        }
    }

    private fun getAllChecklistFromDatabaseToListView(){
        return ChecklistRepository.get().getChecklist().observe(viewLifecycleOwner){ checklist ->
            checklist.let {
                histOrOngoRecView.adapter = ChecklistAdapter(checklist)
            }
        }
    }

    private fun getUnChecklistFromDatabaseToListView(){
        return ChecklistRepository.get().getUnChecked().observe(viewLifecycleOwner){ checklist ->
            checklist.let {
                histOrOngoRecView.adapter = ChecklistAdapter(checklist)
            }
        }
    }

    inner class ChecklistViewHolder(view: View): RecyclerView.ViewHolder(view){
        val roomCheck: TextView = itemView.roomText
        val dateCheck: TextView = itemView.dateText
        val checkoutCheck: TextView = itemView.checkoutText
        val noteCheck: TextView = itemView.noteText

        var idCheck = ""

        init {

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