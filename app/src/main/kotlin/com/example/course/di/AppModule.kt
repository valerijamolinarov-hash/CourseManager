package com.example.course.di

import android.content.Context
import androidx.room.Room
import com.example.course.data.local.AppDatabase
import com.example.course.data.local.CourseDao
import com.example.course.data.local.UserCredentialDao
import com.example.course.data.remote.CourseService
import com.example.course.data.remote.LoginService
import com.example.course.data.repository.CourseRepository
import com.example.course.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "course_db"
        ).build()
    }

    @Provides
    fun provideCourseDao(database: AppDatabase): CourseDao {
        return database.courseDao()
    }

    @Provides
    fun provideUserCredentialDao(database: AppDatabase): UserCredentialDao {
        return database.userCredentialDao()
    }

    @Provides
    fun provideLoginService(): LoginService {
        return LoginService()
    }

    @Provides
    fun provideCourseService(): CourseService {
        return CourseService()
    }

    @Provides
    fun provideCourseRepository(courseDao: CourseDao): CourseRepository {
        return CourseRepository(courseDao)
    }

    @Provides
    fun provideUserRepository(userCredentialDao: UserCredentialDao): UserRepository {
        return UserRepository(userCredentialDao)
    }
}
