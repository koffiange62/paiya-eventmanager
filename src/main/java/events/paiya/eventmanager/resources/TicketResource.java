package events.paiya.eventmanager.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TicketResource {
    private String id;
    private String code;
    private String name;
    private Integer quantity;
    private Double price;
    private Boolean isTransactionFeesSupported;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDateOfSales;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDateOfSales;
    private Integer minimumTicketQuantityPerOrder;
    private Integer maximumTicketQuantityPerOrder;
    private String description;
    private String eventId;

    // Audit properties
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Integer version;
}
