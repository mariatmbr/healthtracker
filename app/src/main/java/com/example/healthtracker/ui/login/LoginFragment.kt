package com.example.healthtracker.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.healthtracker.databinding.FragmentLoginBinding
import com.example.healthtracker.ui.main.MainActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.json.JSONException


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        LoginManager.getInstance()
            .logInWithReadPermissions(
                requireActivity(),
                listOf("public_profile", "email")
            )

        binding.loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {}

                override fun onError(error: FacebookException) {
                    Log.d("qwe", error.message ?: "idk error");
                }

                override fun onSuccess(result: LoginResult) {
                    Log.d("qwe", result.accessToken.token)
                    getFacebookData(result)
                }

            })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun getFacebookData(loginResult: LoginResult) {
        val request = GraphRequest.newMeRequest(
            loginResult.accessToken
        ) { _, response -> // Application code
            try {
                Log.d("qwe", response.toString())
                val email = response!!.getJSONObject()!!.getString("email")
                val firstName = response!!.getJSONObject()!!.getString("first_name")
                val lastName = response!!.getJSONObject()!!.getString("last_name")

                startActivity(Intent(requireContext(), MainActivity::class.java).apply {
                    putExtra("email", email)
                    putExtra("name", "$firstName $lastName")
                })

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,email,first_name,last_name,gender")
        request.parameters = parameters
        request.executeAsync()
    }
}