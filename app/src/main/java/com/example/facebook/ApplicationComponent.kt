package com.example.facebook

import com.example.facebook.activity.HomeActivity
import com.example.facebook.activity.MainActivity
import com.example.facebook.api.NetworkService
import com.example.facebook.changepassword.ChangePasswordFragment
import com.example.facebook.createpost.CreatePostFragment
import com.example.facebook.di.LoginModule
import com.example.facebook.fragment.MainScreenPageFragment
import com.example.facebook.fragment.RegisterAccountFragment
import com.example.facebook.friendslist.FriendsPageFragment
import com.example.facebook.login.LoginFragment
import com.example.facebook.userprofile.ProfilePageFragment
import dagger.Component

//@Singleton
@Component(modules = [NetworkService::class, LoginModule::class])
interface ApplicationComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(mainScreenFragment:MainScreenPageFragment)
    fun inject(homeActivity: HomeActivity)
    fun inject(changePasswordFragment: ChangePasswordFragment)
    fun inject(createPostFragment: CreatePostFragment)
    fun inject(friendsPageFragment:FriendsPageFragment)
    fun inject(registerAccountFragment:RegisterAccountFragment)
    fun inject(profilePageFragment:ProfilePageFragment)


}