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
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForVenue;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.exceptions.NoVenuePresentException;
import com.cg.marriageceremony.model.Venue;
import com.cg.marriageceremony.repository.VenueRepository;

@SpringBootTest
class VenueServiceTest {
	
	@MockBean
	VenueRepository venueRepository;
	
	@Autowired
	VenueService venueService;
	
//	Stub
	
//	Test 1 & 3
	private Venue melbourne;
	
//	Test 2
	private List<Venue> venueList;
	private Venue oval;
	private Venue lords;
	
//	Exception
	private Venue sedney;

	@BeforeEach
	void setUp() throws Exception {
//		Test 1 & 3
		melbourne = new Venue("Melbourne", "Beautiful Stadium", "Melbourne's image url");
		
//		Test 2
		venueList = new ArrayList<>();
		
		oval = new Venue("Oval", "Oval shaped statium", "Oval's image url");
		lords = new Venue("Lords", "Home of Cricket", "Lords's image url");
		
		venueList.add(melbourne);
		venueList.add(oval);
		venueList.add(lords);
		
//		Exception
		sedney = new Venue();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Test
	@DisplayName("Saves the venue object provided into the database")
	public void test_addVenue_GivenTheVenue_ShouldReturnSavedVenue() {
		
		Mockito.when(venueRepository.save(melbourne)).thenReturn(melbourne);
		
		Venue venueReturnedFromVenueServiceAddMethod = venueService.addVenue(melbourne);
		
		assertNotNull(venueReturnedFromVenueServiceAddMethod);
		assertEquals(melbourne, venueReturnedFromVenueServiceAddMethod);
	}
	
	
	@Test
	@DisplayName("Return List of Venues")
	public void test_findAllVenues_ShouldReturnListOfVenues() {
		
		Mockito.when(venueRepository.findAll()).thenReturn(venueList);
		
		List<Venue> venueListReturnedFromVenueService = venueService.allVenue();
		
		assertNotNull(venueList);
		assertNotNull(venueListReturnedFromVenueService);
		assertEquals(3, venueListReturnedFromVenueService.size());
		assertNotEquals(4, venueListReturnedFromVenueService.size());
		assertEquals(melbourne, venueListReturnedFromVenueService.get(0));
		assertEquals(oval, venueListReturnedFromVenueService.get(1));
		assertEquals(lords, venueListReturnedFromVenueService.get(2));
	}

	
	@Test
	public void test_addCatarer_shouldThrowFieldCannotBeEmptyExceptionForVenue() {
		
		Mockito.when(venueRepository.save(melbourne)).thenThrow(new FieldCannotBeEmptyExceptionForVenue());
		
		assertThrows(FieldCannotBeEmptyExceptionForVenue.class, () -> {
			
			venueService.addVenue(sedney);
			
		});
	
	}
	
	
	@Test
	public void test_allCateres_shouldThrowNoVenuePresentException() {
		
		Mockito.when(venueRepository.findAll()).thenThrow(NoVenuePresentException.class);
		
		assertThrows(NoVenuePresentException.class, () -> venueService.allVenue());
		
	}
	

}
