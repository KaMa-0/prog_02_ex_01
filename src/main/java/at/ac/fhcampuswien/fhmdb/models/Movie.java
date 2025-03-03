package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {

    private String title;
    private String description;
    private List<Genres> genres = new ArrayList<>();

    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres.addAll(genres);
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
                Arrays.asList(Genres.ANIMATION, Genres.FAMILY, Genres.ADVENTURE, Genres.COMEDY)));
        movies.add(new Movie(
                "Forrest Gump",
                "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold through the perspective of an Alabama man with an IQ of 75.",
                Arrays.asList(Genres.DRAMA, Genres.ROMANCE, Genres.COMEDY)));
        movies.add(new Movie(
                "Inception",
                "A thief who enters the dreams of others to steal secrets from their subconscious is given a task to plant an idea into the mind of a CEO.",
                Arrays.asList(Genres.ACTION, Genres.SCIENCE_FICTION, Genres.THRILLER)));
        movies.add(new Movie(
                "The Shawshank Redemption",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                Arrays.asList(Genres.DRAMA)));
        movies.add(new Movie(
                "The Dark Knight",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                Arrays.asList(Genres.ACTION, Genres.CRIME, Genres.DRAMA)));
        movies.add(new Movie(
                "Pulp Fiction",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                Arrays.asList(Genres.CRIME, Genres.DRAMA)));
        movies.add(new Movie(
                "The Lord of the Rings: The Fellowship of the Ring",
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                Arrays.asList(Genres.ADVENTURE, Genres.FANTASY)));
        movies.add(new Movie(
                "The Matrix",
                "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                Arrays.asList(Genres.ACTION, Genres.SCIENCE_FICTION)));
        movies.add(new Movie(
                "Interstellar",
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival as Earth becomes uninhabitable.",
                Arrays.asList(Genres.ADVENTURE, Genres.DRAMA, Genres.SCIENCE_FICTION)));

        movies.add(new Movie(
                "The Silence of the Lambs",
                "A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer who skins his victims.",
                Arrays.asList(Genres.CRIME, Genres.DRAMA, Genres.THRILLER)));

        movies.add(new Movie(
                "Spirited Away",
                "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, where humans are changed into beasts.",
                Arrays.asList(Genres.ANIMATION, Genres.ADVENTURE, Genres.FANTASY)));

        movies.add(new Movie(
                "The Godfather",
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                Arrays.asList(Genres.CRIME, Genres.DRAMA)));

        movies.add(new Movie(
                "Titanic",
                "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.",
                Arrays.asList(Genres.DRAMA, Genres.ROMANCE)));

        return movies;
    }
}