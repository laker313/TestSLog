package com.example.restfulltest.repositories;



import com.example.restfulltest.models.FrontLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogFrontRepository implements LogRepositoryInterface<FrontLog> {
    @Autowired
    public LogFrontRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    SessionFactory sessionFactory;

    @Override
    public Integer save(FrontLog frontLog) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Integer id= (Integer) session.save(frontLog);
        tr.commit();
        session.close();
        return id;
    }

    @Override
    public FrontLog search(Integer id) {
        return null;
    }

    @Override
    public FrontLog delete(Integer id) {
        return null;
    }
}

