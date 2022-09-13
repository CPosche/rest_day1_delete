package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("emp")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmps(){
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpById(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.getByID(id))).build();
    }

    @GET
    @Path("highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestSalery(){
        return Response.ok().entity(GSON.toJson(FACADE.getHighestSaleryEmp())).build();
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpByName(@PathParam("name") String name){
        return Response.ok().entity(GSON.toJson(FACADE.getEmployeesByName(name))).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createEmployee(String jsonInput){
        EmployeeDTO employeeDTO = GSON.fromJson(jsonInput, EmployeeDTO.class);
        EmployeeDTO returned = FACADE.create(employeeDTO, 20.5f);
        return Response.ok().entity(returned).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(String jsonInput, @PathParam("id") int id, @QueryParam("salery") float salery){
        EmployeeDTO employeeDTO = GSON.fromJson(jsonInput, EmployeeDTO.class);
        employeeDTO.setId(id);
        EmployeeDTO returned = FACADE.update(employeeDTO, salery);
        return Response.ok().entity(returned).build();
    }

}
