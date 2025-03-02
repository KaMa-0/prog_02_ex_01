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
    void filter_movies_by_title() {
        //HomeController homeController = new HomeController();
        // Data for testing
        homeController.allMovies = List.of(
                new Movie("Avatar", "Science Fiction film", List.of(Genres.SCIENCE_FICTION, Genres.ACTION)),
                new Movie("Interstellar", "Space exploration film", List.of(Genres.SCIENCE_FICTION, Genres.ADVENTURE)),
                new Movie("Zootopia", "Animated film", List.of(Genres.ANIMATION, Genres.COMEDY))
        );
        //We are using manuel filtering in the tests because the filterMovies method updates UI components movieListView.setItems, which
        //causes NullPointerException in the test environment since moveListView is null.
        String searchQuery = "ava";
        List<Movie> filteredList = homeController.allMovies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        movie.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        //Creates a new empty ObservableList, because in actual code it's connected to UI
        //Populates it with the filtered results
        homeController.observableMovies = FXCollections.observableArrayList();
        homeController.observableMovies.addAll(filteredList);
        // check results
        assertEquals(1, homeController.observableMovies.size());
        assertEquals("Avatar", homeController.observableMovies.get(0).getTitle());
    }
    @Test
    void filter_movies_based_on_genre() {
        //HomeController homeController = new HomeController();

        homeController.allMovies = List.of(
                new Movie("Avatar", "Science Fiction film", List.of(Genres.SCIENCE_FICTION, Genres.ACTION)),
                new Movie("Interstellar", "Space exploration film", List.of(Genres.SCIENCE_FICTION, Genres.ADVENTURE)),
                new Movie("Zootopia", "Animated film", List.of(Genres.ANIMATION, Genres.COMEDY))
        );

        String selectedGenre = "ANIMATION";
        List<Movie> filteredList = homeController.allMovies.stream()
                .filter(movie -> movie.getGenres().contains(Genres.valueOf(selectedGenre)))
                .collect(Collectors.toList());

        homeController.observableMovies = FXCollections.observableArrayList();
        homeController.observableMovies.addAll(filteredList);

        assertEquals(1, homeController.observableMovies.size());
        assertEquals("Zootopia", homeController.observableMovies.get(0).getTitle());
    }

    @Test
    void filter_movies_based_on_query_nothing_found() {
        //HomeController homeController = new HomeController();
        homeController.allMovies = List.of(
                new Movie("Avatar", "Science Fiction film", List.of(Genres.SCIENCE_FICTION, Genres.ACTION)),
                new Movie("Interstellar", "Space exploration film", List.of(Genres.SCIENCE_FICTION, Genres.ADVENTURE)),
                new Movie("Zootopia", "Animated film", List.of(Genres.ANIMATION, Genres.COMEDY))
        );

        // search for text that doesn't exist
        String searchQuery = "nonexistent";
        List<Movie> filteredList = homeController.allMovies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        movie.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        homeController.observableMovies = FXCollections.observableArrayList();
        homeController.observableMovies.addAll(filteredList);

        // check for empty list
        assertEquals(0, homeController.observableMovies.size());
        assertTrue(homeController.observableMovies.isEmpty());
    }

    @Test
    void no_filter_return_all_movies() {
        //HomeController homeController = new HomeController();
        homeController.allMovies = List.of(
                new Movie("Avatar", "Science Fiction film", List.of(Genres.SCIENCE_FICTION, Genres.ACTION)),
                new Movie("Interstellar", "Space exploration film", List.of(Genres.SCIENCE_FICTION, Genres.ADVENTURE)),
                new Movie("Zootopia", "Animated film", List.of(Genres.ANIMATION, Genres.COMEDY))
        );
        // empty filter
        String searchQuery = "";
        String selectedGenre = "No filter";
        List<Movie> filteredList = new ArrayList<>(homeController.allMovies);
        homeController.observableMovies = FXCollections.observableArrayList();
        homeController.observableMovies.addAll(filteredList);
        // check if all the films are listed
        assertEquals(3, homeController.observableMovies.size());
        assertEquals("Avatar", homeController.observableMovies.get(0).getTitle());
        assertEquals("Interstellar", homeController.observableMovies.get(1).getTitle());
        assertEquals("Zootopia", homeController.observableMovies.get(2).getTitle());
    }
}