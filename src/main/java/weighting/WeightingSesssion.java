package weighting;

import models.db.User;

import java.net.SocketTimeoutException;

public class WeightingSesssion {

	private WeightController controller;

	public WeightingSesssion(WeightController controller) {
		this.controller = controller;
	}

	private User getVerifiedUser() {
		return new User();
	}

	public void run() throws SocketTimeoutException {

	}
}
