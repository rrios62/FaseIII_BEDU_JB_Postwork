package org.bedu.java.backend.Postwork.controllers;

import org.bedu.java.backend.Postwork.model.Producto;
import org.bedu.java.backend.Postwork.services.ProductoService;

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

import java.time.LocalDate;
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
@WebMvcTest(ProductoController.class)
class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Obtener producto específico")
    public void testGetProducto() throws Exception {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Coca cola")
                .categoria("Refrescos")
                .precio(15.5f)
                .numeroRegistro("100-00-0001")
                .fechaCreacion(LocalDate.now())
                .build();

        when(productoService.getProducto(eq(1L))).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/producto/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(producto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(producto.getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria").value(producto.getCategoria()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precio").value(producto.getPrecio()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroRegistro").value(producto.getNumeroRegistro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaCreacion").value(producto.getFechaCreacion().toString()))
                .andDo(document("producto/get-producto",
                        responseFields(
                                fieldWithPath("id").description("ID del producto"),
                                fieldWithPath("nombre").description("Nombre del producto"),
                                fieldWithPath("categoria").description("Categoría del producto"),
                                fieldWithPath("precio").description("Precio del producto"),
                                fieldWithPath("numeroRegistro").description("Número de registro del producto"),
                                fieldWithPath("fechaCreacion").description("Fecha de creación del producto")
                        )));
    }

    @Test
    @DisplayName("Obtener todos los productos")
    void getProductos() throws Exception {
        List<Producto> productos = Arrays.asList(
                Producto.builder()
                        .id(1L)
                        .nombre("Coca Cola")
                        .categoria("Refrescos")
                        .precio(15.5f)
                        .numeroRegistro("100-00-0001")
                        .fechaCreacion(LocalDate.now())
                        .build(),
                Producto.builder()
                        .id(2L)
                        .nombre("Chetos")
                        .categoria("Botanas")
                        .precio(18.0f)
                        .numeroRegistro("200-00-0001")
                        .fechaCreacion(LocalDate.now())
                        .build()
        );

        when(productoService.getProductos()).thenReturn(productos);

        mockMvc.perform(get("/producto/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(productos.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nombre").value(productos.get(0).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].categoria").value(productos.get(0).getCategoria()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].precio").value(productos.get(0).getPrecio()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].numeroRegistro").value(productos.get(0).getNumeroRegistro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fechaCreacion").value(productos.get(0).getFechaCreacion().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(productos.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nombre").value(productos.get(1).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].categoria").value(productos.get(1).getCategoria()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].precio").value(productos.get(1).getPrecio()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].numeroRegistro").value(productos.get(1).getNumeroRegistro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].fechaCreacion").value(productos.get(1).getFechaCreacion().toString()))
                .andDo(document("producto/get-productos",
                        responseFields(
                                fieldWithPath("[].id").description("ID del producto"),
                                fieldWithPath("[].nombre").description("Nombre del producto"),
                                fieldWithPath("[].categoria").description("Categoría del producto"),
                                fieldWithPath("[].precio").description("Precio del producto"),
                                fieldWithPath("[].numeroRegistro").description("Número de registro del producto"),
                                fieldWithPath("[].fechaCreacion").description("Fecha de creación del producto")
                        )));
    }
    @Test
    @DisplayName("Guardar producto")
    public void testSaveProducto() throws Exception {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Coca cola")
                .categoria("Refrescos")
                .precio(15.5f)
                .numeroRegistro("100-00-0001")
                .fechaCreacion(LocalDate.now())
                .build();

        given(productoService.saveProducto(any(Producto.class))).willReturn(producto);

        mockMvc.perform(MockMvcRequestBuilders.post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"nombre\": \"Coca Cola\", \"categoria\": \"Refresco\", \"precio\": 15.5, \"numeroRegistro\": \"100-00-0001\", \"fechaCreacion\": \"2023-06-14\"}"))
                .andExpect(status().isCreated())
                .andDo(document("producto/post-producto",
                        requestFields(
                                fieldWithPath("id").description("ID del nuevo producto"),
                                fieldWithPath("nombre").description("Nombre del nuevo producto"),
                                fieldWithPath("categoria").description("Categoría del nuevo producto"),
                                fieldWithPath("precio").description("Precio del nuevo producto"),
                                fieldWithPath("numeroRegistro").description("Número de registro del nuevo producto"),
                                fieldWithPath("fechaCreacion").description("Fecha de creación del nuevo producto")
                        )));
    }

    @Test
    @DisplayName("Actualizar producto")
    public void testUpdateProducto() throws Exception {
        /*Producto producto = Producto.builder()
                .id(1L)
                .nombre("Pepsi Cola")
                .categoria("Refrescos")
                .precio(15.5f)
                .numeroRegistro("100-00-0002")
                .fechaCreacion(LocalDate.now())
                .build();

        given(productoService.updateProducto(any(Producto.class))).willReturn(producto);*/

        mockMvc.perform(MockMvcRequestBuilders.put("/producto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"nombre\": \"Pepsi cola\", \"categoria\": \"Refrescos\", \"precio\": 15.5, \"numeroRegistro\": \"100-00-0002\"}")) //, "fechaCreacion": "2023-06-14"
                .andExpect(status().isNoContent())
                .andDo(document("producto/put-producto",
                        requestFields(
                                fieldWithPath("id").description("ID del producto a actualizar"),
                                fieldWithPath("nombre").description("Nuevo nombre del producto"),
                                fieldWithPath("categoria").description("Nueva categoría del producto"),
                                fieldWithPath("precio").description("Nuevo precio del producto"),
                                fieldWithPath("numeroRegistro").description("Nuevo número de registro del producto"),
                                fieldWithPath("fechaCreacion").description("Nueva fecha de creación del producto")
                        )));
    }

    @Test
    @DisplayName("Eliminar producto")
    public void testDeleteProducto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/producto/1"))
                .andExpect(status().isNoContent())
                .andDo(document("producto/delete-producto"));
    }

}