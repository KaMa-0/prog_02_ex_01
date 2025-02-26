package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here
    private List<Genres> genres;

    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public List<Genres> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        movies.add(new Movie("The Wolf of Wall Street", "asdadsasd",  Arrays.asList(Genres.DRAMA)));
        movies.add(new Movie("Godfather","asdfadsfasf",  Arrays.asList(Genres.DRAMA)));

        return movies;
    }
}
