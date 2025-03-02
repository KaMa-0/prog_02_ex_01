package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    // initialize homeController for testing
    private static HomeController homeController;
    @BeforeAll
    static void init() {
        homeController = new HomeController();
        homeController.initialize(null, null);
    }

    @Test
    void list_sorted_in_ascending_order() {
        homeController.sortMovies(true);
        ArrayList<Movie> expected = new ArrayList<>();
        expected.add(new Movie(
                "Finding Nemo",
                "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.",
                Arrays.asList(Genres.ANIMATION, Genres.FAMILY, Genres.ADVENTURE, Genres.COMEDY)));
        expected.add(new Movie(
                "Forrest Gump",
                "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold through the perspective of an Alabama man with an IQ of 75.",
                Arrays.asList(Genres.DRAMA, Genres.ROMANCE, Genres.COMEDY)));
        assertEquals(expected, homeController.observableMovies);
    }
}