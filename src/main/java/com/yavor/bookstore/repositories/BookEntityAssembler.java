package com.yavor.bookstore.repositories;

import com.yavor.bookstore.repositories.entities.BookEntity;
import com.yavor.bookstore.web.rest.resources.BookResource;

import java.util.List;
import java.util.stream.Collectors;

public class BookEntityAssembler {

    public BookResource toBookResource(BookEntity entity) {

        BookResource resource = new BookResource();
        resource.setId(entity.getId());
        resource.setTitle(entity.getTitle());
        resource.setDescription(entity.getDescription());
        resource.setUnitCost(entity.getUnitCost());
        resource.setIsbn(entity.getIsbn());
        resource.setPublicationDate(entity.getPublicationDate());
        resource.setNumberOfPages(entity.getNumberOfPages());
        resource.setImageUrl(entity.getImageUrl());
        resource.setLanguage(entity.getLanguage());

        return resource;
    }

    public List<BookResource> toBookResources(List<BookEntity> entities) {
        return entities.stream()
                .map(this::toBookResource)
                .collect(Collectors.toList());
    }

    public BookEntity toBookEntity(BookResource resource) {

        BookEntity entity = new BookEntity();
        entity.setTitle(resource.getTitle());
        entity.setDescription(resource.getDescription());
        entity.setUnitCost(resource.getUnitCost());
        entity.setIsbn(resource.getIsbn());
        entity.setPublicationDate(resource.getPublicationDate());
        entity.setNumberOfPages(resource.getNumberOfPages());
        entity.setImageUrl(resource.getImageUrl());
        entity.setLanguage(resource.getLanguage());

        return entity;
    }
}
