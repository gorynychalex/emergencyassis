package ru.popovich.emergencyassist.dbtest;

import ru.popovich.emergencyassist.model.SocialService;

import java.util.ArrayList;
import java.util.List;

public final class SocialServiceGenerator {
    private static SocialServiceGenerator instance = new SocialServiceGenerator();

    private final List<SocialService> socialServices;


    public SocialServiceGenerator() {

        socialServices = new ArrayList<SocialService>() {{
                add(new SocialService("1", "Покупка продуктов", 143.50F));
                add(new SocialService("2", "Приготовление пищи", 143.50F));
                add(new SocialService("3", "Оплата ЖКХ", 71.77F));
                add(new SocialService("4", "Уборка", 287.09F));
        }};
    }

    public static SocialServiceGenerator getInstance() { return instance; }

    public List<SocialService> getSocialServices() { return socialServices; }
}
