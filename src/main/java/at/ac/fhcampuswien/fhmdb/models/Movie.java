package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {

    private final int lengthInMinutes;
    private final double rating;
    private String title;
    private String description;
    private int releaseYear;
    private List<Genres> genres = new ArrayList<>();

    public Movie(String title, String description, List<Genres> genres, int lengthInMinutes, double rating) {
        this.title = title;
        this.description = description;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
        this.genres.addAll(genres);
        this.releaseYear = 0;
    }

    public Movie(String title, String description, List<Genres> genres, int releaseYear, int lengthInMinutes, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
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

    public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(
                "Finding Nemo",
                "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.",
                Arrays.asList(Genres.ANIMATION, Genres.FAMILY, Genres.ADVENTURE, Genres.COMEDY),
                2003,
                100,
                8.2));

        movies.add(new Movie(
                "Forrest Gump",
                "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold through the perspective of an Alabama man with an IQ of 75.",
                Arrays.asList(Genres.DRAMA, Genres.ROMANCE, Genres.COMEDY),
                1994,
                142,
                8.8));

        movies.add(new Movie(
                "Inception",
                "A thief who enters the dreams of others to steal secrets from their subconscious is given a task to plant an idea into the mind of a CEO.",
                Arrays.asList(Genres.ACTION, Genres.SCIENCE_FICTION, Genres.THRILLER),
                2010,
                148,
                8.8));

        return movies;
    }
}
