package com.packtpub.felix;

import java.util.Set;

import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.BundleContext;

import com.packtpub.felix.bookshelf.inventory.api.Book;
import com.packtpub.felix.bookshelf.service.api.BookshelfService;
import com.packtpub.felix.bookshelf.service.api.InvalidCredentialsException;

public class BookshelfServiceProxy {
	public static final String SCOPE = "book";
	public static final String[] FUNCTIONS = new String[] { "search" };
	private BundleContext context;

	public BookshelfServiceProxy(BundleContext context) {
		this.context = context;
	}

	@Descriptor("Search books by author, title, or category")
	public Set<Book> search(
			@Descriptor("username") String username,
			@Descriptor("password") String password,
			@Descriptor("search on attribute: author, title, or category") String attribute,
			@Descriptor("match like (use % at the beginning or end of <like> for wild-card)") String filter)
			throws InvalidCredentialsException {
		BookshelfService service = lookupService();
		String sessionId = service.login(username, password.toCharArray());
		Set<String> results;
		if ("title".equals(attribute)) {
			results = service.searchBooksByTitle(sessionId, filter);
		} else if ("author".equals(attribute)) {
			results = service.searchBooksByAuthor(sessionId, filter);
		} else if ("category".equals(attribute)) {
			results = service.searchBooksByCategory(sessionId, filter);
		} else {
			throw new RuntimeException(
					"Invalid attribute, expecting one of { 'title', "
							+ "'author', 'category' } got '" + attribute + "'");
		}
		return getBooks(sessionId, service, results);
	}
}
