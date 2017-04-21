package services.authentication;

import org.glassfish.hk2.api.Factory;

public class GuardHandlerProvider implements Factory<Guard> {
	@Override
	public Guard provide() {
		return new JwtGuard();
	}

	@Override
	public void dispose(Guard instance) {
	}
}
