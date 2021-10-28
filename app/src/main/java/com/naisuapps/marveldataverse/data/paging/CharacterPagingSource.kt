package com.naisuapps.marveldataverse.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.data.network.CharacterService
import com.orhanobut.logger.Logger
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val apiService: CharacterService) :
    PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            // Start refresh at page 0 if undefined.
            val position = params.key ?: INITIAL_PAGE_OFFSET
            val response = apiService.getCharacters(CURRENT_LIMIT_SIZE, position)
            return LoadResult.Page(
                data = response?.data?.characters ?: emptyList(),
                prevKey = null, // Only forward
                nextKey = if (response?.data?.characters.isNullOrEmpty()) null
                else position.plus(CURRENT_LIMIT_SIZE)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            Logger.e("Error paging exception: ${e.message}")
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(CURRENT_LIMIT_SIZE) ?: anchorPage?.nextKey?.minus(
                CURRENT_LIMIT_SIZE
            )
        }
    }

    companion object {
        const val CURRENT_LIMIT_SIZE = 20
        const val INITIAL_PAGE_OFFSET = 0
    }
}