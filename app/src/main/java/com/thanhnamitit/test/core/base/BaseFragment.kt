package com.thanhnamitit.test.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

open class BaseFragment<BINDING : ViewDataBinding>(
    val bindingCreator: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> BINDING,
) : Fragment() {

    private var _binding: BINDING? = null
    val binding
        get() = _binding!!

    open fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): BINDING {
        return bindingCreator(inflater, container, false).apply {
            lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        this._binding = onCreateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted { collectFlow() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    open suspend fun collectFlow() {}
}