@Controller
@RequestMaping("/")
class ReadingListController {
	String reader = "hreeman"

	@Autowired
	ReadingListRepository readingListRepository

	@RequestMapping(method=RequestMethod.GET)
	def readersBooks(Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader)

		if (readingList) {
			model.addAttribute("books", readingList) //add to model
		}

		"readingList" //return to View Name
	}

	@RequestMapping(method=RequestMethod.POST)
	def addToReadingList(Book book) {
		book.setReader(reader)
		readingListRepository.save(book) //save the book

		"redirect:/" //redirect after POST
	}
}