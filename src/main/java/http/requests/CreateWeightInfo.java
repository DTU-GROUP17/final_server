package http.requests;

import java.util.Set;

public class CreateWeightInfo {

    private String name;
    private String uri;
	public boolean isFull(){
    return this.getName()!=null && this.getUri()!=null;
        }
    public String getName() {
        return this.name;
    }

    public String getUri() {
        return this.uri;
    }

        }
