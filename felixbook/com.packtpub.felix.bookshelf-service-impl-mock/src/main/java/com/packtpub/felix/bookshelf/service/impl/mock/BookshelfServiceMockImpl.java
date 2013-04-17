package com.packtpub.felix.bookshelf.service.impl.mock;

import java.util.Arrays;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.packtpub.felix.bookshelf.inventory.api.Book;
import com.packtpub.felix.bookshelf.inventory.api.BookAlreadyExistsException;
import com.packtpub.felix.bookshelf.inventory.api.BookInventory;
import com.packtpub.felix.bookshelf.inventory.api.BookNotFoundException;
import com.packtpub.felix.bookshelf.inventory.api.InvalidBookException;
import com.packtpub.felix.bookshelf.service.api.BookInventoryNotRegisteredRuntimeException;
import com.packtpub.felix.bookshelf.service.api.BookshelfService;
import com.packtpub.felix.bookshelf.service.api.InvalidCredentialsException;
import com.packtpub.felix.bookshelf.service.api.SessionNotValidRuntimeException;

public class BookshelfServiceMockImpl implements BookshelfService {

	private String sessionId;
	private BundleContext context;

	public BookshelfServiceMockImpl(BundleContext context) {
		this.context = context;
	}

	public String login(String username, char[] password)
			throws InvalidCredentialsException {
		if ("admin".equals(username)
				&& Arrays.equals(password, "admin".toCharArray())) {
			this.sessionId = Long.toString(System.currentTimeMillis());
			return this.sessionId;
		}
		throw new InvalidCredentialsException(username);
	}

	public void logout(String sessionId) {
		checkSession(sessionId);
		this.sessionId = null;
	}

	public boolean sessionIsValid(String sessionId) {
		return this.sessionId != null && this.sessionId.equals(sessionId);
	}

	protected void checkSession(String sessionId) {
		if (!sessionIsValid(sessionId)) {
			throw new SessionNotValidRuntimeException(sessionId);
		}
	}

	public Set<String> getGroups(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addBook(String session, String isbn, String title,
			String author, String category, int rating)
			throws BookAlreadyExistsException, InvalidBookException {
		// TODO Auto-generated method stub

	}

	public void modifyBookCategory(String session, String isbn, String category)
			throws BookNotFoundException, InvalidBookException {
		// TODO Auto-generated method stub

	}

	public void modifyBookRating(String session, String isbn, int rating)
			throws BookNotFoundException, InvalidBookException {
		// TODO Auto-generated method stub

	}

	public void removeBook(String session, String isbn)
			throws BookNotFoundException {
		// TODO Auto-generated method stub

	}

	public Book getBook(String sessionId, String isbn)
			throws BookNotFoundException {
		checkSession(sessionId);
		BookInventory inventory = lookupBookInventory();
		return inventory.loadBook(isbn);
	}

	private BookInventory lookupBookInventory() {
		String name = BookInventory.class.getName();
		ServiceReference ref = this.context.getServiceReference(name);
		if (ref == null) {
			throw new BookInventoryNotRegisteredRuntimeException(name);
		}
		return (BookInventory) this.context.getService(ref);
	}

	public Set<String> searchBooksByCategory(String session, String categoryLike) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> searchBooksByAuthor(String session, String authorLike) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> searchBooksByTitle(String session, String titleLike) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> searchBooksByRating(String session, int ratingLower,
			int ratingUpper) {
		// TODO Auto-generated method stub
		return null;
	}

}
