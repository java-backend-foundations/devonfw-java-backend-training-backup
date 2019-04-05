package com.devonfw.app.java.order.orderservice.service.api.rest;

import java.time.LocalDate;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.devonfw.module.rest.common.api.RestService;
import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;

/**
 * The service interface for REST calls in order to execute the logic of
 * component {@link Orderservice}.
 */
@Path("/orderservice/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OrderserviceRestService extends RestService {


	/**
	 * Delegates to {@link Orderservice#deleteCustomer}.
	 *
	 * @param id ID of the {@link CustomerEto} to be deleted
	 */
	@DELETE
	@Path("/customer/{id}/")
	public void deleteCustomer(@PathParam("id") long id);

	@GET
	@Path("/items/{name}/")
	public Page <ItemEto> findItemsWithNameLikeOrdered(@PathParam("name") String name);

	@GET
	@Path("/items/{name}")
	public Set <ItemEto> findItemByName(@PathParam("name") String name);

	@POST
	@Path("/items/raisePrice/")
	public void raiseItemPrice(@PathParam("name") String name, Float price);

	@GET
	@Path("/order/find/{date}/{status}")
	public Set <OrderEto> findOrdersByCreationDateAndStatus (@PathParam("date") LocalDate date, @PathParam("status") OrderStatus status);

	@PUT
	@Path("/order/save/")
	public OrderCto saveOrder(OrderCto order);

}
