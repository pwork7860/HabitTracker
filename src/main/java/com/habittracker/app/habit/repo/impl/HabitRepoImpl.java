package com.habittracker.app.habit.repo.impl;

import com.habittracker.app.habit.data.models.Habit;
import com.habittracker.app.habit.repo.iface.HabitRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HabitRepoImpl implements HabitRepo {

    List<Habit> habits = new ArrayList<>();

    @Override
    public boolean createhabitRequest(Habit habit) {
        return habits.add(habit);
    }

    @Override
    public List<Habit> getHabits() {
        return habits;
    }

    @Override
    public Habit getHabitById(String id) {
        for (Habit item : habits) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;

    }

    @Override
    public boolean updateHabit(Habit habit) {
        for (Habit item : habits) {
            if (item.getId().equals(habit.getId())) {
                item.setName(habit.getName());
                item.setFrequency(habit.getFrequency());
            }
        }
        return true;
    }

    @Override
    public boolean deleteHabit(String id) {
        Iterator<Habit> iterator = habits.iterator();

        while (iterator.hasNext()) {
            Habit item = iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
    return false;
    }
}
