package com.example.imagefilterapp.listeners

import java.io.File

interface SavedImageListener {
    fun onImageClicked(file:File)
}