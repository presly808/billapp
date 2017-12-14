/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.artcode.billapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.CustomerRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRepository customerRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		customerRepository.deleteAll();

	}

	@Test
	public void shouldReturnOneUser() throws Exception {
		Customer entity = new Customer();
		entity.setName("Ivan");
		entity.setPass("ololo");
		entity.setPhone("123456789012");
		customerRepository.save(entity);

		mockMvc.perform(get("/customer").param("name","Ivan"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Ivan"));

	}

	@Test
	public void shouldReturnTwoUsers() throws Exception {
		Customer entity = new Customer();
		entity.setName("Oleg");
		entity.setPass("pass");
		entity.setPhone("123456789012");
		customerRepository.save(entity);
		Customer entity2 = new Customer();
		entity2.setName("Andrii");
		entity2.setPass("pass");
		entity2.setPhone("123456789013");
		assertNotNull("Customer repository not found!", customerRepository);
		customerRepository.save(entity2);

		mockMvc.perform(get("/customers"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(2)));
	}
}