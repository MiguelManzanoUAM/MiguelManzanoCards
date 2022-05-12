package eps.uam.es.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import eps.uam.es.dadm.cards.databinding.FragmentTutorialBinding

class TutorialFragment: Fragment() {
    /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    /* override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentTutorialBinding>(inflater, R.layout.fragment_tutorial, container, false)

        return binding.root
    }
}