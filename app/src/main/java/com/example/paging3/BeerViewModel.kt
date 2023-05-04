package com.example.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.paging3.repository.BeerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(val beerRepo: BeerRepo) : ViewModel() {

    var list = beerRepo.getBeers().cachedIn(viewModelScope)

}