package weighting;

import models.db.User;

public class WeightingSession {

	private WeightController controller;

	public WeightingSession(WeightController controller) {
		this.controller = controller;
	}

	private User getVerifiedUser() {
		return new User();
	}

	public void run() {
		this.controller.reset();
//		this.controller.sendMessage("get rekked");
		System.out.println("running session");
		System.out.println("Response: "+this.controller.prompt("type", "0"));
	}
}
