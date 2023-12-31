package events.paiya.eventmanager.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PhysicalAdresse extends Adresse{
    private String location;
    private String town;
    private String postalCode;
    private String country;
    private String state;
    private String longitude;
    private String latitude;
}