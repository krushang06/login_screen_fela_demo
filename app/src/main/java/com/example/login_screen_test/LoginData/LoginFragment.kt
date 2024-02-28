package com.example.login_screen_test.LoginData

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.login_screen_test.utils.PreferencesManager
import com.example.login_screen_test.R
import com.example.login_screen_test.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        addObservers()
        binding.backBTN.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.loginbuttonBT.setOnClickListener {
            val enteredPassword = binding.passwordEt.text.toString().trim()
            val enteredUsername = binding.emailET.text.toString().trim()

            fun isValidEmail(emailET: String): Boolean {
                return Patterns.EMAIL_ADDRESS.matcher(emailET).matches()
            }

            fun isValidPassword(passwordEt: String): Boolean {
                return passwordEt.length >= 5
            }

            if (isValidEmail(enteredUsername) && isValidPassword(enteredPassword)) {
                val loginData = LoginRequest(enteredUsername, enteredPassword)
                viewModel.fetchJoke(loginData, requireContext())
            } else {
                if (!isValidEmail(enteredUsername)) {
                    binding.emailET.error = "Invalid email"
                } else {
                    binding.emailET.error = null
                }
                if (!isValidPassword(enteredPassword)) {
                    binding.passwordEt.error = "Password should be at least 5 characters"
                } else {
                    binding.passwordEt.error = null
                }
            }
        }
    }

    private fun addObservers() {
        viewModel.token.observe(viewLifecycleOwner) { token ->
            if (token != null) {
                findNavController().navigate(R.id.action_loginFragment_to_HomeFragment)
                viewModel.clearToken()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

/*

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        addObservers()
        binding.backBTN.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.loginbuttonBT.setOnClickListener {
            val enteredPassword = binding.passwordEt.text.toString().trim()
            val enteredUsername = binding.emailET.text.toString().trim()

            fun isValidEmail(emailET: String): Boolean {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(emailET).matches()
            }

            fun isValidPassword(passwordEt: String): Boolean {
                return passwordEt.length >= 5
            }

            if (isValidEmail(enteredUsername) && isValidPassword(enteredPassword)) {
                val loginData = LoginRequest(enteredUsername, enteredPassword)
                if (!isValidEmail(enteredUsername)) {

                    viewModel.fetchJoke(loginData, requireContext())

                } else {
                    if (!isValidEmail(enteredUsername)) {
                        binding.emailET.error = "Invalid email"
                    } else {
                        binding.emailET.error = null
                    }
                    if (!isValidPassword(enteredPassword)) {
                        binding.passwordEt.error = "Password should be at least 5 characters"
                    } else {
                        binding.passwordEt.error = null
                    }
                }
            }
        }
    }
    private fun addObservers() {
        viewModel.token.observe(viewLifecycleOwner) { token ->
            if (token != null) {
                findNavController().navigate(R.id.action_loginFragment_to_HomeFragment)
                viewModel.clearToken()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
*/
