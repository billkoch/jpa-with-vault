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

import io.billkoch.examples.domain.Person;
import io.billkoch.examples.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private PersonRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    while(true) {
      log.debug("Persisting a new person");
      personRepository.save(new Person(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(8)));
      log.debug("Now there are {} persisted people", personRepository.count());
      long sleepTime = RandomUtils.nextLong(1000L, 6000L);
      log.debug("Sleeping for {} milliseconds", sleepTime);
      Thread.sleep(sleepTime);
    }
  }
}
