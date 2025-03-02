package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



class MovieTest {

    @Test
    void movie_should_have_title() {
        Movie movie = new Movie("Interstellar", "A space adventure", Arrays.asList(Genres.SCIENCE_FICTION));
        assertEquals("Interstellar", movie.getTitle());
    }

    @Test
    void movie_should_have_description() {
        Movie movie = new Movie("Interstellar", "A space adventure", Arrays.asList(Genres.SCIENCE_FICTION));
        assertEquals("A space adventure", movie.getDescription());
    }

    @Test
    void movie_should_have_genres() {
        Movie movie = new Movie("Interstellar", "A space adventure", Arrays.asList(Genres.SCIENCE_FICTION, Genres.DRAMA));
        List<Genres> expectedGenres = Arrays.asList(Genres.SCIENCE_FICTION, Genres.DRAMA);
        assertEquals(expectedGenres, movie.getGenres());
    }

    @Test
    void initialize_movies_should_create_movies() {
        List<Movie> movies = Movie.initializeMovies();
        assertNotNull(movies);
        assertEquals(3, movies.size());
    }

    @Test
    void initialize_movies_should_have_correct_titles() {
        List<Movie> movies = Movie.initializeMovies();
        assertEquals("Finding Nemo", movies.get(0).getTitle());
        assertEquals("Forrest Gump", movies.get(1).getTitle());
        assertEquals("Inception", movies.get(2).getTitle());
    }
}