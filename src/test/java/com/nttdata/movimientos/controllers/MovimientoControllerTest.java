package com.nttdata.movimientos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.movimientos.entities.Movimiento;
import com.nttdata.movimientos.services.MovimientoService;
import com.nttdata.movimientos.util.TipoMovimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class MovimientoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MovimientoService service;

    @InjectMocks
    private MovimientoController controller;

    private JacksonTester<Movimiento> movimientoJacksonTester;


    private Movimiento movimiento;


    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice()
                .addFilters()
                .build();

        movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setTipo(TipoMovimiento.CRE);
        movimiento.setValorMovimiento(50.0);
        movimiento.setFecha(LocalDate.parse("10/12/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        movimiento.setSaldoInicial(10.6);


    }

    @Test
    void deberiaObtenerElDetallePorId() throws Exception {
        // given
        given(service.detalle(1L))
                .willReturn(Optional.of(movimiento));

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/movimientos/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                movimientoJacksonTester.write(movimiento).getJson()
        );
    }


    @Test
    void deberiaListar() throws Exception {

        // given
        given(service.listar())
                .willReturn(Arrays.asList(movimiento));

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/movimientos")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}