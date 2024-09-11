package com.example.speedotransfer.network.datamodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.network.api.FavouritesAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouritesViewModel: ViewModel() {
    private val _favourites = MutableStateFlow<List<FavouriteResponse>>(emptyList())
    val favourites: MutableStateFlow<List<FavouriteResponse>> = _favourites

    fun fetchFavourites(userId:String) {
        viewModelScope.launch(Dispatchers.IO){
            _favourites.update { FavouritesAPIService.FavouritesAPI.getFavorites(userId) }
        }
    }
    fun addFavourite(userId:String, favouriteRequest: FavouriteRequest) {
        viewModelScope.launch(Dispatchers.IO){
            FavouritesAPIService.FavouritesAPI.addFavorite(userId, favouriteRequest)
        }
    }
    fun deleteFavourite(userId:String, favouriteId:String) {
        viewModelScope.launch(Dispatchers.IO){
            FavouritesAPIService.FavouritesAPI.deleteFavorite(userId, favouriteId)
            _favourites.update { FavouritesAPIService.FavouritesAPI.getFavorites(userId) }
        }
    }
    fun updateFavourite(userId:String, favouriteId:String, favouriteRequest: FavouriteRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            FavouritesAPIService.FavouritesAPI.deleteFavorite(userId,favouriteId)
            FavouritesAPIService.FavouritesAPI.addFavorite(userId, favouriteRequest)
            _favourites.update { FavouritesAPIService.FavouritesAPI.getFavorites(userId) }
        }
    }
}