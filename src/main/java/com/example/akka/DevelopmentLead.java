package com.example.akka;

import static akka.actor.Actors.actorOf;

import java.util.concurrent.CountDownLatch;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class DevelopmentLead extends UntypedActor {
    private int noOfDevelopers;
    private int noOfTasksPerDeveloper;
    private final CountDownLatch latch;
    private int developedComponentsCount = 0;

    ActorRef[] developers = null;

    public DevelopmentLead(int noOfDevelopers, int noOfTasksPerDeveloper, CountDownLatch latch) {
	this.noOfDevelopers = noOfDevelopers;
	this.noOfTasksPerDeveloper = noOfTasksPerDeveloper;
	this.latch = latch;

	developers = new ActorRef[noOfDevelopers];
	for (int i = 0; i < noOfDevelopers; i++) {
	    developers[i] = actorOf(Developer.class).start();
	}
    }

    public void onReceive(Object message) {
	if (message instanceof Requirements) {
	    int developerId = 0;
	    for (int i = 0; i < noOfDevelopers; i++) {
		developerId = i + 1;
		developers[i].tell(new SoftwareDevelopment(developerId, noOfTasksPerDeveloper), getContext());
	    }
	} else if (message instanceof Software) {
	    Software developedSoftware = (Software) message;
	    System.out.println("Developed Software : " + developedSoftware.getDevelopedComponentId());
	    developedComponentsCount++;
	    if (developedComponentsCount == noOfDevelopers) {
		getContext().stop();
	    }
	}
    }

    @Override
    public void preStart() {

    }

    @Override
    public void postStop() {
	System.out.println("Software Development Done!!!");
	latch.countDown();
    }
}
