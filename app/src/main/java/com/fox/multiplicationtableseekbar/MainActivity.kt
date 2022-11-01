package com.fox.multiplicationtableseekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.fox.multiplicationtableseekbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding?: throw RuntimeException("ActivityMainBinding = null")

    val numbers = arrayListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        binding.listViewNumbers.adapter = adapter
        binding.seekBar.max = MAX
        binding.seekBar.setOnSeekBarChangeListener( object :SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                numbers.clear()
                if (progress < MIN) {
                    binding.seekBar.progress = MIN
                }
                for (i in MIN .. COUNT) {
                    numbers.add(binding.seekBar.progress * i)
                }
                adapter.notifyDataSetChanged()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        binding.seekBar.progress = 10
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val MIN = 1
        private const val MAX = 20
        private const val COUNT = 10

    }
}