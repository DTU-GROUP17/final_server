package providers;

import org.glassfish.hk2.api.Factory;
import services.authentication.Guard;
import services.authentication.JwtGuard;

public class GuardProvider implements Factory<Guard> {
	@Override
	public Guard provide() {
		return new JwtGuard();
	}

	@Override
	public void dispose(Guard instance) {
	}
}
