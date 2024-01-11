package com.example.starwarproject.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarproject.model.FilterState
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.repository.StarWarRepository
import com.example.starwarproject.retrofit.StarWarApi

class CharacterPagingSource(private val repository: StarWarRepository) :
    PagingSource<Int, ResultsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getAllCharacters(page = nextPageNumber)

            return if (response.isSuccessful) {
                val characters = response.body()?.results ?: emptyList()

                LoadResult.Page(
                    data =
                    characters,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (characters.isEmpty()) null else nextPageNumber + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        TODO("Not yet implemented")
    }
}
