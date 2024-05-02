package di

import com.mmorikawa.book_recommender.core.network.BookRecNetworkDataSource
import com.mmorikawa.book_recommender.core.network.ktor.KtorBookRecApiClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindsNetworkDataSource(ktorBookRecApiClient: KtorBookRecApiClient): BookRecNetworkDataSource
}