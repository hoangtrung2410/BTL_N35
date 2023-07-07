package com.example.btl_n35;

import com.example.btl_n35.dao.*;
import com.example.btl_n35.database.HibernateUtil;
import com.example.btl_n35.entity.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Test extends Application {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

//                QuestionDao.getInstance().selectALl();



//                Set<Answer> answers = QuestionDao.getInstance().selectById(4).getAnswers();
//                for(Answer answer: answers ){
//                    System.out.println(answer.getId());
//                }

                transaction.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}