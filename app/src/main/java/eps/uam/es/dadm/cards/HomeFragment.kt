package eps.uam.es.dadm.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import eps.uam.es.dadm.cards.databinding.FragmentHomeBinding
import eps.uam.es.dadm.cards.databinding.FragmentTitleBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.emailAuth.text = FirebaseAuth.getInstance().currentUser?.email.toString() ?: ""

        binding.decksAuthButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_deckListFragment)
        }

        binding.logOutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_titleFragment)
        }

        return binding.root
    }
}