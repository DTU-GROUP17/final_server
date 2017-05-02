package http.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginInfo {
	@Getter private String userName;
	@Getter private String password;
}
