package com.example.speedotransfer.network.api

import com.example.speedotransfer.network.datamodel.Favourite
import com.example.speedotransfer.network.datamodel.FavouriteRequest
import com.example.speedotransfer.network.datamodel.FavouriteResponse
import com.example.speedotransfer.network.datamodel.TransferRequest
import com.example.speedotransfer.network.datamodel.TransferResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavouritesAPICallable {
    // GET /users/{userId}/favorites
    @GET("/users/{userId}/favorites")
    suspend fun getFavorites(
        @Path("userId") userId: String
    ): List<FavouriteResponse>

    // POST /users/{userId}/favorites
    @POST("/users/{userId}/favorites")
    suspend fun addFavorite(
        @Path("userId") userId: String,
        @Body favoriteRequest: FavouriteRequest
    ): FavouriteResponse

    // DELETE /users/{userId}/favorites/{favoriteId}
    @DELETE("/users/{userId}/favorites/{favoriteId}")
    suspend fun deleteFavorite(
        @Path("userId") userId: String,
        @Path("favoriteId") favoriteId: String
    ) : Response<Unit>
}