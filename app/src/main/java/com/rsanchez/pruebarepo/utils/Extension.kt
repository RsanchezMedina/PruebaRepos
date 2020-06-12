package com.rsanchez.pruebarepo.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImgFromUrl(url: String) = Picasso.with(context).load(url).into(this)

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)

