package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    private HomeController homeController;

    // intial state
    @BeforeEach
    void set_up() {
        homeController = new HomeController();
        // 3 dummy movies
        homeController.allMovies = List.of(
                new Movie("Zootopia", "A city of animals", List.of()),
                new Movie("Interstellar", "A space adventure", List.of()),
                new Movie("Avatar", "A marine on an alien planet", List.of())
        );
        // Filme zu Liste hinzufügen
        homeController.observableMovies.addAll(homeController.allMovies);
    }

    // Testcase für die Aufsteigende Sortierung
    @Test
    void sort_movies_ascending() {
        homeController.sortMovies(true); // Filme in Aufsteigender Reihefolge sortieren
        // Absteigende Sortierung überprüfen
        assertEquals("Avatar", homeController.observableMovies.get(0).getTitle());
        assertEquals("Interstellar", homeController.observableMovies.get(1).getTitle());
        assertEquals("Zootopia", homeController.observableMovies.get(2).getTitle());
    }

    // Testcase für die Absteigender Sortierung
    @Test
    void sort_movies_descending() {
        homeController.sortMovies(false); // Filme in Absteigender Reihefolge sortieren
        // Absteigende Sortierung überprüfen
        assertEquals("Zootopia", homeController.observableMovies.get(0).getTitle());
        assertEquals("Interstellar", homeController.observableMovies.get(1).getTitle());
        assertEquals("Avatar", homeController.observableMovies.get(2).getTitle());
    }


    @Test
    void filter_movies_based_on_query() {

    }

    @Test
    void filter_movies_based_on_genre() {

    }

    @Test
    void filter_movies_based_on_query_nothing_found() {
    }

    @Test
    void no_filter_return_all_movies() {

    }
}