package com.learning.utils;

import com.learning.models.Timers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TimersWrapper {
    public ArrayList<Timers> getTimers() {
        return timers;
    }

    public void setTimers(ArrayList<Timers> timers) {
        this.timers = timers;
    }

    private ArrayList<Timers> timers;
}
