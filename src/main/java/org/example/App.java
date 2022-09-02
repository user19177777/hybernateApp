package org.example;

import org.example.models.Item;
import org.example.models.Person;
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
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();


            Person person = session.get(Person.class,7);
            Item item = new Item("item777",person);

            person.setItems(new ArrayList<Item>(Collections.singletonList(item)));
            session.update(person);
            session.save(item);

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }

    }
}
