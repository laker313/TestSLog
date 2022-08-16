package com.example.restfulltest.repositories;



import com.example.restfulltest.models.FrontLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
        Session session = sessionFactory.openSession();
        FrontLog frontLog = session.get(FrontLog.class,id);
        session.close();
        return frontLog;
    }


    public void delete(FrontLog frontLog) {
        System.out.println(frontLog);
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(frontLog);
        tx1.commit();
        session.close();

    }

    @Override
    public List<FrontLog> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FrontLog> cq = cb.createQuery(FrontLog.class);
        Root<FrontLog> rootEntry = cq.from(FrontLog.class);
        CriteriaQuery<FrontLog> all = cq.select(rootEntry);
        TypedQuery<FrontLog> allQuery = session.createQuery(all);
        return allQuery.getResultList();

    }

    @Override
    public FrontLog update(FrontLog frontLog) {
        System.out.println(frontLog);
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(frontLog);
        frontLog =session.get(FrontLog.class,frontLog.getId());
        tx1.commit();
        session.close();
        return frontLog;
    }
}


