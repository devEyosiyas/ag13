package com.myedu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.myedu.R
import com.myedu.databinding.FragmentLoginBinding
import com.myedu.utils.Validator.validateEmail
import com.myedu.utils.Validator.validateName
import com.myedu.utils.Validator.validatePassword

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editEmail.doOnTextChanged { text, _, _, _ ->
            if (validateEmail(text.toString()))
                with(binding.emailInputLayout) {
                    error = null
                    isHelperTextEnabled = false
                }
            else
                binding.emailInputLayout.error = getString(R.string.error_email)
        }

        binding.editPassword.doOnTextChanged { text, _, _, _ ->
            if (validatePassword(text.toString()))
                with(binding.passwordInputLayout) {
                    error = null
                    isHelperTextEnabled = false
                }
            else
                binding.passwordInputLayout.error = getString(R.string.error_password)
        }

        binding.btnLogIn.setOnClickListener {
            validateFields()
            when {
                !validateEmail(binding.editEmail.text.toString()) -> binding.editEmail.requestFocus()
                !validatePassword(binding.editPassword.text.toString()) -> binding.editPassword.requestFocus()
                else -> login()
            }
        }

        // TODO: 12/12/2021 implement forgot password logic
        binding.txtForgotPassword.setOnClickListener {  }

        binding.txtSignUp.setOnClickListener {  Navigation
            .createNavigateOnClickListener(R.id.action_loginFragment_to_signUpFragment)
            .onClick(it) }
    }

    private fun login() {
        Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show()
    }

    private fun validateFields() {
        if (!validateEmail(binding.editEmail.text.toString()))
            with(binding.emailInputLayout) {
                requestFocus()
                error = getString(R.string.helper_email)
            }
        else
            with(binding.emailInputLayout) {
                error = null
                isHelperTextEnabled = false
            }

        if (!validatePassword(binding.editPassword.text.toString()))
            with(binding.passwordInputLayout) {
                requestFocus()
                error = getString(R.string.helper_password)
            }
        else
            with(binding.passwordInputLayout) {
                error = null
                isHelperTextEnabled = false
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}