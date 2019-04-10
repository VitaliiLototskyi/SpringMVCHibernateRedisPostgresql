package model;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="developer")
@RedisHash("Developer")
public class Developer implements Serializable {
    @Id
    @Column(name="id")
    private String id;
    @Column (name="name")
    private String name;

    public Developer() {
    }

    public Developer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Developer> generateDevs(int count) {
        List<Developer> developers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Developer dev = new Developer();
            dev.setId(dev.idGen());
            dev.setName(dev.nameGen());
            developers.add(dev);
        }

        return developers;
    }

    private String nameGen() {
        List<String> names =  new ArrayList<>();
        names.add("Erik");
        names.add("Tom");
        names.add("David");
        names.add("Mike");
        names.add("John");
        names.add("Jacob");
        names.add("Freddy");
        names.add("Andrew");
        names.add("Sam");
        double tmp = Math.random()*8;

        return names.get((int)tmp);
    }

    public String idGen() {
        int tmp =  (int)(Math.random()*100000);
        String id = Integer.toString(tmp);

        return id;
    }
}
