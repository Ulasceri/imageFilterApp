package com.example.imagefilterapp.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagefilterapp.repositories.SavedImagesRepository
import com.example.imagefilterapp.utilities.Coroutines
import java.io.File

class SavedImagesViewModel(private val savedImagesRepository: SavedImagesRepository) : ViewModel() {

    private val savedImagesDataState = MutableLiveData<SavedImagesDataState>()
    val savedImagesUiState: LiveData<SavedImagesDataState> get() = savedImagesDataState

    fun loadSavedImages(){
        Coroutines.io {
            kotlin.runCatching {
                emitSavedImagesUiStates(isLoading = true)
                savedImagesRepository.loadSavedImages()
            }.onSuccess { savedImages ->
                if (savedImages.isNullOrEmpty()){
                    emitSavedImagesUiStates(error = "No image found")
                }else {
                    emitSavedImagesUiStates(savedImages = savedImages)
                }
            }.onFailure {
                emitSavedImagesUiStates(error = it.message.toString())
            }
        }
    }

    private fun emitSavedImagesUiStates(
        isLoading: Boolean = false,
        savedImages: List<Pair<File, Bitmap>>? = null,
        error: String? = null
    ) {
        val dataState = SavedImagesDataState(isLoading,savedImages,error)
        savedImagesDataState.postValue(dataState)
    }

    data class SavedImagesDataState(
        val isLoading: Boolean,
        val savedImages: List<Pair<File,Bitmap>>?,
        val error: String?
    )
}