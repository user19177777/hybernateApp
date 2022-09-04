package org.example;

import org.example.models.Item;
import org.example.models.Passport;
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
                .addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            //transient state. hibernate не отслеживает сущности Person Passport
            Person person = new Person("Den",35);
            Passport  passport = new Passport(5555,person);
            person.setPassport(passport);
            //persistent(managed) state. Сущность переходит в отслеживаемое состояние,
            //объекты-сущности переходят в Persistent context
            //при вызове сеттеров будет генерироваться sql код(изменять данные в бд)
            session.save(person);
            session.save(passport);

            System.out.println(person.getName());
            //detached state. объекты-сущности перестают быть в Persistent context,
            //не отслеживаются hibernate
            //при вызове сеттеров не будет генерироваться sql код(не будет изменять данные в бд)
            //имя в бд не изменится
            session.detach(person);
            person.setName("Ben");
            System.out.println(person.getName());
            //обратно помещает объект в Persistent context
            //имя в бд изменится
            session.merge(person);
            person.setName("Ben");
            System.out.println(person.getName());



            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }

    }
}
