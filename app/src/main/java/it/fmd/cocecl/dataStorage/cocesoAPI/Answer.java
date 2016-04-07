package it.fmd.cocecl.dataStorage.cocesoAPI;

import java.io.Serializable;

public abstract class Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }
}
