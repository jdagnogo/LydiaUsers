package com.jdagnogo.lydiausers.repository.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.api.UserApi
import retrofit2.HttpException
import androidx.room.withTransaction
import com.jdagnogo.lydiausers.repository.api.UserMapper
import java.io.IOException


private const val GITHUB_STARTING_PAGE_INDEX = 1
private const val SEED = "lydia"

@OptIn(ExperimentalPagingApi::class)
class LydiaRemoteMediator(
    private val api: UserApi,
    private val database: LydiaDatabase
) : RemoteMediator<Int, User>() {

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                nextKey
            }
        }

        try {
            val resultResponse = api.getUsers(SEED, state.config.pageSize, page)
            val userMapper = UserMapper()

            val userResponses = resultResponse.items
            val endOfPaginationReached = userResponses.isEmpty()
            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.getRemoteKeysDao().clearRemoteKeys()
                    database.getUserDao().clear()
                }
                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = userResponses.map {
                    RemoteKeys(it.email, prevKey = prevKey, nextKey = nextKey)
                }
                database.getRemoteKeysDao().insertAll(keys)
                database.getUserDao().insertAll(
                    userResponses.map { userMapper.toUser(it)}
                )
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, User>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { user ->
                // Get the remote keys of the last item retrieved
                database.getRemoteKeysDao().remoteKeysRepoId(user.email)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, User>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { user ->
                // Get the remote keys of the first items retrieved
                database.getRemoteKeysDao().remoteKeysRepoId(user.email)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, User>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.email?.let { email ->
                database.getRemoteKeysDao().remoteKeysRepoId(email)
            }
        }
    }

}
