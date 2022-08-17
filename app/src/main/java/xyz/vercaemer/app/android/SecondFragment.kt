package xyz.vercaemer.app.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import xyz.vercaemer.app.DependencyManager
import xyz.vercaemer.app.R
import xyz.vercaemer.app.databinding.FragmentSecondBinding
import xyz.vercaemer.app.x.example.ExampleViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private var dependencyManager: DependencyManager? = null

    private val exampleViewModel: ExampleViewModel get() = dependencyManager!!.exampleViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dependencyManager = (context as ViewContainer).dependencyManager
    }

    override fun onDetach() {
        dependencyManager = null
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()
        exampleViewModel.setObserver {
            Toast.makeText(context, exampleViewModel.text, Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        exampleViewModel.removeObserver()
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}