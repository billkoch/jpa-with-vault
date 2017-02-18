/*
 * Copyright 2017 Bill Koch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.billkoch.examples;

import static org.assertj.core.api.Assertions.assertThat;

import io.billkoch.examples.domain.Person;
import io.billkoch.examples.repositories.PersonRepository;
import io.billkoch.examples.support.DataJpaIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaIntegrationTest
public class PersonPersistenceTests {

  @Autowired private PersonRepository uut;

  @Test
  public void savesPeople() {
    assertThat(uut.count()).isEqualTo(0L);

    Person newPerson = new Person("Bunny", "Bugs");
    Person persistedPerson = uut.save(newPerson);

    assertThat(uut.count()).isEqualTo(1L);
    assertThat(uut.findOne(persistedPerson.getId())).isEqualTo(newPerson);
  }
}
