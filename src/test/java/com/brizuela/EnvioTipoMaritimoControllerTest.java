package com.brizuela;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.brizuela.model.EnvioTipoMaritimo;
import com.brizuela.service.EnvioTipoMaritimoService;
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
public class EnvioTipoMaritimoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioTipoMaritimoService envioBarcoService;

    private static final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicml6dWVsYUBnbWFpbC5jb20iLCJleHAiOjE2ODcyOTg3MjIsInJvbGUiOiJBRE1JTiIsIm5hbWUiOiJqdWxpbyIsInVzZXJJZCI6OTk5OTk5LCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl19.spDg7z7yn0iyy58R4JzcgSyOhYcW1GEN1X2nkVPUXwa7hS_bVTet5Y7HOSUk7bUECNyKnweWpd6WWU5elWu_EA";

    @BeforeEach
    void setup() throws Exception {

        given(this.envioBarcoService.reader())
                .willReturn(Lists.newArrayList(envioBarco()));

        given(this.envioBarcoService.update(envioBarco()))
                .willReturn(envioBarcoUpdate());

        given(this.envioBarcoService.maritimoById(any()))
                .willReturn(Optional.of(envioBarco()));

        doNothing().when(this.envioBarcoService).delete(any());
    }

    private EnvioTipoMaritimo envioBarco() {
        EnvioTipoMaritimo envio = new EnvioTipoMaritimo();
        envio.setCantidadProducto(12);
        envio.setPuertoEntrega("Puerto Acajutla");
        envio.setNumeroFlota("15A");
        envio.setFechaEntrega(new Date());
        envio.setFechaRegistro(new Date());
        envio.setNumeroGuia("199");
        return envio;
    }

    private EnvioTipoMaritimo envioBarcoUpdate() {
        EnvioTipoMaritimo envio = new EnvioTipoMaritimo();
        envio.setId(2L);
        envio.setPuertoEntrega("Puerto Libertad");
        envio.setNumeroFlota("12A");
        envio.setPrecioEnvio(BigDecimal.valueOf(15));
        envio.setCantidadProducto(15);
        envio.setFechaEntrega(new Date());
        envio.setFechaRegistro(new Date());
        envio.setNumeroGuia("155");
        return envio;
    }

    @Test
    void getEnvio() throws Exception {
        mockMvc.perform(get("/api/maritimo")
                        .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void processEnvio() throws Exception {

        mockMvc.perform(
                        post("/api/maritimo")
                                .header("Authorization", token)
                                .content(asJsonString(envioBarco()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateEnvioPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/maritimo/{id}", 2L)
                        .content(asJsonString(envioBarcoUpdate()))
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidadProducto").value("15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precioEnvio").value("15"));
    }

    @Test
    public void deleteEnvio() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/maritimo/{id}", 2L)
                        .header("Authorization", token))
                .andExpect(status().isAccepted());
    }
}
