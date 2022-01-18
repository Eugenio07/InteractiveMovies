package com.example.interactivemovies.di


import androidx.lifecycle.SavedStateHandle
import com.example.data.repositories.MoviesRepository
import com.example.data.repositories.UserRepository
import com.example.usecases.MoviesUseCases
import com.example.usecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class GeneralModule {
 @Provides
 fun userUsesCasesProvider(userRepository: UserRepository) = UserUseCases(userRepository)

 @Provides
 fun moviesUsesCasesProvider(moviesRepository: MoviesRepository) = MoviesUseCases(moviesRepository)

 @Provides
 @Named("movieID")
 fun movieIDProvider(args: SavedStateHandle): Int = args.get<Int>("movieID")?: throw IllegalStateException("ID null")
 }