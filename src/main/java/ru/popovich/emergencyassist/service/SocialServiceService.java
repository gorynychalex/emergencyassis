package ru.popovich.emergencyassist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.repository.SocialServiceDao;

@Service
public class SocialServiceService {

    @Autowired
    SocialServiceDao socialServiceDao;

    public void save(SocialService socialService){
        socialServiceDao.save(socialService);
    }

}
