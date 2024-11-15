package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private List<Reservation> listReservations;

    @BeforeEach
    void setUp() {
        // Création d'une réservation pour les tests
        reservation = new Reservation();
        reservation.setIdReservation("123");
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);

        Reservation reservation2 = new Reservation();
        reservation2.setIdReservation("456");
        reservation2.setAnneeUniversitaire(new Date());
        reservation2.setEstValide(false);

        listReservations = Arrays.asList(reservation, reservation2);
    }

    @Test
    void testRetrieveAllReservations() {
        // Arrange
        when(reservationRepository.findAll()).thenReturn(listReservations);

        // Act
        List<Reservation> result = reservationService.retrieveAllReservations();

        // Assert
        assertEquals(2, result.size());
        assertEquals("123", result.get(0).getIdReservation());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservation() {
        // Arrange
        when(reservationRepository.findById("123")).thenReturn(Optional.of(reservation));

        // Act
        Reservation result = reservationService.retrieveReservation("123");

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
        verify(reservationRepository, times(1)).findById("123");
    }

    @Test
    void testAddReservation() {
        // Arrange
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation result = reservationService.addReservation(reservation);

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testModifyReservation() {
        // Arrange
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation result = reservationService.modifyReservation(reservation);

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testRemoveReservation() {
        // Arrange
        String reservationId = "123";
        doNothing().when(reservationRepository).deleteById(reservationId);

        // Act
        reservationService.removeReservation(reservationId);

        // Assert
        verify(reservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    void testFindReservationsByDateAndStatus() {
        // Arrange
        Date date = new Date();
        boolean status = true;
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, status)).thenReturn(listReservations);

        // Act
        List<Reservation> result = reservationService.trouverResSelonDateEtStatus(date, status);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(reservationRepository, times(1)).findAllByAnneeUniversitaireBeforeAndEstValide(date, status);
    }
}

