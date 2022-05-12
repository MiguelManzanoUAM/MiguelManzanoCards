package eps.uam.es.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import eps.uam.es.dadm.cards.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        //setup
        val bundle=intent.extras
        val email = bundle?.getString("email")
        setup(email ?: "")
    }

    private fun setup(email:String){
        title="Inicio"
        binding.emailAuth.text = email

        binding.logOutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            //para volver a la pantalla anterior
            onBackPressed()
        }
    }
}