import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */
public class MovieDB {
	MyLinkedList<Genre> genreList;

	public MovieDB() {
        // FIXME implement this
		// HINT: MovieDBGenre 클래스를 정렬된 상태로 유지하기 위한
		// MyLinkedList 타입의 멤버 변수를 초기화 한다.
		genreList = new MyLinkedList<>();
    }


    // for INSERT %genre% %title%
    public void insert(MovieDBItem newMovie) {
        // FIXME implement this
        // Insert the given item to the MovieDB.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
//        System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", newMovie.getGenre(), newMovie.getTitle());

        // Start
//		boolean isGenreExist = false;
//
//		for (Genre currGenre : genreList) {
//			if (currGenre.equals(newMovie.getGenre())) {
//				// 1. If there same genre exist
//				isGenreExist = true;
//				currGenre.getMovieList().add(newMovie);
//				break;
//			}
//		}
//
//		// 2. If there's no existing genre
//		if (!isGenreExist) {
//			Genre newGenre = new Genre(newMovie.getGenre());
//			newGenre.getMovieList().add(newMovie); // Adding new movie to new Genre
//			genreList.add(newGenre); // Adding new Genre to genreList
//		}

		// Start
        boolean hasInserted = false; // Is it essential?
		MyLinkedListIterator<Genre> genreIterator = genreList.iterator();
		while (genreIterator.hasNext()) {
			Genre currGenre = genreIterator.next();
			// 1. If there same genre exist
			if (currGenre.equals(newMovie.getGenre())) {

				MyLinkedListIterator<MovieDBItem> movieIterator = currGenre.getMovieList().iterator();
				while (movieIterator.hasNext()) {
					MovieDBItem currMovie = movieIterator.next();
					// 1-1). If there exist place in middle
					if (currMovie.compareTo(newMovie) > 0) {

						hasInserted = true;
						movieIterator.insert(newMovie);
						break;
					} else if (currMovie.equals(newMovie)) {
						hasInserted = true;
						break;
					}
				}
				// 1-2). Found genre, but should insert in the back
				if (!hasInserted) {
					hasInserted = true;
					currGenre.getMovieList().add(newMovie);
				}

				break; // break While outside this if

			// 2. Cannot found same Genre but should insert new genre IN HERE!
			} else if (currGenre.getItem().compareTo(newMovie.getGenre()) > 0) {

				hasInserted = true;
				Genre newGenre = new Genre(newMovie.getGenre());
				newGenre.getMovieList().add(newMovie); // Adding new movie to new Genre
				genreIterator.insert(newGenre); // Adding new Genre to genreList
                break;
//			    break; // Keep the index
			}
		}

		// 3. Genre should be added in HERE(kept index) or last place
		if (!hasInserted) {
			Genre newGenre = new Genre(newMovie.getGenre());
			newGenre.getMovieList().add(newMovie); // Adding new movie to new Genre
			genreList.add(newGenre);
		}
	}

	// for DELETE %genre% %title%
    public void delete(MovieDBItem newMovie) {
        // FIXME implement this
        // Remove the given item from the MovieDB.

		// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
//        System.err.printf("[trace] MovieDB: DELETE [%s] [%s]\n", newMovie.getGenre(), newMovie.getTitle());

        // Start
		Iterator<Genre> genreIterator = genreList.iterator();
		while (genreIterator.hasNext()) {
			Genre currGenre = genreIterator.next();
			if (currGenre.equals(newMovie.getGenre())) {
				// 1. If there same genre exist
				Iterator<MovieDBItem> movieIterator = currGenre.getMovieList().iterator();
				while (movieIterator.hasNext()) {
					// 2. If there same movie exist
					MovieDBItem currMovie = movieIterator.next();
					if (currMovie.equals(newMovie)) {
						movieIterator.remove();

						// 3. If movied-removed && Genre is now empty
						if (currGenre.getMovieList().size() == 0) {
							genreIterator.remove();
						}
						break;
					}

				}
				break;
			}
		}
	}

	// for SEARCH %term%
    public MyLinkedList<MovieDBItem> search(String term) {
        // FIXME implement this
        // Search the given term from the MovieDB.
        // You should TODO:return a linked list of MovieDBItem.
        // The search command is handled at SearchCmd class.
    	
    	// Printing search results is the responsibility of SearchCmd class. 
    	// So you must TODO:not use System.out in this method to achieve specs of the assignment.
    	
        // This tracing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
//    	System.err.printf("[trace] MovieDB: SEARCH [%s]\n", term);
    	
    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.
		// Start
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

        for (Genre currGenre : genreList) {
        	for (MovieDBItem currMovie : currGenre.getMovieList()) {
        		if (currMovie.getTitle().contains(term)) {
        			results.add(currMovie);
				}
			}
		}

        return results;
    }

	// for PRINT
    public MyLinkedList<MovieDBItem> items() {
        // FIXME implement this
        // Search the given term from the MovieDatabase.
        // You should return a linked list of QueryResult.
        // The print command is handled at PrintCmd class.

    	// Printing movie items is the responsibility of PrintCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
//        System.err.printf("[trace] MovieDB: ITEMS\n");

    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.

		// Start
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

		for (Genre currGenre : genreList) {
			for (MovieDBItem currMovie : currGenre.getMovieList()) {
				results.add(currMovie);
			}
		}

		return results;
	}

}

class Genre extends Node<String> implements Comparable<Genre> {
//	private String item; = genreName
//	private Node<String> next;
	private MyLinkedList<MovieDBItem> genreMovieList;

	public Genre(String genreName) {
		super(genreName);
		genreMovieList = new MyLinkedList<>();
	}

	public MyLinkedList<MovieDBItem> getMovieList() {
		return this.genreMovieList;
	}

	@Override
	public int compareTo(Genre o) {
	    return getItem().compareTo(o.getItem());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getItem() == null) ? 0 : getItem().hashCode());
		result = prime * result + ((getMovieList() == null) ? 0 : getMovieList().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
	    return this.getItem().equals(obj);
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//
//		Genre other = (Genre) obj;
//		if (!getItem().equals(other.getItem()))
//			return false;
//
//		return true;
		// Can do more, but it's excessive.
		// Cuz, It should return true, only if Genre:Name is same
//
//		if (getTitleList() == null) {
//			if (other.getTitleList() != null)
//				return false;
//		}
//
//		if (this.getTitleList().size() != other.getTitleList().size())
//			return false;
	}
}

//class MovieList implements ListInterface<String> {
//	// Member of (included in) Genre
//	// Saving the list of titles
//	public MovieList() {
//	}
//
//	@Override
//	public Iterator<String> iterator() {
//		throw new UnsupportedOperationException("not implemented yet");
//	}
//
//	@Override
//	public boolean isEmpty() {
//		throw new UnsupportedOperationException("not implemented yet");
//	}
//
//	@Override
//	public int size() {
//		throw new UnsupportedOperationException("not implemented yet");
//	}
//
//	@Override
//	public void add(String item) {
//		throw new UnsupportedOperationException("not implemented yet");
//	}
//
//	@Override
//	public String first() {
//		throw new UnsupportedOperationException("not implemented yet");
//	}
//
//	@Override
//	public void removeAll() {
//		throw new UnsupportedOperationException("not implemented yet");
//	}
//}