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
package io.billkoch.examples.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "people")
@Data
@Setter(AccessLevel.PROTECTED)
public class Person {
  @Id
  @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "sequence", sequenceName = "people_id_seq", allocationSize = 10)
  private Long id;

  private final String lastName;

  private final String firstName;
}
