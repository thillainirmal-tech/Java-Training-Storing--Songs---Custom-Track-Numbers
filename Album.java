import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    //  Add song with validation
    public boolean addSong(String title, double duration) {
        if (title == null || title.isEmpty() || duration <= 0) {
            System.out.println("Invalid song details. Title must be non-empty, and duration must be positive.");
            return false;
        }

        if (findSong(title) == null) {
            songs.add(new Song(title, duration));
            System.out.println("Song added: " + title + " (" + duration + " mins)");
            return true;
        }

        System.out.println("Song already exists: " + title);
        return false;
    }

    //  Find song by title
    private Song findSong(String title) {
        for (Song checkedSong : songs) {
            if (checkedSong.getTitle().equalsIgnoreCase(title)) {
                return checkedSong;
            }
        }
        return null;
    }

    //  Add song to playlist by track number
    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        int index = trackNumber - 1;

        if (index >= 0 && index < songs.size()) {  // Fixed condition
            playList.add(songs.get(index));
            System.out.println("Added to playlist: " + songs.get(index).getTitle());
            return true;
        } else {
            System.out.println("Invalid track number: " + trackNumber);
            return false;
        }
    }

    //  Add song to playlist by title
    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        Song checkedSong = findSong(title);

        if (checkedSong != null) {
            playList.add(checkedSong);
            System.out.println("Added to playlist: " + title);
            return true;
        } else {
            System.out.println("Song not found: " + title);
            return false;
        }
    }

    //  Display all songs in the album
    public void printAlbum() {
        System.out.println("\nAlbum: " + name + " by " + artist);
        System.out.println("---------------------------------");
        for (int i = 0; i < songs.size(); i++) {
            System.out.printf("%-2d. %-20s (%.2f mins)%n", i + 1, songs.get(i).getTitle(), songs.get(i).getDuration());
        }
        System.out.println("---------------------------------");
    }
}
