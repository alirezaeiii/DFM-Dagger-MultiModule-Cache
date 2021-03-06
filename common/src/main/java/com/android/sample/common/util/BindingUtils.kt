package com.android.sample.common.util

import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.sample.common.base.BaseAdapter
import com.bumptech.glide.Glide

@BindingAdapter("showData")
fun <T, R: RecyclerView.ViewHolder> RecyclerView.showData(viewState: ViewState<T>?) {
    if (viewState is ViewState.Error) {
        this.scrollToPosition(0)
        @Suppress("UNCHECKED_CAST")
        (this.adapter as BaseAdapter<T, R>).submitList(null)
    }
}

@BindingAdapter("refreshing")
fun <T> SwipeRefreshLayout.setSwipeRefreshLayout(viewState: ViewState<T>?) {
    isRefreshing = viewState is ViewState.Loading
}

@BindingAdapter("showData")
fun <T> View.showData(viewState: ViewState<T>?) {
    visibility = if (viewState is ViewState.Success) View.VISIBLE else View.GONE
}

@BindingAdapter("showError")
fun <T> View.showError(viewState: ViewState<T>?) {
    visibility = if (viewState is ViewState.Error) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("descriptive_date")
fun setDescriptiveDate(toolbar: Toolbar, timestamp: Long) {
    val now = System.currentTimeMillis()
    val relativeDate = DateUtils.getRelativeTimeSpanString(
        timestamp,
        now,
        DateUtils.SECOND_IN_MILLIS,
        DateUtils.FORMAT_SHOW_YEAR
    )
    toolbar.title = relativeDate
}