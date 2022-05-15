package eps.uam.es.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import eps.uam.es.dadm.cards.databinding.FragmentStudyBinding
import timber.log.Timber

class StudyFragment: Fragment() {

    lateinit var binding: FragmentStudyBinding

    private val viewModel: StudyViewModel by lazy {
        ViewModelProvider(this).get(StudyViewModel::class.java)
    }

    private var listener = View.OnClickListener { v ->
        val quality = when (v?.id){
            R.id.easy_button_view -> viewModel.update(5)
            R.id.doubt_button_view -> viewModel.update(3)
            R.id.difficult_button_view -> viewModel.update(0)
            else -> throw Exception("Unknown quality")
        }

        binding.invalidateAll()
    }

    /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    /* override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_study, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Asigna un observador a dueCard
        viewModel.dueCard.observe(viewLifecycleOwner){
            viewModel.card = it
            binding.invalidateAll()
        }

        binding.answerButtonView.setOnClickListener {
            //viewModel?.card?.answered = true
            viewModel.card?.answered = true
            binding.invalidateAll()
        }

        Timber.i("Main: onCreate called")

        binding.apply{
            easyButtonView.setOnClickListener(listener)
            doubtButtonView.setOnClickListener(listener)
            difficultButtonView.setOnClickListener(listener)
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        if(prefs.getBoolean("board", true)){
            binding.boardView?.visibility = View.VISIBLE
        }

        return binding.root
    }

}