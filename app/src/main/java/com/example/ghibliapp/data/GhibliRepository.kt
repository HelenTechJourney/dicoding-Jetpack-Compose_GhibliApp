package com.example.ghibliapp.data

import com.example.ghibliapp.model.GetMovie
import com.example.ghibliapp.model.Ghibli
import com.example.ghibliapp.model.StudioGhibliData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GhibliRepository {

    private val getMovie = mutableListOf<GetMovie>()

    init {
        if (getMovie.isEmpty()) {
            StudioGhibliData.movies.forEach {
                getMovie.add(GetMovie(it, 0))
            }
        }
    }

    fun getAllRewards(): Flow<List<GetMovie>> {
        return flowOf(getMovie)
    }

    fun getMovieById(movieId: Long): GetMovie {
        return getMovie.first {
            it.ghibli.id == movieId
        }
    }

    fun getData(): List<Ghibli> {
        return StudioGhibliData.movies
    }
    fun searchGhibli(query: String): List<Ghibli>{
        return StudioGhibliData.movies.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
    companion object {
        @Volatile
        private var instance: GhibliRepository? = null

        fun getInstance(): GhibliRepository =
            instance ?: synchronized(this) {
                GhibliRepository().apply {
                    instance = this
                }
            }
    }
}