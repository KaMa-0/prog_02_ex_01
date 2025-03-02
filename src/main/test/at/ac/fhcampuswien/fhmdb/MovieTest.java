package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



class MovieTest {

    @Test
    void movieShouldHaveTitle() {
        Movie movie = new Movie("Interstellar", "A space adventure", Arrays.asList(Genres.SCIENCE_FICTION));
        assertEquals("Interstellar", movie.getTitle());
    }

    @Test
    void movieShouldHaveDescription() {
        Movie movie = new Movie("Interstellar", "A space adventure", Arrays.asList(Genres.SCIENCE_FICTION));
        assertEquals("A space adventure", movie.getDescription());
    }

    @Test
    void movieShouldHaveGenres() {
        Movie movie = new Movie("Interstellar", "A space adventure", Arrays.asList(Genres.SCIENCE_FICTION, Genres.DRAMA));
        List<Genres> expectedGenres = Arrays.asList(Genres.SCIENCE_FICTION, Genres.DRAMA);
        assertEquals(expectedGenres, movie.getGenres());
    }

    @Test
    void initializeMoviesShouldCreateMovies() {
        List<Movie> movies = Movie.initializeMovies();
        assertNotNull(movies);
        assertEquals(3, movies.size());
    }

    @Test
    void initializeMoviesShouldHaveCorrectTitles() {
        List<Movie> movies = Movie.initializeMovies();
        assertEquals("Finding Nemo", movies.get(0).getTitle());
        assertEquals("Forrest Gump", movies.get(1).getTitle());
        assertEquals("Inception", movies.get(2).getTitle());
    }
}