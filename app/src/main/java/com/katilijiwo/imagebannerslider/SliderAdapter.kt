package com.katilijiwo.imagebannerslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.katilijiwo.imagebannerslider.databinding.ListSliderBinding

class SliderAdapter: PagerAdapter() {

    private val datas = mutableListOf<String>()
    fun setDatas(newData: List<String>){
        datas.clear()
        datas.addAll(newData)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: ListSliderBinding = DataBindingUtil.inflate(inflater, R.layout.list_slider, container, false)

        Glide.with(container.context)
            .load(datas[position])
            .into(binding.ivSlider)

        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int = datas.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
}