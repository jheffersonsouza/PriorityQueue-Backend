package com.jheffersonsouza.priorityqueue.dto;

public record User(String name, UserPriority priority) {
    @Override
    public String toString() {
        return name + ":" + priority;
    }
}
