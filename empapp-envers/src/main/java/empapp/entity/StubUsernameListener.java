package empapp.entity;

import org.hibernate.envers.RevisionListener;

import java.util.UUID;

public class StubUsernameListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        if (o instanceof EmployeeRevisionEntity revision) {
            revision.setUsername("admin"); // Spring Security
            revision.setUuid(UUID.randomUUID().toString());
        }
    }
}
