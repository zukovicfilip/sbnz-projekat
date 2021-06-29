package sbnz.integracija.example.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
public class DetailsEvent implements Serializable {

    private Date executionTime;
    private UUID propertyId;
    private UUID userId;

    public DetailsEvent(Date executionTime, UUID propertyId, UUID userId) {
        this.executionTime = executionTime;
        this.propertyId = propertyId;
        this.userId = userId;
    }

    public DetailsEvent() {
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
