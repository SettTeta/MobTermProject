package sett.teta.termproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_create_check.*
import java.util.*

class CreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_check, container, false)
    }

    override fun onStart() {
        super.onStart()

        saveButton.setOnClickListener {
            saveNewCheck(
                houseInput.text.toString(),
                dateInput.text.toString(),
                checkoutInput.text.toString(),
                noteInput.text.toString()
            )
        }
    }

    private fun saveNewCheck(
        saveHouse: String,
        saveDate: String,
        saveCheckout: String,
        saveNotes: String
    ) {
        val saveCheck = Check(UUID.randomUUID(),saveHouse, saveDate, saveCheckout, saveNotes, false)
        ChecklistRepository.get().addCheck(saveCheck)

    }

}