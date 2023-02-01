package com.ravi.calculatordemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ravi.calculatordemo.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val result = intent.getDoubleExtra(RESULT, 0.0)
        binding.tvResult.text = result.toString()
    }
    companion object {
        private const val RESULT = "result"
        fun launch(activity: AppCompatActivity,result:Double) =
            activity.startActivity(Intent(activity, ResultActivity::class.java).apply {
                activity.intent.putExtra(RESULT, result)
                activity.intent.extras?.let {
                    putExtras(it)
                }
            })
    }
}
