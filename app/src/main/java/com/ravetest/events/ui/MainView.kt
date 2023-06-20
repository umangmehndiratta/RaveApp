package com.ravetest.events.ui;

import com.ravetest.events.data.model.Banner

interface MainView {
    fun onGetBanners(banners: List<Banner>)
}
