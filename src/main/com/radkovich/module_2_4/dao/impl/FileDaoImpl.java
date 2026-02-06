package com.radkovich.module_2_4.dao.impl;

import com.radkovich.module_2_4.dao.FileDao;
import com.radkovich.module_2_4.exception.EntityNotFoundException;
import com.radkovich.module_2_4.exception.HibernateRepositoryException;
import com.radkovich.module_2_4.model.FileEntity;
import com.radkovich.module_2_4.util.HibernateFactory;
import org.hibernate.Session;

import java.util.List;

public class FileDaoImpl implements FileDao {
    @Override
    public void save(FileEntity fileEntity) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.persist(fileEntity);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not save file!", e);
        }
    }

    @Override
    public FileEntity findById(Integer id) {
        try (Session session = HibernateFactory.openSession()) {
            FileEntity fileEntity = session.find(FileEntity.class, id);

            if (fileEntity == null) {
                throw new EntityNotFoundException(String.valueOf(FileEntity.class), id);
            }

            return fileEntity;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not find file with ID: " + id + "!", e);
        }
    }

    @Override
    public List<FileEntity> findAll() {
        try (Session session = HibernateFactory.openSession()) {
            return session.createSelectionQuery("FROM FileEntity ", FileEntity.class).getResultList();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get all files from DB!", e);
        }
    }

    @Override
    public void update(FileEntity fileEntity) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.merge(fileEntity);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not update file!", e);
        }
    }

    @Override
    public void delete(FileEntity fileEntity) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.remove(fileEntity);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not delete file!", e);
        }
    }
}
