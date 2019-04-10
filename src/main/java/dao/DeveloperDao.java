package dao;


import model.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import utils.HibernateSessionFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeveloperDao {

    public Developer findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Developer.class, id);
    }

    public void save(Developer developer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.save(developer);
        tr.commit();
        session.close();
    }

    public void update(Developer developer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(developer);
        tx1.commit();
        session.close();
    }

    public List<Developer> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Developer> developers = (List<Developer>) session.createQuery("FROM Developer").list();
        return developers;
    }

    public void saveToRedis(Developer developer) {
        Jedis jedis = new Jedis();
        final Map<String, String> properties = new HashMap<String, String>();

        properties.put("id", developer.getId());
        properties.put("name", developer.getName());

        jedis.hmset("developer:" + developer.getId(), properties);

    }

    public Developer getDeveloperFromRedis(String id) {
        Jedis jedis = new Jedis();
        Map<String, String> properties = jedis.hgetAll("user:" + id);
        Developer developer = new Developer();
        developer.setId(id);
        developer.setName(properties.get("name"));

        return developer;
    }

    public Map<String, String> getAllFromRedis(String key) {
        Jedis jedis = new Jedis();
        Map<String, String> value = jedis.hgetAll(key);
        jedis.close();

        return value;
    }

}
