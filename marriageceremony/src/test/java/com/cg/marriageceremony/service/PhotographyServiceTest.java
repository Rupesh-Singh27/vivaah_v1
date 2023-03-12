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
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPhotography;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.exceptions.NoPhotographyPresentException;
import com.cg.marriageceremony.model.Photography;
import com.cg.marriageceremony.repository.PhotographyRepository;

@SpringBootTest
class PhotographyServiceTest {

	@Autowired
	PhotographyService photographyService;
	
	@MockBean
	PhotographyRepository photographyRepository;
	
//	stub
//	Test 1 & 3
	Photography sidharth;
	
//	Test2
	Photography glenn;
	Photography surya;
	List<Photography> photographyList;

//	Exception
	private Photography sanjay;

	@BeforeEach
	void setUp() throws Exception {
		
//		Test 1 & 3
		sidharth = new Photography("Sidharth", "Bowler", "sidharth's image url");
		
//		Test 2
		photographyList = new ArrayList<>();
		
		glenn = new Photography("Glenn", "All-Rounder", "Glenn's image url");
		surya = new Photography("Surya", "Batsmen", "Surya's image url");
		
		photographyList.add(sidharth);
		photographyList.add(glenn);
		photographyList.add(surya);
		
//		Exception
		sanjay = new Photography();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Saves the photographer object provided into the database")
	public void test_addMakeup_GivenTheMakeup_ShouldReturnSavedMakeup() {
		
		Mockito.when(photographyRepository.save(sidharth)).thenReturn(sidharth);
		
		Photography photographyReturnedFromPhotographyServiceAddMethod = photographyService.addPhotography(sidharth);
		
		assertNotNull(photographyReturnedFromPhotographyServiceAddMethod);
		assertEquals(sidharth, photographyReturnedFromPhotographyServiceAddMethod);
	}
	
	@Test
	@DisplayName("Return List of photographers")
	public void test_getAllMakeups_ShouldReturnListOfMakeups() {
		
		Mockito.when(photographyRepository.findAll()).thenReturn(photographyList);
		
		List<Photography> photographyListReturnedFromphotographyService = photographyService.getAllPhotography();
		
		assertNotNull(photographyList);
		assertNotNull(photographyListReturnedFromphotographyService);
		assertEquals(3, photographyListReturnedFromphotographyService.size());
		assertNotEquals(4, photographyListReturnedFromphotographyService.size());
		assertEquals(sidharth, photographyListReturnedFromphotographyService.get(0));
		assertEquals(glenn, photographyListReturnedFromphotographyService.get(1));
		assertEquals(surya, photographyListReturnedFromphotographyService.get(2));
	}
	
	@Test
	public void test_addCatarer_shouldThrowFieldCannotBeEmptyExceptionForPhotography() {
		
		Mockito.when(photographyRepository.save(glenn)).thenThrow(new FieldCannotBeEmptyExceptionForPhotography());
		
		assertThrows(FieldCannotBeEmptyExceptionForPhotography.class, () -> {
			
			photographyService.addPhotography(sanjay);
			
		});
	
	}
	
	
	@Test
	public void test_allCateres_shouldThrowNoPhotographyPresentException() {
		
		Mockito.when(photographyRepository.findAll()).thenThrow(NoPhotographyPresentException.class);
		
		assertThrows(NoPhotographyPresentException.class, () -> photographyService.getAllPhotography());
		
	}
	


}
