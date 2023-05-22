package com.brizuela;

import com.brizuela.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.brizuela.model.EnvioTipoTerrestre;
import com.brizuela.service.EnvioTipoTerrestreService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnvioTerrestreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicml6dWVsYUBnbWFpbC5jb20iLCJleHAiOjE2ODcyOTg3MjIsInJvbGUiOiJBRE1JTiIsIm5hbWUiOiJqdWxpbyIsInVzZXJJZCI6OTk5OTk5LCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl19.spDg7z7yn0iyy58R4JzcgSyOhYcW1GEN1X2nkVPUXwa7hS_bVTet5Y7HOSUk7bUECNyKnweWpd6WWU5elWu_EA";

    @MockBean
    private EnvioTipoTerrestreService envioCamionService;

    @BeforeEach
    void setup() throws Exception {

        given(this.envioCamionService.reader())
                .willReturn(Lists.newArrayList(envioCamion()));

        given(this.envioCamionService.update(envioCamion()))
                .willReturn(envioCamionUpdate());

        given(this.envioCamionService.terrestreById(any()))
                .willReturn(Optional.of(envioCamion()));

        doNothing().when(this.envioCamionService).delete(any());
    }

    private EnvioTipoTerrestre envioCamion() {
        EnvioTipoTerrestre envio = new EnvioTipoTerrestre();
        envio.setBodegaEntrega("San Salvador");
        envio.setNumeroPlaca("ASD123");
        envio.setCantidadProducto(19);
        envio.setFechaEntrega(new Date());
        envio.setFechaRegistro(new Date());
        envio.setNumeroGuia("1234567890");
        envio.setTipoProducto("Ventas");
        envio.setPrecioEnvio(BigDecimal.valueOf(12));
        envio.setPrecioOriginal(BigDecimal.valueOf(12));
        return envio;
    }

    private EnvioTipoTerrestre envioCamionUpdate() {
        EnvioTipoTerrestre envio = new EnvioTipoTerrestre();
        envio.setId(2L);
        envio.setBodegaEntrega("Santa Ana");
        envio.setNumeroPlaca("ASD123");
        envio.setTipoProducto("Ventas");
        envio.setPrecioEnvio(BigDecimal.valueOf(12));
        envio.setPrecioOriginal(BigDecimal.valueOf(12));
        envio.setCantidadProducto(12);
        envio.setFechaEntrega(new Date());
        envio.setFechaRegistro(new Date());
        envio.setNumeroGuia("1234567890");
        return envio;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getEnvio() throws Exception {
        mockMvc.perform(get("/api/terrestre")
                        .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void processEnvio() throws Exception {

        mockMvc.perform(
                        post("/api/terrestre")
                                .header("Authorization", token)
                                .content(asJsonString(envioCamion()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateEnvioPI() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/terrestre/{id}", 1L)
                                .content(asJsonString(envioCamionUpdate()))
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidadProducto").value("12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precioEnvio").value("12"));
    }

    @Test
    public void deleteEnvio() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/terrestre/{id}", 1L)
                                .header("Authorization", token))
                .andExpect(status().isAccepted());
    }
}
