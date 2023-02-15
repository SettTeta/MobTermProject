package sett.teta.termproject.checkeractivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_check.*
import sett.teta.termproject.Check
import sett.teta.termproject.R
import sett.teta.termproject.checklistpackage.ChecklistRepository
import java.util.*

class ViewCheck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_check)

        val checkId = UUID.fromString(intent.getStringExtra("ID"))
        ChecklistRepository.get().getCheck(checkId).observe(this){ check ->
            check.let {
                viewRoomText.text = check.room
                viewDateText.text = check.date
                viewCheckoutText.text = check.checkout
                viewNoteText.text = check.notes
            }
        }

        viewBackButton.setOnClickListener {
            finish()
        }

        viewCheckButton.setOnClickListener {
            val c = Check(checkId, viewRoomText.text.toString(), viewDateText.text.toString(), viewCheckoutText.text.toString(), viewNoteText.text.toString(), true)
            ChecklistRepository.get().updateChecklist(c)
            finish()
        }
    }
}