package com.katilijiwo.imagebannerslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.katilijiwo.imagebannerslider.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var currPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* val assets = listOf(
            R.drawable.photo_one,
            R.drawable.photo_two,
            R.drawable.photo_three
        ) */

        val assets: List<String> = listOf(
            "https://images.pexels.com/photos/3843443/pexels-photo-3843443.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            "https://images.pexels.com/photos/39853/woman-girl-freedom-happy-39853.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            "https://images.pexels.com/photos/37728/pexels-photo-37728.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        )

        createSlider(assets)
        autoScrollViewPager(assets.size)
    }

    private fun autoScrollViewPager(assetsSize: Int) {
        lifecycleScope.launch {
            withContext(Dispatchers.Default){
                while(true){
                    Log.d("<DEBUG>", currPosition.toString())
                    delay(5000)
                    if(currPosition == assetsSize)
                        currPosition = 0

                    withContext(Dispatchers.Main){
                        binding.vpSlider.setCurrentItem(currPosition++, true)
                    }
                }
            }
        }
    }

    private fun createSlider(assets: List<String>) {
        val adapter = SliderAdapter()
        adapter.setDatas(assets)
        binding.vpSlider.adapter = adapter
        binding.indicator.setViewPager(binding.vpSlider)
        binding.indicator.radius = 5 * resources.displayMetrics.density
        binding.indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currPosition = position
            }
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

}