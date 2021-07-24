package com.yavor.bookstore.web.rest.controllers;

import com.yavor.bookstore.web.rest.controllers.util.NumberGenerator;
import com.yavor.bookstore.web.rest.resources.BookResource;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/books")
public class BookController {

    @Inject
    BookRepository repository;

    @Inject
    NumberGenerator numberGenerator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {

        List<BookResource> resources = repository.getAllBooks();

        if (resources.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(resources).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveBook(@NotNull BookResource resource,
                             @Context UriInfo info) {

        String isbn = numberGenerator.generateNumber();

        resource.setIsbn(isbn);

        BookResource savedBook = repository.saveBook(resource);

        URI uri = info.getBaseUriBuilder()
                .path(Long.toString(savedBook.getId()))
                .build();

        return Response
                .created(uri)
                .build();
    }
}
