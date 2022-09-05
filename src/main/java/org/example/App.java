package org.example;

import org.example.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //конфигурируем Hibernate, добавляем класс с @Entity
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();


        try (sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Actor actor = session.get(Actor.class,10);

            session.getTransaction().commit();
            //eager выставлено, поэтому подгружены связанные сущности, поэтому есть доступ вне сессии, объект на котором вызываются геттеры должен быть в Persistent context
            System.out.println(actor.getMovies());
        }
    }
}
