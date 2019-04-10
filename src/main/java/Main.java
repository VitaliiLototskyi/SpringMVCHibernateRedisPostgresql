import dao.DeveloperDao;
import model.Developer;
import redis.clients.jedis.Jedis;
import services.DeveloperService;

public class Main {
    public static void main(String[] args) {

        DeveloperService developerService =  new DeveloperService();
        Jedis jedis =  new Jedis();
        System.out.println(jedis.info());
        Developer developer = new Developer("231", "dev");

        DeveloperDao developerDao = new DeveloperDao();
        developerDao.saveToRedis(developer);
    }
}
