package com.example.akka;

import akka.actor.UntypedActor;

public class Developer extends UntypedActor {

    public void onReceive(Object message) {
	if (message instanceof SoftwareDevelopment) {
	    SoftwareDevelopment softwareDevelopment = (SoftwareDevelopment) message;
	    String developedComponentId = doDevelopment(softwareDevelopment);
	    getContext().reply(new Software(developedComponentId));
	}
    }

    private String doDevelopment(SoftwareDevelopment softwareDevelopment) {
	int noOfTasks = softwareDevelopment.getNoOfTasksPerDeveloper();
	int developerId = softwareDevelopment.getDeveloperId();
	String developedComponentId = "dev_" + developerId;
	for (int i = 1; i <= noOfTasks; i++) {
	    System.out.println("Developer : " + developerId + "is working on task" + i);
	    developedComponentId = developedComponentId + "_" + i;
	}
	return developedComponentId;
    }

}
