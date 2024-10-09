package com.example.docuscanner.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docuscanner.data.local.repository.Repository
import com.example.docuscanner.data.models.DocumentEntity
import com.example.docuscanner.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

//    private val _viewState = MutableStateFlow<UiState<List<DocumentEntity>>>(UiState.Loading)
//    val viewState = _viewState.asStateFlow()

    private val _documents = MutableStateFlow<List<DocumentEntity>>(emptyList())
    val documents = _documents.asStateFlow()

    private val _currentDoc = MutableStateFlow<DocumentEntity?>(null)
    val currentDoc = _currentDoc.asStateFlow()

    fun showRenameDeleteDialog() {
        _showDialog.update {
            true
        }
    }

    fun dismissDialog() {
        _showDialog.update {
            false
        }
    }

    fun setCurrentDoc(document: DocumentEntity) {
        _currentDoc.update {
            document
        }
    }

    fun fetchAllPdfs() = viewModelScope.launch {
        repository.getAllPDFs().collect { pdfs ->
            _documents.update {
                return@update pdfs
            }
        }
    }

    fun addNewDocument(document: DocumentEntity) = viewModelScope.launch {

        repository.addNewDocument(document)

    }

    fun updateDocument(document: DocumentEntity) = viewModelScope.launch {
        repository.updateDocument(document)
    }

    fun deleteDocument(document: DocumentEntity) = viewModelScope.launch {
        repository.deleteDocument(document)
    }
}