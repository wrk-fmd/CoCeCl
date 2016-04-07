package it.fmd.cocecl.dataStorage.cocesoAPI.loginAPI;

import java.io.Serializable;

import it.fmd.cocecl.dataStorage.cocesoAPI.Answer;

public class LoginAnswer extends Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer session;

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }
}
