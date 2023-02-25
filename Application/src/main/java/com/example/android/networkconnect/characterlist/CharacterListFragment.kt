package com.example.android.networkconnect.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.networkconnect.api.ApiResponseStatus
import com.example.android.networkconnect.api.Util
import com.example.android.networkconnect.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private val viewModel: CharacterListViewModel by viewModels()

    private lateinit var binding: FragmentCharacterListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.charactersRecycler

        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = CharacterListAdapter()
        recycler.adapter = adapter

        observeViewModel(adapter)

        binding.next.setOnClickListener {
            viewModel.getCharacters(Util.NEXT_PAGE)
        }

        binding.prev.setOnClickListener {
            viewModel.getCharacters(Util.PREV_PAGE)
        }
    }

    private fun observeViewModel(adapter: CharacterListAdapter) {
        viewModel.characterList.observe(requireActivity()) { characterList ->
            adapter.submitList(characterList)
        }
        viewModel.status.observe(requireActivity()) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    binding.includeProgress.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), getString(status.errorId), Toast.LENGTH_SHORT).show()
                }
                is ApiResponseStatus.Loading -> binding.includeProgress.progress.visibility = View.VISIBLE

                is ApiResponseStatus.Success -> binding.includeProgress.progress.visibility = View.GONE
                null -> TODO()
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            CharacterListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}