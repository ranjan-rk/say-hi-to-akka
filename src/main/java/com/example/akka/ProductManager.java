package com.example.akka;

import static akka.actor.Actors.actorOf;

import java.util.concurrent.CountDownLatch;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class ProductManager {
    public static void main(String[] args) throws Exception {
	int noOfDevelopers = 10;
	int noOfTasksPerDeveloper = 10;
	ProductManager productManager = new ProductManager();
	productManager.transfterRquirements(noOfDevelopers, noOfTasksPerDeveloper);
    }

    public void transfterRquirements(final int noOfDevelopers, final int noOfTasksPerDeveloper) throws Exception {
	final CountDownLatch countDownLatch = new CountDownLatch(1);
	ActorRef developmentLead = actorOf(new UntypedActorFactory() {
	    public UntypedActor create() {
		return new DevelopmentLead(noOfDevelopers, noOfTasksPerDeveloper, countDownLatch);
	    }
	}).start();
	developmentLead.tell(new Requirements());
	countDownLatch.await();
    }
}
