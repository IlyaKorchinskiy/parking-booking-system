package ru.korchinskiy.parking.booking.infrastructure.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parkings")
public class ParkingEntity {

    @Id
    private UUID id;

    private String address;

    @Column(name = "coordinate_x")
    private BigDecimal coordinateX;

    @Column(name = "coordinate_y")
    private BigDecimal coordinateY;

    @Column(name = "total_places")
    private Integer totalPlaces;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_id")
    private Set<SlotEntity> slots;
}
