package com.erikahendsel.activitygenerator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.erikahendsel.activitygenerator.api.RetrofitInstance
import com.erikahendsel.activitygenerator.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getActivity()
            } catch (e: IOException) {
                Log.d(TAG, "IOException: you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d(TAG, "HttpException: unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!
                binding.tvActivity.text = result.activity
                binding.tvAccessibility.text =
                    "Accessibility: ${result.accessibility}"
                binding.tvType.text = "Type: ${result.type}"
                binding.tvParticipants.text = "Participants: ${result.participants}"
                binding.tvPrice.text = "Price: ${result.price}"
                binding.tvLink.text =
                    if (result.link.isEmpty()) "" else "Link: ${result.link}"
            } else {
                Log.d(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }

        binding.btnGenerate.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                val response = try {
                    RetrofitInstance.api.getActivity()
                } catch (e: IOException) {
                    Log.d(TAG, "IOException: you might not have internet connection")
                    return@launch
                } catch (e: HttpException) {
                    Log.d(TAG, "HttpException: unexpected response")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    binding.tvActivity.text = result.activity
                    binding.tvAccessibility.text =
                        "Accessibility: ${result.accessibility}"
                    binding.tvType.text = "Type: ${result.type}"
                    binding.tvParticipants.text = "Participants: ${result.participants}"
                    binding.tvPrice.text = "Price: ${result.price}"
                    binding.tvLink.text =
                        if (result.link.isEmpty()) "" else "Link: ${result.link}"
                } else {
                    Log.d(TAG, "Response not successful")
                }
            }
        }
    }
}
