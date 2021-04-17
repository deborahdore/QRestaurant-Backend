package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.ManagerMapper;
import com.certimetergroup.qrestaurant.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class ManagerRepository {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Pattern BCRYPT_PATTERN;
    @Autowired
    private ManagerMapper managerMapper;

    @Value("${bcrypt.password.encoder.pattern.regexp}")
    private String passwordPattern;

    @PostConstruct
    public void init() {
        BCRYPT_PATTERN = Pattern.compile(passwordPattern);
    }

    public Manager getManager(Integer idManager) {
        return managerMapper.getManager(idManager);
    }

    public int postManager(Manager manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return managerMapper.postManager(manager);
    }

    public int putManager(Manager manager) {
        if (!BCRYPT_PATTERN.matcher(manager.getPassword()).matches())
            manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return managerMapper.putManager(manager);
    }

    public int deleteManager(Manager manager) {
        return managerMapper.deleteManager(manager);
    }

    public int deleteManagerByID(Integer idManager) {
        return managerMapper.deleteManagerByID(idManager);
    }

    public Manager getManagerByPhone(String phone) {
        return managerMapper.getManagerByPhone(phone);
    }

    public Manager getManagerByPhoneAndPassword(String phone, String password) {
        Manager manager = managerMapper.getManagerByPhone(phone);
        return (manager != null && passwordEncoder.matches(password, manager.getPassword())) ? manager : null;
    }

    public List<Manager> getAllManagers() {
        return managerMapper.getAllManagers();
    }
}
