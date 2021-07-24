package com.yavor.bookstore.repositories;

import com.yavor.bookstore.repositories.entities.BookEntity;
import com.yavor.bookstore.web.rest.controllers.BookRepository;
import com.yavor.bookstore.web.rest.resources.BookResource;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext(unitName = "bookstorePU")
    EntityManager entityManager;

    @Resource
    UserTransaction transaction;

    @Inject
    BookEntityAssembler assembler;

    @Override
    public List<BookResource> getAllBooks() {

        TypedQuery<BookEntity> query = entityManager.createQuery("SELECT b FROM BookEntity b", BookEntity.class);
        List<BookEntity> entities = query.getResultList();

        return assembler.toBookResources(entities);
    }

    @Override
    public BookResource saveBook(BookResource book) {

        BookEntity entity = assembler.toBookEntity(book);

        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();

            return assembler.toBookResource(entity);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            throw new DataBaseException();
            } catch (SystemException systemException) {
                systemException.printStackTrace();
                throw new DataBaseException();
            }
        }
    }
}
