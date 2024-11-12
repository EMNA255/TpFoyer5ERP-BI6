package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.DAO.Entities.Universite;
import tn.esprit.tpfoyer.DAO.Repositories.UniversiteRepository;
import tn.esprit.tpfoyer.Services.Universite.UniversiteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrUpdate() {
        Universite universite = Universite.builder()
                .nomUniversite("Université de Test")
                .adresse("123 Rue de l'Exemple")
                .build();

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.addOrUpdate(universite);

        assertNotNull(result);
        assertEquals("Université de Test", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testDeleteById() {
        doNothing().when(universiteRepository).deleteById(1L);

        universiteService.deleteById(1L);

        verify(universiteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        Universite universite = new Universite();

        doNothing().when(universiteRepository).delete(any(Universite.class));

        universiteService.delete(universite);

        verify(universiteRepository, times(1)).delete(universite);
    }
}
