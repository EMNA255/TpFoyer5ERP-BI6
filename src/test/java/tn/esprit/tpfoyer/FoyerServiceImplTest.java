package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer1;
    private Foyer foyer2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        foyer1 = new Foyer(); // Set properties as needed
        foyer2 = new Foyer(); // Set properties as needed
    }

    @Test
    void retrieveAllFoyers() {
        when(foyerRepository.findAll()).thenReturn(Arrays.asList(foyer1, foyer2));

        List<Foyer> foyers = foyerService.retrieveAllFoyers();
        assertNotNull(foyers);
        assertEquals(2, foyers.size());
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void retrieveFoyer() {
        Long id = 1L;
        when(foyerRepository.findById(id)).thenReturn(Optional.of(foyer1));

        Foyer result = foyerService.retrieveFoyer(id);
        assertNotNull(result);
        assertEquals(foyer1, result);
        verify(foyerRepository, times(1)).findById(id);
    }

    @Test
    void addFoyer() {
        when(foyerRepository.save(foyer1)).thenReturn(foyer1);

        Foyer result = foyerService.addFoyer(foyer1);
        assertNotNull(result);
        assertEquals(foyer1, result);
        verify(foyerRepository, times(1)).save(foyer1);
    }

    @Test
    void modifyFoyer() {
        when(foyerRepository.save(foyer1)).thenReturn(foyer1);

        Foyer result = foyerService.modifyFoyer(foyer1);
        assertNotNull(result);
        assertEquals(foyer1, result);
        verify(foyerRepository, times(1)).save(foyer1);
    }

    @Test
    void removeFoyer() {
        Long id = 1L;
        doNothing().when(foyerRepository).deleteById(id);

        foyerService.removeFoyer(id);
        verify(foyerRepository, times(1)).deleteById(id);
    }
}
