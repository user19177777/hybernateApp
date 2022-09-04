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
            Movie movie1 = session.get(Movie.class,28);
            Movie movie2 = session.get(Movie.class,29);
            Movie movie3 = session.get(Movie.class,30);
            actor.setMovies(new ArrayList<>(List.of(movie1,movie2,movie3)));
            List<Movie> movies = actor.getMovies();
            for (Movie movie:movies)
                System.out.println(movie.getName());

            session.getTransaction().commit();
        }
    }
}
