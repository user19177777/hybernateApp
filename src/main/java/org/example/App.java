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


            Person person = new Person("person7",44);
            Item item1 = new Item("item778",person);
            Item item2 = new Item("item779",person);
            Item item3 = new Item("item780",person);

            person.setItems(new ArrayList<Item>(Collections.singletonList(item1)));
            person.setItems(new ArrayList<Item>(Collections.singletonList(item2)));
            //person.setItems(new ArrayList<Item>(Collections.singletonList(item3)));

            //сохранит в бд и person и item
            session.save(person);

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }

    }
}
