package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.changepassword.ChangePasswordViewModel
import com.example.facebook.createpost.CreatePostViewModel
import com.example.facebook.friendslist.FriendsListViewModel
import com.example.facebook.login.LoginPageViewModel
import com.example.facebook.register.RegisterAccountViewModel
import com.example.facebook.userprofile.ProfilePageViewModel
import com.example.facebook.viewmodels.HomeActivityViewModel
import dagger.Module
import dagger.Provides
@Module
class ChangePswdModule {
    @Provides
    fun getChangePswdVM(apiService: ApiService): ChangePasswordViewModel {
        return ChangePasswordViewModel(apiService)
    }
}
@Module
class LoginModule {

    @Provides
    fun getViewModel(apiService:ApiService): LoginPageViewModel {
        return LoginPageViewModel(apiService)
    }
}
@Module
class HomeActivityModule {
    @Provides
    fun getHomeActivityViewModule(apiService:ApiService): HomeActivityViewModel {
        return HomeActivityViewModel(apiService)
    }
}
@Module
class FriendsListModule {
    @Provides
    fun getFriendsListVM(apiService: ApiService): FriendsListViewModel {
        return FriendsListViewModel(apiService)
    }
}

@Module
class RegisterModule{
    @Provides
    fun getRegisterAccVM(apiService: ApiService):RegisterAccountViewModel{
        return RegisterAccountViewModel(apiService)
    }
}

@Module
class ProfileModule{
    @Provides
    fun getProfileVM(apiService: ApiService):ProfilePageViewModel{
        return ProfilePageViewModel(apiService)
    }
}

@Module
class CreatePostModule {
    @Provides
    fun getCreatePostVM(apiService: ApiService): CreatePostViewModel {
        return CreatePostViewModel(apiService)
    }
}
@Module
class MainActivityModule {

    @Provides
    fun getMainActivityViewModel(apiService: ApiService):HomeActivityViewModel{
        return HomeActivityViewModel(apiService)
    }
}
@Module
class MainScreenModule {
    @Provides
    fun getMainScreenViewModule(apiService: ApiService): HomeActivityViewModel {
        return HomeActivityViewModel(apiService)
    }
}