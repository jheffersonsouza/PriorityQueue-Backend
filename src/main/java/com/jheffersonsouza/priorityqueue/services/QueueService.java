package com.jheffersonsouza.priorityqueue.services;

import com.jheffersonsouza.priorityqueue.dto.User;
import com.jheffersonsouza.priorityqueue.dto.UserPriority;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class QueueService {

    private final ArrayList<User> queue;
    private boolean active = false;

    public QueueService(ArrayList<User> queue) {
        this.queue = queue;
    }

    @Scheduled(fixedDelay = 30_000)
    public void processarFila() {
        if (!active) return;
        User u = removeFirst();
        if (u != null) {
            Logger.getAnonymousLogger().info(u.name() + " saiu da fila.");
        }
    }

    public synchronized void add(User user) {
        if (!active) active = true;
        if (user.priority() == UserPriority.NORMAL) {
            queue.add(user);
            return;
        }
        User fN = queue.stream()
                .filter(u -> u.priority() == UserPriority.NORMAL)
                .findFirst()
                .orElse(null);
        if (fN == null) {
            queue.add(user);
            return;
        }
        queue.add(queue.indexOf(fN), user);
    }

    public ArrayList<User> getQueue() {
        return queue;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized User getUser(int index) {
        return queue.get(index);
    }

    public synchronized User removeFirst() {
        if (queue.isEmpty()) {
            active = false;
            return null;
        }
        return queue.removeFirst();
    }
}
