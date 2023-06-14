package org.bedu.java.backend.Postwork.controllers;

import org.bedu.java.backend.Postwork.model.Cliente;
import org.bedu.java.backend.Postwork.services.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "bedu.org/rest", uriPort = 80)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Obtener cliente especifico")
    public void testGetCliente() throws Exception {
        Cliente cliente = new Cliente(1L, "Ricardo Rios","ricardo@prueba.com","10000","Isable la catolica 89");
        when(clienteService.getCliente(eq(1L))).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cliente.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(cliente.getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.correoContacto").value(cliente.getCorreoContacto()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroEmpleados").value(cliente.getNumeroEmpleados()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.direccion").value(cliente.getDireccion()))
                   .andDo(document("cliente/get-cliente",
                        responseFields(
                                fieldWithPath("id").description("ID del cliente"),
                                fieldWithPath("nombre").description("Nombre del cliente"),
                                fieldWithPath("correoContacto").description("Cuenta de correo"),
                                fieldWithPath("numeroEmpleados").description("Número de empleados"),
                                fieldWithPath("direccion").description("Dirección del cliente")
                                )));
    }

   @Test
    @DisplayName("Obtener toda la lista")
    void getClientes() throws Exception {

        List<Cliente> clientes = Arrays.asList(
                Cliente.builder().id(1L).nombre("Dinenno").direccion("Ciudad Universitaria 1").numeroEmpleados("1000").correoContacto("dinenno@prueba.com").build(),
                Cliente.builder().id(2L).nombre("Salvio").direccion("Ciudad Universitaria 2").numeroEmpleados("2000").correoContacto("salvio@prueba.com").build(),
                Cliente.builder().id(3L).nombre("Mohammed").direccion("Ciudad Universitaria 3").numeroEmpleados("3000").correoContacto("mohammed@cprueba.com").build()
        );

        given(clienteService.getClientes()).willReturn(clientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(clientes.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nombre").value(clientes.get(0).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].correoContacto").value(clientes.get(0).getCorreoContacto()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].numeroEmpleados").value(clientes.get(0).getNumeroEmpleados()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].direccion").value(clientes.get(0).getDireccion()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(clientes.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nombre").value(clientes.get(1).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].correoContacto").value(clientes.get(1).getCorreoContacto()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].numeroEmpleados").value(clientes.get(1).getNumeroEmpleados()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].direccion").value(clientes.get(1).getDireccion()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(clientes.get(2).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].nombre").value(clientes.get(2).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].correoContacto").value(clientes.get(2).getCorreoContacto()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].numeroEmpleados").value(clientes.get(2).getNumeroEmpleados()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].direccion").value(clientes.get(2).getDireccion()))
                //Generación de documentación
                  .andDo(document("cliente/get-clientes",
                         responseFields(
                            fieldWithPath("[].id").description("Id del cliente"),
                            fieldWithPath("[].nombre").description("Nombre del cliente"),
                            fieldWithPath("[].correoContacto").description("Correo del cliente"),
                            fieldWithPath("[].numeroEmpleados").description("Numero de empleados del cliente"),
                            fieldWithPath("[].direccion").description("Direccion del cliente")
                         )));
    }

  @Test
  @DisplayName("Guardar cliente")
    public void testSaveCliente() throws Exception {
        Cliente cliente = new Cliente(1L, "Ricardo Rios","prueba@prueba.com","10000","Isable la catolica 89");
        when(clienteService.saveCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"id\": 1, \"nombre\": \"Ricardo Rios\", \"correoContacto\":\"prueba@prueba.com\", \"numeroEmpleados\":10000, \"direccion\":\"Isable la catolica 89\"}"))
                .andExpect(status().isCreated())
                .andDo(document("cliente/post-cliente",
                        requestFields(
                                fieldWithPath("id").description("Identificador de cliente nuevo"),
                                fieldWithPath("nombre").description("El nombre del cliente nuevo"),
                                fieldWithPath("correoContacto").description("La dirección de correo del cliente nuevo"),
                                fieldWithPath("numeroEmpleados").description("Trabajadores del cliente"),
                                fieldWithPath("direccion").description("La dirección del cliente nuevo")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    @DisplayName("Actualizar cliente")
    public void testUpdateCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/cliente/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"nombre\": \"Ricardo Rios\", \"correoContacto\":\"prueba@prueba.com\", \"numeroEmpleados\":10000, \"direccion\":\"Isable la catolica 89\"}"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(document("cliente/put-cliente",
                        requestFields(
                                fieldWithPath("id").description("El identificador del nuevo a actualizar"),
                                fieldWithPath("nombre").description("El nombre del cliente"),
                                fieldWithPath("direccion").description("La dirección del cliente"),
                                fieldWithPath("correoContacto").description("El correo electrónico del cliente"),
                                fieldWithPath("numeroEmpleados").description("Trabajadores del cliente")
                        )
        ));
        verify(clienteService, times(1)).updateCliente(any(Cliente.class));
    }

    @Test
    @DisplayName("Elimina cliente")
    public void testEliminaCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/{clienteId}", 1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("cliente/delete-cliente"
                ));
        verify(clienteService, times(1)).deleteCliente(eq(1L));
    }


}
