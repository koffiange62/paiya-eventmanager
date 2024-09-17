package events.paiya.eventmanager.resources;

import events.paiya.eventmanager.domains.CashAccount;
import events.paiya.eventmanager.domains.EventOrganizer;
import events.paiya.eventmanager.domains.OnlineAddress;
import events.paiya.eventmanager.domains.PhysicalAddress;
import events.paiya.eventmanager.domains.Scheduling;
import events.paiya.eventmanager.domains.Ticket;
import events.paiya.eventmanager.enumeration.EventType;
import events.paiya.eventmanager.enumeration.Langages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EventResource{
    private String id;
    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Must contain no more than 100 caracters")
    private String title;
    @Builder.Default
    private EventType eventType = EventType.SINGLE_EVENT;
    @Size(max = 30, message = "Must contain no more than 50 caracters")
    private String eventCategory;
    @Builder.Default
    private List<String> tags = new ArrayList<>();
    private String imageCover;
    @Size(max = 150, message = "Must contain no more than 100 caracters")
    private String summary;
    @Size(max = 3000, message = "Must contain no more than 100 caracters")
    private String description;
    private LocalDateTime publicationDate;
    @Builder.Default
    private Boolean visibility = false;
    
    private LocalDate date;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private Scheduling scheduling;
    private String timeZone;
    
    private PhysicalAddress physicalAdresse;
    private OnlineAddress onlineAdresse;
    private Langages eventPageLanguage;
    private EventOrganizer eventOrganizer;
    @Builder.Default
    private List<Ticket> tickets = new ArrayList<>();
    @Builder.Default
    private List<CashAccount> cashAccounts = new ArrayList<>();

    // Audit properties
    private String owner;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Integer version;
}
