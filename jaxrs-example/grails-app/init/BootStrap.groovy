import grails.util.Environment
import com.budjb.Book
import com.budjb.Author

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT ||
                Environment.current == Environment.TEST) {
            bootstrapTestData()
        }
    }
    def destroy = {
    }

    private bootstrapTestData() {
        Author author = new Author(firstName: "John", lastName: "Smith",
                email: "jsmith@example.com").save(failOnError:true, flush:true)
        Book book1 = new Book(title: "Savior Of The West", isbn : "ISB1234",
                published: Date.parse('dd/mm/yyyy', '29/02/2012'), author:author).
                save(failOnError:true, flush:true)

    }
}
