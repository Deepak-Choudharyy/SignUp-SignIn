package dk.deepak.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.E

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

//    companion object {
//        const val KEY1 ="dk.deepak.signin.signin.name"
//        const val KEY2 ="dk.deepak.signin.signin.email"
//        const val KEY3 ="dk.deepak.signin.signin.password"
//        const val KEY4 ="dk.deepak.signin.MainActivity.userId"
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val SignUp = findViewById<Button>(R.id.button)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etUniqueId = findViewById<EditText>(R.id.etId)
        val login = findViewById<TextView>(R.id.login)


        getSupportActionBar()?.hide();


        SignUp.setOnClickListener {
            if (etName.text.toString().isNotEmpty() && etEmail.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty() && etUniqueId.text.toString().isNotEmpty() ) {

                if(Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
                    val Name = etName.text.toString()
                    val Email = etEmail.text.toString()
                    val Password = etPassword.text.toString()
                    val UniqueId = etUniqueId.text.toString()


                    val user = User(Name, Email, Password, UniqueId)


                    database = FirebaseDatabase.getInstance().getReference("Users")
                    database.child(Name).setValue(user).addOnSuccessListener {
                        etName.text?.clear()
                        etEmail.text?.clear()
                        etPassword.text?.clear()
                        etUniqueId.text?.clear()
                        Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener {
                        Toast.makeText(this, "Faild", Toast.LENGTH_SHORT).show()
                    }
                }else {
                    Toast.makeText(this, "Please Enter Correct Email Address ", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter your details ", Toast.LENGTH_SHORT).show()
            }

        }

        login.setOnClickListener {
            val openlogin = Intent(this,signin::class.java)
            startActivity(openlogin)
            finish()
        }
    }
}