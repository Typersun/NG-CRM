package iluha.containers;

import com.fasterxml.jackson.databind.ObjectMapper;
import iluha.repository.impl.DeviceRepositoryImpl;
import iluha.repository.impl.ProgrammerRepositoryImpl;
import iluha.services.impl.DeviceServiceImpl;
import iluha.services.impl.ProgrammerServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class DIContainer {
    public static DIContainer instance = new DIContainer();
    private final Map<String, Object> dependencies = new HashMap<>();

    private DIContainer() {
        addDependency("ProgrammerRepository", new ProgrammerRepositoryImpl());
        addDependency("ObjectMapper", new ObjectMapper());
        addDependency("ProgrammerService", new ProgrammerServiceImpl(new ProgrammerRepositoryImpl(), new DeviceRepositoryImpl()));
        addDependency("DeviceService", new DeviceServiceImpl(new ProgrammerRepositoryImpl(), new DeviceRepositoryImpl()));
    }

    public void addDependency(String key, Object dependency) {
        dependencies.put(key, dependency);
    }

    public Object getDependency(String key) {
        return dependencies.get(key);
    }


}
