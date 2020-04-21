package dataStructureHW2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */
public class MovieDB {
	private MyList<Genre> genreList;
	public MovieDB() {
		genreList = new MyList<Genre>();
	}

	public void insert(MovieDBItem item) {
		//my code start
		int genreIndex = genreList.findIndex(new Genre(item.getGenre()));
		
		Genre targetGenre;

		if (genreIndex < 0) { //genre가 이미 존재!
			//genre를 찾아서
			targetGenre = genreList.nodeAt(-1*genreIndex).getItem();
			//MovieList에 add title!
			targetGenre.movieList.add(item.getTitle());
			//genreList.nodeAt(-1*genreIndex).getItem().movieList.add(item.getTitle());

		} else { // genre를 새로 넣어야 함!
			
			genreList.add(new Genre(item.getGenre()));
			
			targetGenre = genreList.nodeAt(genreIndex+1).getItem();
			//MovieList 에 add title!
			targetGenre.movieList.add(item.getTitle());
			//genreList.nodeAt(genreIndex+1).getItem().movieList.add(item.getTitle());

		}
	}

	public void delete(MovieDBItem item) {
		//my code start
		int genreIndex = genreList.findIndex(new Genre(item.getGenre()));

		if (genreIndex < 0) { //genre가 존재!
			Genre targetGenre = genreList.nodeAt(-1*genreIndex).getItem();
			MyList<String> targetMovieList = targetGenre.movieList;
			
			targetMovieList.delete(item.getTitle());
			if (targetMovieList.size() == 0) {	//삭제 후에 genre가 비면
				//genre를 지움
				genreList.delete(targetGenre);
			}
			/*
			genreList.nodeAt(-1*genreIndex).getItem().movieList.delete(item.getTitle());
			if (genreList.nodeAt(-1*genreIndex).getItem().movieList.size() == 0) {	//삭제 후에 genre가 비면
				//genre를 지움
				genreList.delete(genreList.nodeAt(-1*genreIndex).getItem());
			}
			*/
		} else { // genre가 없음!
			//아무것도 하지 않는다.
			return;
		}
	}

	public MyLinkedList<MovieDBItem> search(String term) {
		
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
		
		//my code start
		MovieDBItem newMDB;
		
		for (Genre genre : genreList) {
			for (String title : genre.movieList) {
				if (title.contains(term)) {
					newMDB = new MovieDBItem(genre.getItem(), title);
					results.add(newMDB);
				}
			}
		}
		
		return results;
	}

	public MyLinkedList<MovieDBItem> items() {
		
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
		
		//my code start
		//모든 node들을 results에 add해서 return 하면 된다.
		MovieDBItem newMDB;
		
		for (Genre genre : genreList) {
			for (String title : genre.movieList) {
				newMDB = new MovieDBItem(genre.getItem(), title);
				results.add(newMDB);
			}
		}

		return results;
	}
}

class Genre extends Node<String> implements Comparable<Genre> {
	MyList<String> movieList;
	public Genre(String name) {
		super(name);
		movieList = new MyList<String>();
	}

	@Override
	public int compareTo(Genre o) {
		return this.getItem().compareTo(o.getItem());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieList == null) ? 0 : movieList.hashCode());
		result = prime * result + ((this.getItem() == null) ? 0 : this.getItem().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Genre) {
			return this.getItem().equals(((Genre)obj).getItem());			
		} else {
			return false;
		}

	}
}

class MyList<T extends Comparable<T>> extends MyLinkedList<T> {	//Genre또는 Title을 담는 linked list
	public MyList() {
		super();
	}
	
	@Override
	public void add(T item) {// insert item in alphabetical order.
		int index = this.findIndex(item);
		
		if (index >= 0) {//list에 없으면
			this.nodeAt(index).insertNext(item);
		} else {
			return;
		}
		numItems += 1;
	}
	
	//전달받는 item이 list에 있으면 삭제, 없으면 아무것도 하지 않는다.
	public void delete(T item) {
		int index = this.findIndex(item);
		
		if (index < 0) {//item이 list에 있으면
			this.nodeAt(-1*(index+1)).removeNext();
		} else {//없으면
			//아무것도 하지 않는다.
			return;
		}
		numItems -= 1;
	}
	
	//삽입할 위치를 int 로 return. 동일한게 있을 경우 음수, 위치는 양수로 표현.
	public int findIndex(T want) {
		int index = 0;
		
		for (T node : this) {
			if (want.compareTo(node) > 0) {
				index += 1;
			} else if (want.equals(node)) {//같은 게 있는 경우 음수 리턴
				return -1*(index+1);
			} else {	//적절한 위치 리턴
				return index;
			}
		}
		return index;	//0과 마지막을 위함
	}
}