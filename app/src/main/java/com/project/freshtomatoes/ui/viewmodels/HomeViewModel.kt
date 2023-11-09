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
    var popularMovies by mutableStateOf(emptyList<Movie>())
    var newMovies by mutableStateOf(emptyList<Movie>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            popularMovies = _requester.popularMovies().results
            newMovies = _requester.nowPlayingMovies().results
        }
    }

    private fun getPopularMovies(): MovieDisplay {
        return (MovieDisplay(popularLabel, popularMovies))
    }

    private fun getNewMovies(): MovieDisplay {
        return (MovieDisplay(newLabel, newMovies))
    }

    fun getAllMovies(): List<MovieDisplay> {
        return listOf(getPopularMovies(), getNewMovies())
    }
}

