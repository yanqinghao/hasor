/*
 * Copyright 2008-2009 the original author or authors.
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
package net.hasor.core;
import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * 使用 ID 方式进行依赖注入-based {@linkplain Qualifier qualifier}.
 *
 * <p>Example usage:
 *
 * <pre>
 *   public class Car {
 *     &#064;Inject <b>@ID("driver")</b> Seat driverSeat;
 *     &#064;Inject <b>@ID("passenger")</b> Seat passengerSeat;
 *     ...
 *   }</pre>
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface ID {
    /**如果同类型有多个注册可以使用该值进行区分。*/
    public String value() default "";
}