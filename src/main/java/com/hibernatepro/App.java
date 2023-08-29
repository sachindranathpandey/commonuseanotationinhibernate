package com.hibernatepro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
       System.out.println("Project Started");
       
       //SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
      // Creating Student
       
       Configuration cfg=new Configuration();
       cfg.configure("hibernate.cfg.xml");
       SessionFactory factory = cfg.buildSessionFactory();
       Student student=new Student();
       
       student.setId(1);;
       student.setName("Hemant");
       student.setCity("Lko");
       
//       Address Object
       
       Address address= new Address();
       address.setStreet("Street C");
       address.setCity("Delhi");
       address.setOpen(true);
       address.setAddedDate(new Date());
       address.setX(12.343);
       
       // reading image
       
       FileInputStream fis=new FileInputStream("src/main/java/logof.png");
       byte[] data=new byte[fis.available()];
       fis.read(data);
       
       address.setImage(data);
       
       
       Session session = factory.openSession();
       Transaction transaction = session.beginTransaction();
       session.save(student);
       //session.save(address);
       
       
       List<Student> products = session.createQuery("FROM Student", Student.class).getResultList();
       
       for (Student product : products) {
           System.out.println("Product ID: " + product.getId());
           System.out.println("Product Name: " + product.getName());
           System.out.println("Product Name: " + product.getCity());
           // ... Other attributes
       }
       transaction.commit();
       session.close();
       System.out.println("Done");
       
    }
}
