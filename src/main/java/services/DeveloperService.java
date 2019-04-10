package services;

import dao.DeveloperDao;
import model.Developer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeveloperService {
    private DeveloperDao developerDao = new DeveloperDao();

    public DeveloperService() {
    }

    public Developer findDeveloper(int id) {
        return developerDao.findById(id);
    }

    public void saveDeveloper(Developer developer) {
        developerDao.save(developer);
    }

    public List<Developer> findAllDevelopers() {
        return developerDao.findAll();
    }

    public void saveDeveloperToRedis(Developer developer) {
        developerDao.saveToRedis(developer);
    }

    public Developer findDeveloperRedis(String id) {
       return developerDao.getDeveloperFromRedis(id);
    }

    public Map<String, String> getAllFromRedis(String key) {
        return developerDao.getAllFromRedis(key);
    }
}
