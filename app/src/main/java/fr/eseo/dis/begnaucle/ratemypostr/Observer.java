package fr.eseo.dis.begnaucle.ratemypostr;

import fr.eseo.dis.begnaucle.ratemypostr.service.ServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.ServiceResult;

/**
 * Created by cleme on 04/01/2018.
 */

public interface Observer {
    public Subject addSubject(Subject subject);

    public Subject removeSubject(Subject subject);

    public void react(Object obj);

    public void receiveResult(ServiceResult sr);
}
