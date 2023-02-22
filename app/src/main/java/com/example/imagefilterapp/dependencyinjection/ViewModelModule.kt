package com.example.imagefilterapp.dependencyinjection

import com.example.imagefilterapp.repositories.SavedImagesRepository
import com.example.imagefilterapp.viewmodels.EditImageViewModel
import com.example.imagefilterapp.viewmodels.SavedImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditImageViewModel(editImageRepository = get()) }
    viewModel { SavedImagesViewModel(savedImagesRepository = get()) }
}