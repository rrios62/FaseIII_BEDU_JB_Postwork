package org.bedu.java.backend.Postwork.controllers;

import org.bedu.java.backend.Postwork.model.Etapa;
import org.bedu.java.backend.Postwork.services.EtapaService;

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
@WebMvcTest(EtapaController.class)
class EtapaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EtapaService etapaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Obtener etapa específica")
    public void testGetEtapa() throws Exception {

        Etapa etapa = new Etapa(1L, "Etapa 1", 1);

        when(etapaService.getEtapa(eq(1L))).thenReturn(Optional.of(etapa));

        mockMvc.perform(get("/etapa/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.etapaID").value(etapa.getEtapaID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(etapa.getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orden").value(etapa.getOrden()))
                    .andDo(document("etapa/get-etapa",
                responseFields(
                        fieldWithPath("etapaID").description("ID de la etapa"),
                        fieldWithPath("nombre").description("Nombre de la etapa"),
                        fieldWithPath("orden").description("Numero de la orden")
                )));
    }
    @Test
    @DisplayName("Obtener toda la lista")
    void getEtapas() throws Exception {

        List<Etapa> etapas = Arrays.asList(
                Etapa.builder()
                        .etapaID(1L)
                        .nombre("Etapa 1")
                        .orden(1)
                        .build(),
                Etapa.builder()
                        .etapaID(2L)
                        .nombre("Etapa 2")
                        .orden(2)
                        .build(),
                Etapa.builder()
                        .etapaID(3L)
                        .nombre("Etapa 3")
                        .orden(3)
                        .build());

        given(etapaService.getEtapas()).willReturn(etapas);

        mockMvc.perform(MockMvcRequestBuilders.get("/etapa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].etapaID").value(etapas.get(0).getEtapaID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nombre").value(etapas.get(0).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].orden").value(etapas.get(0).getOrden()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].etapaID").value(etapas.get(1).getEtapaID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nombre").value(etapas.get(1).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].orden").value(etapas.get(1).getOrden()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].etapaID").value(etapas.get(2).getEtapaID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].nombre").value(etapas.get(2).getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].orden").value(etapas.get(2).getOrden()))
                    .andDo(document("etapa/get-etapas",
                        responseFields(
                                fieldWithPath("[].etapaID").description("Id de la etapa"),
                                fieldWithPath("[].nombre").description("Nombre de la etapa"),
                                fieldWithPath("[].orden").description("Numero de orden")
                        )));
        ;
    }

    @Test
    @DisplayName("Guardar etapa")
    public void testSaveEtapa() throws Exception {
        Etapa etapa = new Etapa(1L, "Etapa 1",1);
        when(etapaService.saveEtapa(any(Etapa.class))).thenReturn(etapa);

        mockMvc.perform(MockMvcRequestBuilders.post("/etapa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"etapaID\": 1, \"nombre\": \"Etapa 1\", \"orden\": 1}"))
                .andExpect(status().isCreated())
                    .andDo(document("etapa/post-etapa",
                        requestFields(
                                fieldWithPath("etapaID").description("Identificador de la nueva etapa"),
                                fieldWithPath("nombre").description("Nombre de la etapa nueva"),
                                fieldWithPath("orden").description("Numero de orden de la nueva etapa")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }


    @Test
    @DisplayName("Actualizar etapa")
    public void testUpdateEtapa() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/etapa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"etapaID\": 1, \"nombre\": \"Etapa 1\", \"orden\": 1}"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andDo(document("etapa/put-etapa",
                        requestFields(
                                fieldWithPath("etapaID").description("Identificador de la etapa a actualizar"),
                                fieldWithPath("nombre").description("El nombre de la etapa"),
                                fieldWithPath("orden").description("Numero de orden")
                        )
                    ));
        verify(etapaService, times(1)).updateEtapa(any(Etapa.class));
    }

    @Test
    @DisplayName("Elimina etapa")
    public void testEliminaEtapa() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/etapa/{etapaId}",1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("cliente/delete-cliente"
                ));
        verify(etapaService, times(1)).deleteEtapa(eq(1L));
    }
}