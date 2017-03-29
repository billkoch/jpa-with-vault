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
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "people")
@Data
@Setter(AccessLevel.PROTECTED)
public class Person {
  @Id
  @GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;

  private final String lastName;

  private final String firstName;
}
