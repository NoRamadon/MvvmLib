/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dmi.mvvm_kotlin.view.adapter


import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.dmi.mvvm_kotlin.R
import com.dmi.mvvm_kotlin.data.model.Result


class PlaceAdapter : PagedListAdapter<Result, PlaceViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder =
            PlaceViewHolder(parent)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                    oldItem.placeId == newItem.placeId

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                    oldItem == newItem
        }
    }
}

class PlaceViewHolder(parent :ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var place : Result? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(place : Result?) {
        this.place = place
        nameView.text = place?.name
    }
}
