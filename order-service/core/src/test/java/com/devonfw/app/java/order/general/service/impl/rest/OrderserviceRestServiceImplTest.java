package com.devonfw.app.java.order.general.service.impl.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.catalina.filters.CorsFilter;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.impl.OrderserviceImpl;
import com.devonfw.app.java.order.orderservice.service.impl.rest.OrderserviceRestServiceImpl;

@Transactional
public class OrderserviceRestServiceImplTest {

	private MockMvc mockMvc;

	@Mock
	private OrderserviceRestServiceImpl orderServiceRest;

	@InjectMocks
	private OrderserviceImpl orderController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(orderController)
				.addFilters(new CorsFilter())
				.build();
	}

	@Test
	public void shouldFindItemsByDateAndStatus() throws Exception {
		//given
		OrderStatus status = OrderStatus.NEW;
		LocalDate date = LocalDate.of(2019, 03, 15);
		Set<OrderEto> testData = prepareOrderTestData();
		when(orderServiceRest.findOrdersByCreationDateAndStatus(date, status)).thenReturn(testData);

		//when
		 mockMvc.perform(get("/order-service/services/rest/orderservice/v1/order/find/null/NEW"))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
         .andExpect((ResultMatcher) jsonPath("$.date", is(date)))
         .andExpect((ResultMatcher) jsonPath("$.status", is(status)));

			//then

		 verify(orderServiceRest, times(1)).findOrdersByCreationDateAndStatus(date, status);
		 verifyNoMoreInteractions(orderServiceRest);


	}

	private Set<OrderEto> prepareOrderTestData() {
		//given
				OrderEto orderToFind = new OrderEto();
				OrderStatus status = OrderStatus.NEW;
				orderToFind.setStatus(status);
				LocalDate creationDate = LocalDate.of(2019, 03, 15);
				orderToFind.setCreationDate(creationDate);

				Set<OrderEto> result = new HashSet<OrderEto>();
				result.add(orderToFind);
				return result;

	}

}
