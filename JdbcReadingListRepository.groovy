@Repository
class JdbcReadingListRepository implements ReadingListRepository {

	@Autowired
	JdbcTemplate Jdbc //Dependency Inject JdbcTemplate

	List<book> findByReader(String reader) {
		jdbc.query(
			"SELECT ID, READER, ISBN, TITLE, AUTHOR, DESCRIPTION" +
			"FROM BOOK WHERE READER = ?",
			{
				rs, row ->
					new Book(
						id: rs.getLong(1),
						reader: rs.getString(2),
						isbn: rs.getString(3),
						title: rs.getString(4),
						author: rs.getString(5),
						descrption: rs.getString(6)
					)
			} as RowMapper, //RowMapper Clousure
			reader
		)
	}

	void save(Book book) {
		jdbc.update(
			"INSERT INTO BOOK " +
			"(READER, ISBN, TITLE, AUTHOR, DESCRIPTION) " +
			"VALUES (?, ?, ?, ?, ?)",
			book.reader,
			book.isbn,
			book.title,
			book.author,
			book.description
		)
	}
}