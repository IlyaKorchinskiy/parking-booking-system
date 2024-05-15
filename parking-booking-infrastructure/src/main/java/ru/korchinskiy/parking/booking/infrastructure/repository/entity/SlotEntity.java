package ru.korchinskiy.parking.booking.infrastructure.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "slots")
@IdClass(SlotEntityId.class)
public class SlotEntity {

    @Id
    @Column(name = "start_date_time")
    private OffsetDateTime startDateTime;

    @Id
    @Column(name = "parking_id")
    private UUID parkingId;

    private Integer available;
}
