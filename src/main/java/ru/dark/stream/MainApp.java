//package ru.dark.stream;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;
//import ru.one.learning.hiber.models.Product;
//
//import java.util.List;
//import javax.xml.validation.Validator;
//
//public class MainApp {
//
//    public static void main(String[] args) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        List products = null;
//        try {
//            session.beginTransaction();
//
////SQL======
////            NativeQuery query = session.createNativeQuery("SELECT * FROM product;");
////            query.addEntity(Product.class);
////            products = query.list();
////=========
////            NativeQuery query = session.createNativeQuery("Update product set description = 'Молоко 2.5%' where id = '1';");
////            query.executeUpdate();
////=========
////            NativeQuery query = session.createNativeQuery("Update product set description = ? where id = ?");
////            query.setParameter(1, "Домик в Деревне2");
////            query.setParameter(2, 1);
////            query.executeUpdate();
////HQL======
////            Query hqlquery = session.createQuery("FROM Product");
////            products = hqlquery.list();
////
////Criteria=
//                Criteria criteria = session.createCriteria(Product.class);
//                products = criteria.list();
//                criteria.add(Restrictions.between("price", 10 , 50));
////
////Добавление данных в таблицу
////            Product product = new Product();
////            product.setTitle("Ряженка");
////            product.setDescription("Свежая");
////            product.setPrice(new BigDecimal("88.00"));
////            session.save(product);
//            session.getTransaction().commit();
////Валидация
//
//
//        } catch (Exception e){
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//            sessionFactory.close();
//        }
//
//        sessionFactory.close();
//        if (products!=null)products.forEach(x -> System.out.println( x));
////        for (Iterator iterator = products.iterator(); iterator.hasNext();) {
////           Product product =  (Product) iterator.next();
////            System.out.println(product.toString());
////        }
//    }
//}
