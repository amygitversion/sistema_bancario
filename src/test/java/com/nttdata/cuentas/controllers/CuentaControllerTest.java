package com.nttdata.cuentas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.cuentas.entities.Cuenta;
import com.nttdata.cuentas.services.CuentaService;
import com.nttdata.cuentas.util.TipoCuenta;
import com.nttdata.movimientos.entities.Movimiento;
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

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class CuentaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CuentaService service;

    @InjectMocks
    private CuentaController controller;

    private JacksonTester<Cuenta> cuentaJacksonTester;


    private Movimiento movimiento;
    private Cuenta cuenta;

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
        movimiento.setFecha("10/12/2022");
        movimiento.setSaldoInicial(10.6);


    }

    @Test
    void deberiaObtenerElDetallePorId() throws Exception {
        // given
        given(service.detalle(1L))
                .willReturn(Optional.of(new Cuenta(Arrays.asList(movimiento))));

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/cuentas/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                cuentaJacksonTester.write(new Cuenta(Arrays.asList(movimiento))).getJson()
        );
    }

    @Test
    void noDeberiaCrearCuentaYaExistente() throws Exception {
        //given
        cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumero("1");
        cuenta.setTipo(TipoCuenta.AHORRO);
        cuenta.setSaldo(25.0);
        cuenta.setEstado(true);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        post("/cuentas/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
       assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
}