package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {

    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List <UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(19, 0), 2000);
        for (UserMealWithExceed meal: list) {
            System.out.println(meal);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList,
                                                                   LocalTime startTime, LocalTime endTime,
                                                                   int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> outList = new ArrayList<>();
        Map<LocalDate, List<UserMeal>> map = new HashMap<>();
        Iterator<UserMeal> iterator = mealList.iterator();
        while (iterator.hasNext()){
            UserMeal meal = iterator.next();
            LocalDate date = meal.getDateTime().toLocalDate();
            List<UserMeal> list = map.get(date);
            if (list == null){
                list = new ArrayList<>();
                map.put(date, list);
            }
            map.get(date).add(meal);
        }
        for (LocalDate date:map.keySet()) {
            int dayCalories = 0;
            for (UserMeal meal:map.get(date)) {
                dayCalories += meal.getCalories();
            }
            boolean isExceed = dayCalories > caloriesPerDay;
            for (UserMeal meal:map.get(date)) {
                if (meal.getDateTime().toLocalTime().isAfter(startTime) &&
                        meal.getDateTime().toLocalTime().isBefore(endTime))
                outList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(),
                        meal.getCalories(), isExceed));
            }
        }
        return outList;
    }

}
