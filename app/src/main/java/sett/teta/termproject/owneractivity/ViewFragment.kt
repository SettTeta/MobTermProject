package sett.teta.termproject.owneractivity

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_checker_page.*
import kotlinx.android.synthetic.main.checklist_row.view.*
import kotlinx.android.synthetic.main.fragment_view_history.*
import sett.teta.termproject.Check
import sett.teta.termproject.R
import sett.teta.termproject.checklistpackage.ChecklistRepository
import java.util.UUID

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
            typeViewTextView.text = "Ongoing"
        }

        completedButton.setOnClickListener {
            getChecklistFromDatabaseToListView()
            typeViewTextView.text = "Completed"
        }
    }

    private fun getChecklistFromDatabaseToListView() {
        return ChecklistRepository.get().getChecked().observe(viewLifecycleOwner) { checklist ->
            checklist.let {
                histOrOngoRecView.adapter = ChecklistAdapter(checklist.reversed())
            }
        }
    }

    private fun getAllChecklistFromDatabaseToListView() {
        return ChecklistRepository.get().getChecklist().observe(viewLifecycleOwner) { checklist ->
            checklist.let {
                histOrOngoRecView.adapter = ChecklistAdapter(checklist.reversed())
            }
        }
    }

    private fun getUnChecklistFromDatabaseToListView() {
        return ChecklistRepository.get().getUnChecked().observe(viewLifecycleOwner) { checklist ->
            checklist.let {
                histOrOngoRecView.adapter = ChecklistAdapter(checklist.reversed())
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

        override fun onClick(v: View?) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Are you sure you want to delete...    Room:${roomCheck.text} - Time:${checkoutCheck.text} - Date:${dateCheck.text}?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, _ ->
                    ChecklistRepository.get().deleteCheck(UUID.fromString(idCheck))
                    Toast.makeText(requireContext(), "Item deleted", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
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