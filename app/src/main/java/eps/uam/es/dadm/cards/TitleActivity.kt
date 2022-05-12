package eps.uam.es.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import eps.uam.es.dadm.cards.databinding.ActivityTitleBinding
import timber.log.Timber

class TitleActivity : AppCompatActivity() {

    lateinit var binding: ActivityTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_title)

        /* Nodos Firebase se crean solos*/
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference = database.getReference("message")
        Timber.i("Referencia: ${reference.toString()}")

        reference.setValue("Hello from Cards")

        reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Snackbar.make(binding.root, snapshot.value.toString(), Snackbar.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {}

        })

        /*Valores por defecto */
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        binding.navView.setupWithNavController(navHostFragment.navController)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("Title: onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("Title: onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("Title: onPause called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("Title: onStop called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("Title: onSaveInstanceState called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("Title: onRestoreInstanceState called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("Title: onDestroy called")
    }

    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}