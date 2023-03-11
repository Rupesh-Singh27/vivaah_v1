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
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForMakeup;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.exceptions.NoMakeupPresentException;
import com.cg.marriageceremony.model.MakeUp;
import com.cg.marriageceremony.repository.MakeUpRepository;

@SpringBootTest
class MakeupServiceTest {
	
	@MockBean
	MakeUpRepository makeupRepository;
	
	@Autowired
	MakeUpService makeupService;
	
//	stub
//	Test 1 & 3
	MakeUp prithvi;
	
//	Test2
	MakeUp ishant;
	MakeUp bharat;
	List<MakeUp> makeupList;
	
//	Exception
	MakeUp hussain;

	@BeforeEach
	void setUp() throws Exception {
		
//		Test 1
		prithvi = new MakeUp("Prithvi", "Opener", "image url");
		
//		Test 2
		makeupList = new ArrayList<>();
		
		ishant = new MakeUp("Ishant", "Bowler", "ishant's image url");
		bharat = new MakeUp("Bharat", "Wicket-Keeper", "Bharat's image url");
		
		makeupList.add(prithvi);
		makeupList.add(ishant);
		makeupList.add(bharat);
		
//		Exception
		hussain = new MakeUp();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Saves the makeup object provided into the database")
	public void test_addMakeup_GivenTheMakeup_ShouldReturnSavedMakeup() {
		
		Mockito.when(makeupRepository.save(prithvi)).thenReturn(prithvi);
		
		MakeUp makeupReturnedFromMakeupServiceAddMethod = makeupService.addMakeUp(prithvi);
		
		assertNotNull(makeupReturnedFromMakeupServiceAddMethod);
		assertEquals(prithvi, makeupReturnedFromMakeupServiceAddMethod);
	}
	
	@Test
	@DisplayName("Return List of makeups")
	public void test_getAllMakeups_ShouldReturnListOfMakeups() {
		
		Mockito.when(makeupRepository.findAll()).thenReturn(makeupList);
		
		List<MakeUp> makeupListReturnedFromMakeupService = makeupService.allMakeUp();
		
		assertNotNull(makeupList);
		assertNotNull(makeupListReturnedFromMakeupService);
		assertEquals(3, makeupListReturnedFromMakeupService.size());
		assertNotEquals(4, makeupListReturnedFromMakeupService.size());
		assertEquals(prithvi, makeupListReturnedFromMakeupService.get(0));
		assertEquals(ishant, makeupListReturnedFromMakeupService.get(1));
		assertEquals(bharat, makeupListReturnedFromMakeupService.get(2));
	}
	
	@Test
	public void test_addCatarer_shouldThrowFieldCannotBeEmptyExceptionForMakeup() {
		
		Mockito.when(makeupRepository.save(prithvi)).thenThrow(new FieldCannotBeEmptyExceptionForMakeup());
		
		assertThrows(FieldCannotBeEmptyExceptionForMakeup.class, () -> makeupService.addMakeUp(prithvi));
	   
	}
	
	@Test
	public void test_allCateres_shouldThrowNoMakeupPresentException() {
		
		Mockito.when(makeupRepository.findAll()).thenThrow(NoMakeupPresentException.class);
		
		assertThrows(NoMakeupPresentException.class, () -> makeupService.allMakeUp());
		
	}

}
