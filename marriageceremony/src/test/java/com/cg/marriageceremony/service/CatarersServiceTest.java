package com.cg.marriageceremony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyException;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.model.Caterer;
import com.cg.marriageceremony.repository.CatererRepository;

@SpringBootTest
class CatarersServiceTest {
	
	@MockBean
	CatererRepository catarerRepository;
	
	@Autowired
	CatererService catarerService;
	
//	stub
//	Test 1 & 3
	private Caterer sanjeev;

//	Test 2
	private List<Caterer> catarersList;
	private Caterer ved;
	private Caterer vishal;
	
//	Exception
	private Caterer kapil;

	@BeforeEach
	void setUp() throws Exception {
//		Test 1
		sanjeev = new Caterer("sanjeev", "some random description", "image url");
		
//		Test 2
		catarersList = new ArrayList<>();
		
		ved = new Caterer("Ved Vishwakarma", "Masterchef", "some image url");
		vishal = new Caterer("Vishal Dayama", "Dhaba", "another image url");
		
		catarersList.add(sanjeev);
		catarersList.add(ved);
		catarersList.add(vishal);
		
//		Exception
		kapil = new Caterer();
	}

	@AfterEach
	void tearDown() throws Exception {
		sanjeev = ved = vishal = kapil = null;
		catarersList = null;
	}
	
	@Test
	@DisplayName("Saves the catarer object provided into the database")
	public void test_addCatarer_GivenTheCatarer_ShouldReturnSavedCatarer() {
		
		Mockito.when(catarerRepository.save(sanjeev)).thenReturn(sanjeev);
		
		Caterer venueReturnedFromCatarerServiceAddMethod = catarerService.addCaterer(sanjeev);
		
		assertNotNull(venueReturnedFromCatarerServiceAddMethod);
		assertEquals(sanjeev, venueReturnedFromCatarerServiceAddMethod);
		assertEquals(sanjeev.getCatererName(), venueReturnedFromCatarerServiceAddMethod.getCatererName());
		assertEquals(sanjeev.getCatererDescription(), venueReturnedFromCatarerServiceAddMethod.getCatererDescription());
	}
	
	
	@Test
	@DisplayName("Return List of Catarers")
	public void test_findAllCaterers_ShouldReturnListOfCatarers() {
		
		Mockito.when(catarerRepository.findAll()).thenReturn(catarersList);
		
		List<Caterer> catarersListReturnedFromCatarerService = catarerService.getAllCaterers();
		
		assertNotNull(catarersList);
		assertNotNull(catarersListReturnedFromCatarerService);
		assertEquals(3, catarersListReturnedFromCatarerService.size());
		assertNotEquals(4, catarersListReturnedFromCatarerService.size());
		assertEquals(sanjeev, catarersListReturnedFromCatarerService.get(0));
		assertEquals(ved, catarersListReturnedFromCatarerService.get(1));
		assertEquals(vishal, catarersListReturnedFromCatarerService.get(2));
	}
	
	@Test
	public void test_addCatarer_shouldThrowFieldCannotBeEmptyExceptionForCaterer() {
		
		Mockito.when(catarerRepository.save(ved)).thenThrow(new FieldCannotBeEmptyException());
				
		assertThrows(FieldCannotBeEmptyException.class, () -> {
			
			catarerService.addCaterer(kapil);
			
		});
	
	}
	
	
	@Test
	public void test_allCateres_shouldThrowNoCatererPresentException() {
		
		Mockito.when(catarerRepository.findAll()).thenThrow(NoCatererPresentException.class);
		
		assertThrows(NoCatererPresentException.class, () -> catarerService.getAllCaterers());
		
	}
	
	
//	@Test
//	public void test_deleteVendor_shouldThrowCatererDoesNotExistException() {
//		
//		Mockito.when(catarerRepository.deleteVendor(1)).thenThrow(new CatererDoesNotExistException());
//		
//		assertThrows(CatererDoesNotExistException.class, () -> catarerService.deleteVendor(1));
//		
//	}

}
