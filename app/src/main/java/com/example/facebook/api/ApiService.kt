package com.example.facebook.api

import com.example.facebook.api.request.*
import com.example.facebook.api.response.*
import com.example.facebook.api.response.ChangePasswordRequest
import com.example.facebook.util.ApiResponse
import retrofit2.http.*

interface ApiService {


    @POST("/api/v1/login")
    suspend fun signIn(@Body info: LoginRequest): ApiResponse<LoginResponse>

    @POST("/api/v1/register")
    suspend fun registerUser(
        @Body info: RegisterUser
    ): ApiResponse<RegisterRequest>

    @PUT("/api/v1/changePassword/{userId}")
    suspend fun changePassword(
        @Body details: ChangePasswordRequest,
        @Path("userId", encoded = true) userId: Int,
    ): ApiResponse<ChangePasswordRequest>

    @GET("/api/v1/profile/{userId}")
    suspend fun getProfile(
        @Path("userId", encoded = true) userId: String,
    ): ApiResponse<GetUserProfile>

    @POST("/api/v1/post")
    suspend fun createPost(
        @Body postInfo: CreatePost
    ): ApiResponse<CreatePost>

    @DELETE("/api/v1/friend/{friendId}/{userId}")
    suspend fun deleteFriend(
        @Path("friendId", encoded = true) friendId: String,
        @Path("userId", encoded = true) userId: String,
    ): ApiResponse<RemoveFriendDataClass>

    @POST("/api/v1/friend")
    suspend fun addFriend(
        @Body friendInfo: AddNewFriend
    ): ApiResponse<AddNewFriend>


    @PUT("/api/v1/postLikes/{userId}/{postId}/{loginStatus}")
    suspend fun updateLike(
        @Path("userId", encoded = true) userId: String,
        @Path("postId", encoded = true) postId: String,
        @Path("loginStatus", encoded = true) loginStatus: String,
    ): ApiResponse<UpdateLikes>


    @GET("/api/v1/likes/{postId}")
    suspend fun likeCount(
        @Path("postId", encoded = true) postId: String,
    ): ApiResponse<LikesCount>

    @DELETE("/api/v1/post/{userId}/{postId}")
    suspend fun deletePost(
        @Path("userId", encoded = true) userId: String,
        @Path("postId", encoded = true) postId: String,
    ): ApiResponse<DeletePostDataClass>

    @GET("/api/v1/posts")
    suspend fun getPosts(
    ): ApiResponse<List<PostsResponsesItem>>

    @GET("/api/v1/profile/{userId}")
    suspend fun getUserProfile(
        @Path("userId", encoded = true) userId: String,
    ): ApiResponse<GetUserProfile>

}