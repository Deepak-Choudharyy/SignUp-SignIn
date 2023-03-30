package dk.deepak.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference

    companion object {
        const val KEY1 ="dk.deepak.signin.signin.name"
        const val KEY2 ="dk.deepak.signin.signin.email"
        const val KEY3 ="dk.deepak.signin.signin.password"
        const val KEY4 ="dk.deepak.signin.signin.userId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        getSupportActionBar()?.hide();

        val signIn = findViewById<Button>(R.id.btn)
        val user = findViewById<EditText>(R.id.etUId)
        val password = findViewById<EditText>(R.id.etPassword)
        val back = findViewById<ImageView>(R.id.tvBack)
        signIn.setOnClickListener {

            val user = user.text.toString()
            val password = password.text.toString()
            if (user.isNotEmpty() && password.isNotEmpty()){
                redData(user,password)
            }else{
                Toast.makeText(this,"Enter your correct Id",Toast.LENGTH_SHORT).show()
            }

        }
        back.setOnClickListener {
            val intentBack= Intent(this,MainActivity::class.java)
            startActivity(intentBack)
        }
    }


    private fun redData(userId: String,userIdP: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userId).get().addOnSuccessListener {

            if(it.exists()){
                if(userIdP == it.child("password").value) {

                    val name = it.child("name").value
                    val email = it.child("email").value
                    val password = it.child("password").value
                    val userId = it.child("uniqueId").value

                    val intentWelcome = Intent(this, welcome::class.java)
                    intentWelcome.putExtra(KEY1, name.toString())
                    intentWelcome.putExtra(KEY2, email.toString())
                    intentWelcome.putExtra(KEY3, password.toString())
                    intentWelcome.putExtra(KEY4, userId.toString())
                    startActivity(intentWelcome)
                    finish()
                }else{
                    Toast.makeText(this,"User Password is Incorrect",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}