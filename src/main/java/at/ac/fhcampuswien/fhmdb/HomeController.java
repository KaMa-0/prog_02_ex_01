package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)

        genreComboBox.getItems().add("No filter");
        for (Genres genre: Genres.values()) {
            genreComboBox.getItems().add(genre);
        }
        genreComboBox.setPromptText("Filter by Genre");
        //Event handlers
        // TODO add event handlers to buttons and call the regarding methods
        searchBtn.setOnAction(actionEvent -> {
            String searchQuery = searchField.getText().toLowerCase();
            String selectedGenre = genreComboBox.getValue() != null ? genreComboBox.getValue().toString() : null;

            filterMovies(searchQuery, selectedGenre);

        });
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });


    }
    private void filterMovies(String searchQuery, String selectedGenre) {

        List<Movie> filteredList = new ArrayList<>(allMovies); //copy the original list to keep original list safe
        List<Movie> result = new ArrayList<>();
        // Metin araması uygula
        /*
        if (searchQuery != null && !searchQuery.isEmpty()) {
            // Büyük-küçük harf duyarsız arama
            filteredList = filteredList.stream() // we could also use foreach instead of stream API
                    .filter(movie ->
                            movie.getTitle().toLowerCase().contains(searchQuery) || //
                            movie.getDescription().toLowerCase().contains(searchQuery))
                    .collect(Collectors.toList());
        }
         */

        for (Movie movie: filteredList) {
            if (movie.getTitle().toLowerCase().contains(searchQuery) || movie.getDescription().toLowerCase().contains(searchQuery)) {
                result.add(movie);
            }
        }
        filteredList = result;


        // Tür filtresi uygula
        if (selectedGenre != null && !selectedGenre.equals("No Filter")) {
            try {
                Genres genre = Genres.valueOf(selectedGenre);
                filteredList = filteredList.stream()
                        .filter(movie -> movie.getGenres().contains(genre))
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                // Geçersiz tür ismi
                System.err.println("Invalid genre: " + selectedGenre);
            }
        }

        // UI'ı güncelle
        observableMovies.clear();
        observableMovies.addAll(filteredList);
    }
}