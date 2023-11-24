package org.example;

import jakarta.persistence.*;
import org.example.entities.DistinctStudent;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none"); // create ,update, none

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            String sql = """
//            select * from Student
//            """;
//
//          Query q =  em.createNativeQuery(sql, Student.class);
//          q.getResultList().forEach(System.out::println);
//
//            String sql = " select s from DistinctStudent s";
//         TypedQuery<DistinctStudent> q=   em.createQuery(sql, DistinctStudent.class);
//            q.getResultList().forEach(System.out::println);


   StoredProcedureQuery q =  em.createStoredProcedureQuery("getstudents", Student.class)
                     .registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
             .setParameter("id", 2);

               q.getResultList().forEach(System.out::println);



            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
/* database level :
1)
USE your_database_name;
    USE your_database_name;

    CREATE VIEW view_unique_students AS
    SELECT DISTINCT * FROM student;

2)
DELIMITER //

CREATE PROCEDURE getstudents(IN student_id INT)
BEGIN
    SELECT * FROM student s WHERE s.id = student_id;
END //

DELIMITER ;

CALL getstudents(1); -- Replace 1 with the desired student ID


 */
