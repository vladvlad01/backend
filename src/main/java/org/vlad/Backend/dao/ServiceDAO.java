package org.vlad.Backend.dao;

import javax.persistence.*;
import java.util.List;

    public class ServiceDAO  {
    	
        protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

        public Object save(Object object){
        	
            EntityManager entityManager = entityManagerFactory.createEntityManager();
           
            entityManager.getTransaction().begin();

           try {
        	   if(!entityManager.contains(object))  {  	
                   entityManager.persist(object);                   
        	   }
               else
                   entityManager.merge(object);

               entityManager.getTransaction().commit();
               return object;
               
           }catch(Exception e) {
        	   return null;
           }  finally {
        	   entityManager.close();
           }
        }
        
        public Object update(Object objectToUpdate){

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            try {
            	if(entityManager.contains(objectToUpdate)) 
            		entityManager.merge(objectToUpdate);       	

            	entityManager.getTransaction().commit();
            	return objectToUpdate;
            }catch(Exception e) {
            	return null;
            }finally {        
            entityManager.close();
            }
        }

        // Parameter length must divide by 2 to be valid

		public Object getOne(String query, String key, String value){
        	
 
        	 EntityManager entityManager = entityManagerFactory.createEntityManager();
             try {
             Object object = entityManager.createNamedQuery(query).setParameter(key, value).getSingleResult();
             
             return object;
             
             }catch(NoResultException nre) {
            	 return null;
             }finally{
            	 entityManager.close();
             }
        }

        public Object findById(String query, String param1, int param2){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
            	Object object = entityManager.createNamedQuery(query).setParameter(param1, param2).getSingleResult();
            	return object;
            	}
            catch(Exception e) {
            	e.printStackTrace();
            	return null;
            }
        }

        @SuppressWarnings("unchecked")
		public List<Object> getAll(String query){

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            try {
            List<Object> objects = (List<Object>) entityManager.createNamedQuery(query).getResultList();
            return objects;
            }catch(Exception e) {
            	e.printStackTrace();
            	return null;
            }finally {
            	entityManager.close();  
            }
        }



        public String delete(Object object){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            try {
            	entityManager.remove(entityManager.merge(object));
            	entityManager.getTransaction().commit();
            	return "Deleted!";
            }catch(Exception e) {
            	e.printStackTrace();
            	return "NOT FOUND!";
            }finally {
            	entityManager.close();
            }
        }

        public void deleteAll(String query){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.createNamedQuery(query).executeUpdate();

            System.out.println(query.split("\\.")[0] +" Info Deleted");

            entityManager.getTransaction().commit();
            entityManager.close();
        }

        public void deleteAllTables(){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Query q = entityManager.createNativeQuery("DROP TABLE track, playlist, passenger, user_playlist, playlist_track, user");
            q.executeUpdate();

            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }


