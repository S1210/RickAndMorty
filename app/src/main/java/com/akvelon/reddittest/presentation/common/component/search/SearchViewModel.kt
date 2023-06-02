package com.akvelon.reddittest.presentation.common.component.search

import com.akvelon.reddittest.presentation.common.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
abstract class SearchViewModel : BaseViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow().debounce(500).distinctUntilChanged()
    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    fun changeQuery(query: String) {
        _searchState.update { searchState -> searchState.copy(query = query) }
        _query.value = query
    }

    fun changeState(state: Boolean) {
        _searchState.update { searchState -> searchState.copy(isOpen = state, query = "") }
        _query.value = ""
    }

}
