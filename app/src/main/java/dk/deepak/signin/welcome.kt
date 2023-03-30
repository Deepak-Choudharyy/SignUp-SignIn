package dk.deepak.signin


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val name = intent.getStringExtra(signin.KEY1)
        val email = intent.getStringExtra(signin.KEY2)
        val password = intent.getStringExtra(signin.KEY3)
        val userId = intent.getStringExtra(signin.KEY4)


        getSupportActionBar()?.hide();

        val welcometext = findViewById<TextView>(R.id.tvwel)
        val Mname = findViewById<TextView>(R.id.Name)
        val Name = findViewById<TextView>(R.id.tvName)
        val Email = findViewById<TextView>(R.id.tvEmail)
        val Password = findViewById<TextView>(R.id.tvPassword)
        val UniqueId = findViewById<TextView>(R.id.tvId)


        welcometext.text="Wel-Come"
        Mname.text=name
        Name.text="Name : $name"
        Email.text="Email : $email"
        Password.text="Password : $password"
        UniqueId.text="ID : $userId"

    }
}