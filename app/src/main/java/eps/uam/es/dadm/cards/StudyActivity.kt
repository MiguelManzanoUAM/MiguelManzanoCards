package eps.uam.es.dadm.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import eps.uam.es.dadm.cards.databinding.ActivityStudyBinding
import timber.log.Timber

class StudyActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_study)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("Main: onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("Main: onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("Main: onPause called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("Main: onStop called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("Main: onSaveInstanceState called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("Main: onRestoreInstanceState called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("Main: onDestroy called")
    }

}