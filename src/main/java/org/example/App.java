package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        HibernateUtil.getSessionFactory();

        Deliveryman deliveryman1 = new Deliveryman("BAza","Moldoshbaev",15);
        Deliveryman deliveryman2 = new Deliveryman("Aza","Koldoshbaev",25);
        Deliveryman deliveryman3 = new Deliveryman("Aza","Joldoshbaev",35);
        Deliveryman deliveryman4 = new Deliveryman("Aza","Ergeshbaev",45);
        Deliveryman deliveryman5 = new Deliveryman("JAza","Tumashbaev",30);
        Deliveryman deliveryman6 = new Deliveryman("Aza","Kumashbaev",40);
        Deliveryman deliveryman7 = new Deliveryman("Aza","Kumbaev",18);

      create(deliveryman1);
      create(deliveryman2);
      create(deliveryman3);
      create(deliveryman4);
      create(deliveryman5);
      create(deliveryman7);

        for (Deliveryman d:getAzaDelivetyman()){
            System.out.println(d);
        }

        update();
       delete();



    }

    public static int create (Deliveryman deliveryman){
        Session session  = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(deliveryman);
        session.getTransaction().commit();
        session.close();
        System.out.println("Succesfully created " + deliveryman);
        return deliveryman.getId();
    }



    public static List<Deliveryman> getAzaDelivetyman(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Deliveryman>deliverymen=session.createQuery("FROM Deliveryman d where d.name ='Aza' and d.age>20").getResultList();
        session.getTransaction().commit();
        session.close();

        return deliverymen;
    }



    public static void update (){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query=session.createQuery("update Deliveryman  set age=18 where name='Aza'");query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Succesfully updated " );
        session.close();
    }
    public static void delete () {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Deliveryman where name='Aza'");query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Succesfully deleted ");
        session.close();
    }
}
