package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    protected ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list
        sortMovies(true);

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("No filter"); // no filter option
        genreComboBox.getItems().addAll(Genres.values()); // fill with other genres

        //Event handlers
        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Filter Button Pressed
        searchBtn.setOnAction(actionEvent -> {
            String searchQuery = searchField.getText();
                                                            // this section checks for null* exception and sets value to null
            String selectedGenre = genreComboBox.getValue() != null ? genreComboBox.getValue().toString() : null;
            List<Movie> filteredList = filterMovies(searchQuery, selectedGenre);

            // Fix für doppelte oder falsche Einträge
            observableMovies.clear();
            observableMovies.addAll(filteredList);

            // Setze `ListView` neu
            movieListView.setItems(null); // Verhindert falsche Einträge
            movieListView.setItems(observableMovies); // Setzt die Liste richtig

        });

        // Sort Button Pressed
        sortBtn.setOnAction(actionEvent -> {
            boolean ascendingOrder;
            if (sortBtn.getText().equals("Sort (asc)")) {
                // switch to descending order
                sortBtn.setText("Sort (desc)"); //update button text
                ascendingOrder = false; // descending order
            } else {
                // switch to ascending order
                sortBtn.setText("Sort (asc)"); // update button text
                ascendingOrder = true; // ascending order
            }

            sortMovies(ascendingOrder);
        });


    }

    public void sortMovies(boolean ascendingOrder) {
        Comparator<Movie> movieComparator = Comparator.comparing(Movie::getTitle); // compare Movie objs based on title
        if (ascendingOrder) {
            observableMovies.sort(movieComparator); //ascending
        } else {
            observableMovies.sort(movieComparator.reversed()); //descending
        }
    }

    public List<Movie> filterMovies(String searchQuery, String selectedGenre) {
        List<Movie> filteredList = new ArrayList<>(allMovies); //copy the original list to keep original list safe
        // Filter by genre
        if (selectedGenre != null && !selectedGenre.equals("No filter")) {
            try {
                Genres genre = Genres.valueOf(selectedGenre);
                filteredList = filteredList.stream()
                        .filter(movie -> movie.getGenres().contains(genre))
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                // Invalid genre
                System.err.println("Invalid genre: " + selectedGenre);
            }
        }
        if (searchQuery != null && !searchQuery.isEmpty()) {
            // key-insensitive
            filteredList = filteredList.stream() // we could also use foreach instead of stream API
                    .filter(movie ->
                            movie.getTitle().toLowerCase().contains(searchQuery) ||
                                    movie.getDescription().toLowerCase().contains(searchQuery))
                    .collect(Collectors.toList());
        }
        // DEBUGGING – Ausgabe der gefilterten Liste vor dem Setzen
        System.out.println("Gefilterte Liste Größe: " + filteredList.size());

        // DEBUGGING – Ausgabe der gefilterten Liste vor dem Setzen
        System.out.println("Gefilterte Liste Größe: " + filteredList.size());

        // DEBUGGING – Prüfen, ob observableMovies richtig aktualisiert wurde
        System.out.println("observableMovies Größe nach Update: " + observableMovies.size());
        return filteredList;
    }

}