package com.ravetest.events.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.setContentView
import com.ravetest.events.R.layout.activity_main
import com.ravetest.events.data.model.Banner
import com.ravetest.events.data.workmanager.EventWorkManager
import com.ravetest.events.databinding.ActivityMainBinding
import com.ravetest.events.helper.extension.addScrollListener
import com.ravetest.events.helper.visibleitem.ThrottleTrackingBus
import com.ravetest.events.ui.adapter.BannersAdapter


class MainActivity : AppCompatActivity(), MainView {
    private val presenter by lazy { MainPresenter(this) }
    private lateinit var binding: ActivityMainBinding
    var trackingBus: ThrottleTrackingBus? = null
    val workManager by lazy { EventWorkManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContentView(this, activity_main)
        binding.adapter = BannersAdapter()
        trackingBus = ThrottleTrackingBus(this::onBannerItemDisplayed)
        binding.recyclerViewBanners.addScrollListener(trackingBus)

        presenter.getBanners()
    }

    override fun onStop() {
        presenter.onStop()
        trackingBus?.unsubscribe()
        super.onStop()
    }

    override fun onGetBanners(banners: List<Banner>) {
        binding.progressBar.isVisible = false
        binding.adapter?.updateList(banners)
    }

    private fun onBannerItemDisplayed(visibleItems: Set<Int>) =
        visibleItems.forEach { workManager.sendEvent(it) }
}
