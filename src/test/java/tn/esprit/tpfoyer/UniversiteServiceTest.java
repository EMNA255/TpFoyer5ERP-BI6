package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceImplTest {

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @Mock
    UniversiteRepository universiteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Données de test
        Universite universite1 = new Universite(1L, "Université A", "Adresse A", null);
        Universite universite2 = new Universite(2L, "Université B", "Adresse B", null);
        List<Universite> universites = Arrays.asList(universite1, universite2);

        // Mocking
        when(universiteRepository.findAll()).thenReturn(universites);

        // Appel de la méthode à tester
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Vérifications
        assertEquals(2, result.size());
        assertEquals("Université A", result.get(0).getNomUniversite());
        assertEquals("Université B", result.get(1).getNomUniversite());

        // Vérification de l'interaction avec le mock
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveUniversite() {
        // Données de test
        Universite universite = new Universite(1L, "Université A", "Adresse A", null);

        // Mocking
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        // Appel de la méthode à tester
        Universite result = universiteService.retrieveUniversite(1L);

        // Vérifications
        assertNotNull(result);
        assertEquals("Université A", result.getNomUniversite());

        // Vérification de l'interaction avec le mock
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testAddUniversite() {
        // Données de test
        Universite universite = new Universite(1L, "Université A", "Adresse A", null);

        // Mocking
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appel de la méthode à tester
        Universite result = universiteService.addUniversite(universite);

        // Vérifications
        assertNotNull(result);
        assertEquals("Université A", result.getNomUniversite());

        // Vérification de l'interaction avec le mock
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testModifyUniversite() {
        // Données de test
        Universite universite = new Universite(1L, "Université A", "Adresse A", null);

        // Mocking
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appel de la méthode à tester
        Universite result = universiteService.modifyUniversite(universite);

        // Vérifications
        assertNotNull(result);
        assertEquals("Université A", result.getNomUniversite());

        // Vérification de l'interaction avec le mock
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRemoveUniversite() {
        // Appel de la méthode à tester
        universiteService.removeUniversite(1L);

        // Vérification de l'interaction avec le mock
        verify(universiteRepository, times(1)).deleteById(1L);
    }
}
