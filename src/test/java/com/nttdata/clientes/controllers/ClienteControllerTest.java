package com.nttdata.clientes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.clientes.entities.Cliente;
import com.nttdata.clientes.services.ClienteService;
import com.nttdata.cuentas.entities.Cuenta;
import com.nttdata.cuentas.util.TipoCuenta;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ClienteService service;

    @InjectMocks
    private ClienteController controller;

    private JacksonTester<Cliente> clienteJacksonTester;

    private Cuenta cuenta;

    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice()
                .addFilters()
                .build();

        cuenta = new Cuenta();
        cuenta.setNumero("1234");
        cuenta.setTipo(TipoCuenta.AHORRO);
        cuenta.setSaldo(25.0);
        cuenta.setEstado(true);
    }

    @Test
    void deberiaObtenerElDetallePorId() throws Exception {

        // given
        given(service.detalle(1L))
                .willReturn(Optional.of(new Cliente("123", true, Arrays.asList(cuenta))));

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/clientes/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                clienteJacksonTester.write( new Cliente("123", true, Arrays.asList(cuenta))).getJson()
        );
    }

}