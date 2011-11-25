package com.example.akka;

public class SoftwareDevelopment {
    private int developerId;
    private int noOfTasksPerDeveloper;

    public SoftwareDevelopment(int developerId, int noOfTasksPerDeveloper) {
	this.developerId = developerId;
	this.noOfTasksPerDeveloper = noOfTasksPerDeveloper;
    }

    public int getNoOfTasksPerDeveloper() {
	return noOfTasksPerDeveloper;
    }

    public int getDeveloperId() {
	return developerId;
    }
}
