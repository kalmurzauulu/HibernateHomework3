package org.example;

import org.hibernate.Session;

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

//      create(deliveryman1);
//      create(deliveryman2);
//      create(deliveryman3);
//      create(deliveryman4);
//      create(deliveryman5);
//      create(deliveryman7);

        for (Deliveryman d:getAzaDelivetyman()){
            System.out.println(d);
        }
        update(3,18);
        update(4,18);
        update(6,18);
        update(2,18);

        deleted(3);
        deleted(4);
        deleted(6);
        deleted(2);


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



    public static void update (int id,int age){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Deliveryman deliveryman = session.get(Deliveryman.class,id);
        deliveryman.setAge(age);
        session.getTransaction().commit();
        System.out.println("Succesfully updated " );
        session.close();
    }
    public static void deleted (int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Deliveryman deliveryman = session.get(Deliveryman.class, id);
        session.delete(deliveryman);
        session.getTransaction().commit();
        System.out.println("Succesfully deleted "+ deliveryman);
        session.close();
    }
}
