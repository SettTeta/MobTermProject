package sett.teta.termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            val userExist = userViewModel.getUserIndex(inputUsername.text.toString(), inputPassword.text.toString())
            if (userExist){
                if(userViewModel.currentUserType){
                    val intent = Intent(this, CheckerPage::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, OwnerPage::class.java)
                    startActivity(intent)
                }

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}