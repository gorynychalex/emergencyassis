package ru.popovich.emergencyassist.dbtest;

import org.springframework.beans.factory.annotation.Autowired;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.repository.SocialServiceDao;

import java.util.ArrayList;
import java.util.List;

public final class SocialServiceGenerator {

    @Autowired
    private SocialServiceDao socialServiceDao;

    private static SocialServiceGenerator instance = new SocialServiceGenerator();

    private final List<SocialService> socialServices;


    public SocialServiceGenerator() {

        socialServices = new ArrayList<SocialService>() {{
                add(new SocialService(1L, "Покупка продуктов", 143.50F));
                add(new SocialService(2L, "Приготовление пищи", 143.50F));
                add(new SocialService(3L, "Оплата ЖКХ", 71.77F));
                add(new SocialService(4L, "Уборка", 287.09F));
        }};

//        socialServiceDao.save(new SocialService("Покупка продуктов", 143.5F));
//        socialServiceDao.save(new SocialService("Оплата ЖКХ", 71.77F));
//        socialServices.forEach(socialServiceDao::save);
    }

    public static SocialServiceGenerator getInstance() { return instance; }

    public List<SocialService> getSocialServices() { return socialServices; }
}
