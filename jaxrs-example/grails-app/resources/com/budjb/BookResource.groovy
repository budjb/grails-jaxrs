package com.budjb

import io.swagger.annotations.Api

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
* @author Alex Stoia
*/
@Path('/api/book')
@Api('book')
class BookResource {
    @POST
    @Consumes([MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Produces([MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Path('/create')
    Response createBook(Book book) {
        // run with :
        // curl -i -X POST -H "Content-Type: application/xml" --data-binary "@src/main/sample/bookXMLSample.xml" http://localhost:8080/api/book/create
        // curl -i -X POST -H "Content-Type: application/json" --data-binary "@src/main/sample/bookJSONSample.json" http://localhost:8080/api/book/create
        book.save(flush: true, failOnError: true)
        return Response.status(201).entity(book).build()
    }

    @GET
    @Produces([MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Path('/list')
    Response listBooks() {
        // run with
        // curl -i -X GET -H "Accept: application/json" http://localhost:8080/api/book/list
        // curl -i -X GET -H "Accept: application/xml" http://localhost:8080/api/book/list
        Collection<Book> books = Book.list()
        return Response.ok(books).build()
    }

    @POST
    @Consumes([MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Produces([MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Path('/update/{id}')
    Response updateBookPath(@PathParam("id") Long id, Book book) {
        // run with :
        // curl -i -X POST -H "Content-Type: application/xml" --data-binary "@src/main/sample/bookXMLSampleUpdate.xml" http://localhost:8080/api/book/update/1
        // curl -i -X POST -H "Content-Type: application/json" --data-binary "@src/main/sample/bookJSONSampleUpdate.json" http://localhost:8080/api/book/update/1
        return updateBook(id, book)
    }
    @POST
    @Consumes([MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Produces([MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Path('/update')
    Response updateBookQuery(@QueryParam("id") Long id, Book book) {
        // run with :
        // curl -i -X POST -H "Content-Type: application/xml" --data-binary "@src/main/sample/bookXMLSampleUpdate.xml" http://localhost:8080/api/book/update?id=1
        // curl -i -X POST -H "Content-Type: application/json" --data-binary "@src/main/sample/bookJSONSampleUpdate.json" http://localhost:8080/api/book/update?id=1
        return updateBook(id, book)
    }

    @DELETE
    @Path('/delete/{id}')
    Response deleteBookPath(@PathParam("id") Long id) {
        // run with :
        // curl -i -X DELETE http://localhost:8080/api/book/delete/1
        return deleteBook(id)
    }
    @DELETE
    @Path('/delete')
    Response deleteBookQuery(@QueryParam("id") Long id) {
        // run with :
        // curl -i -X DELETE http://localhost:8080/api/book/delete?id=1
        return deleteBook(id)
    }

    private Response updateBook(Long id, Book book) {
        Book existingBook = Book.get(id)
        existingBook.properties = book.properties
        existingBook.save(flush: true, failOnError: true)
        return Response.ok(existingBook).build()
    }

    private Response deleteBook(Long id) {
        Book.get(id).delete(flush: true)
        return Response.ok("Book with id ${id} deleted successfully".toString()).build()
    }
}