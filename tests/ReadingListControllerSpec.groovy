import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.mockito.Mockito.*

class ReadingListControllerSpec extends Specification {
	MockMvc mockMvc
	List<Book> expectedList

	def setup() {
		expectedList = new ArrayList<Book>();

		expectedList.add(
			new Book(
				id: 1,
				reader: "hreeman",
				isbn: "1234567890",
				title: "Spring Boot in Action",
				author: "Craig Walls",
				description: "Spring Boot in Action is ..."
			)
		)

		def mockRepo = mock(ReadingListRepository.class)
		when(mockRepo.findByReader("hreeman")).thenReturn(expectedList)

		def controller = new ReadingListController(readingListRepository: mockRepo)

		mockMvc = standaloneSetup(controller).build()
	}

	def "Shuould put list returned from repository into model"() {
		when:
			def response = mockMvc.perform(get("/"))

		then:
			response.andExpect(view().name("readingList"))
					.andExpect(model().attribute("books", expectedList))
	}
}