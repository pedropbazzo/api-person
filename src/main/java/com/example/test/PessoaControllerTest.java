package com.example.test;

import com.example.model.Pessoa;
import com.example.test.AbstractTest;
import com.example.utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PessoaControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getAllPessoas() throws Exception {
        String uri = "/pessoas";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Pessoa[] pessoaslist = super.mapFromJson(content, Pessoa[].class);
        assertTrue(pessoaslist.length > 0);
    }

    @Test
    public void getPessoasByName() throws Exception {
        String name = "Leonardo";
        String uri = "/pessoas/findByName/"+name;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Pessoa[] pessoaslist = super.mapFromJson(content, Pessoa[].class);
        assertTrue(pessoaslist.length > 0);
    }

    public void createPessoa() throws Exception {
        String uri = "/pessoas";
        Pessoa p = new Pessoa();
        p.setId(11);
        p.setNome("Geovane Ferreira Melo");
        p.setCpf(Utils.geraCPF());
        p.setEmail("geovane_ferreira_melo@gmail.com");
        p.setSexo("M");
        p.setDataNascimento(LocalDate.now().toString());
        p.setNaturalidade("Florianopolis");
        p.setNacionalidade("Brasileiro");
        String inputJson = super.mapToJson(p);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }
    @Test
    public void updateProduct() throws Exception {
        String uri = "/pessoas/2";
        Pessoa p = new Pessoa();
        p.setNome("Liandra Borges");
        p.setCpf("345.128.020-53");
        p.setSexo("F");
        p.setEmail("liandra_borges@gmail.com");
        p.setDataNascimento(LocalDate.now().toString());
        p.setNacionalidade("Brasileira");
        p.setNaturalidade("Curitiba");
        String inputJson = super.mapToJson(p);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void deleteProduct() throws Exception {
        String uri = "/pessoas/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}
