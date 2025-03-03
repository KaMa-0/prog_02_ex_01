package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomeControllerTest {

    private HomeController homeController;

    // intial state
    @BeforeEach
    void set_up() {
        homeController = new HomeController();
        // 3 dummy movies
        homeController.allMovies = List.of(
                new Movie("Zootopia", "A city of animals", List.of(Genres.ANIMATION, Genres.FAMILY)),
                new Movie("Interstellar", "A space adventure", List.of(Genres.THRILLER, Genres.SCIENCE_FICTION)),
                new Movie("Avatar", "A marine on an alien planet", List.of(Genres.ANIMATION, Genres.ADVENTURE))
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
    void filter_movies_by_title() {
        String searchQuery = "ava";
        List<Movie> filteredList = homeController.filterMovies(searchQuery, null);
        homeController.observableMovies = FXCollections.observableArrayList();
        homeController.observableMovies.addAll(filteredList);
        // check results
        assertEquals(1, homeController.observableMovies.size());
        assertEquals("Avatar", homeController.observableMovies.get(0).getTitle());
    }
    @Test
    void filter_movies_based_on_genre() {
        String selectedGenre = "ANIMATION";
        homeController.observableMovies = FXCollections.observableArrayList();
        List<Movie> filteredList = homeController.filterMovies(null, selectedGenre);
        homeController.observableMovies.addAll(filteredList);
        assertEquals(2, homeController.observableMovies.size());
        assertEquals("Zootopia", homeController.observableMovies.get(0).getTitle());
        assertEquals("Avatar", homeController.observableMovies.get(1).getTitle());
    }

    @Test
    void filter_movies_based_on_query_nothing_found() {
        // search for text that doesn't exist
        String searchQuery = "nonexistent_abcs123";
        homeController.observableMovies = FXCollections.observableArrayList();
        List<Movie> filteredList = homeController.filterMovies(searchQuery, null);
        homeController.observableMovies.addAll(filteredList);
        assertTrue(homeController.observableMovies.isEmpty());
    }

    @Test
    void no_filter_return_all_movies() {
        // empty filter
        String searchQuery = "";
        String selectedGenre = "No filter";
        List<Movie> filteredList = homeController.filterMovies(searchQuery, selectedGenre);
        homeController.observableMovies = FXCollections.observableArrayList();
        homeController.observableMovies.addAll(filteredList);
        // check if all the films are listed
        assertEquals(3, homeController.observableMovies.size());
        assertEquals("Zootopia", homeController.observableMovies.get(0).getTitle());
        assertEquals("Interstellar", homeController.observableMovies.get(1).getTitle());
        assertEquals("Avatar", homeController.observableMovies.get(2).getTitle());
    }
}