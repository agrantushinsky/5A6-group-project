import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class MovieDisplay(
    val label: String,
    var movieList: List<Movie> = emptyList()
)

class HomeViewModel : ViewModel() {
    private val _requester = TmdbRequest()
    val popularLabel = "Popular Movies"
    val newLabel = "New Movies"
    val topRatedLabel = "Top Rated Movies"
    val actionMovieLabel = "Action"
    val adventureMovieLabel = "Adventure"
    var popularMovies by mutableStateOf(emptyList<Movie>())
    var newMovies by mutableStateOf(emptyList<Movie>())
    var topRatedMovies by mutableStateOf(emptyList<Movie>())
    var actionMovies by mutableStateOf(emptyList<Movie>())
    var adventureMovies by mutableStateOf(emptyList<Movie>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            popularMovies = _requester.popularMovies().results
            newMovies = _requester.nowPlayingMovies().results
            topRatedMovies = _requester.topRatedMovies().results
            actionMovies = _requester.getActionMovies().results
            adventureMovies = _requester.getAdventureMovies().results
        }
    }

    private fun getPopularMovies(): MovieDisplay {
        return MovieDisplay(popularLabel, popularMovies)
    }

    private fun getNewMovies(): MovieDisplay {
        return MovieDisplay(newLabel, newMovies)
    }

    private fun getTopRatedMovies(): MovieDisplay {
        return MovieDisplay(topRatedLabel, topRatedMovies)
    }

    private fun getActionMovies(): MovieDisplay {
        return MovieDisplay(actionMovieLabel, actionMovies)
    }

    private fun getAdventureMovies(): MovieDisplay {
        return MovieDisplay(adventureMovieLabel, adventureMovies)
    }

    fun getAllMovies(): List<MovieDisplay> {
        return listOf(
            getPopularMovies(),
            getNewMovies(),
            getTopRatedMovies(),
            getActionMovies(),
            getAdventureMovies()
        )
    }
}
