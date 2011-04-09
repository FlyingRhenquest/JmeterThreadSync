/**
 * Copyright 2011 Bruce Ide
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Jmeter UI Stuff for CheckpointConfigElement
 */

package com.flyingrhenquest.JmeterThreadSync;

import java.beans.PropertyDescriptor;
import org.apache.jmeter.testbeans.BeanInfoSupport;

public class CheckpointConfigElementBeanInfo extends BeanInfoSupport {

  public CheckpointConfigElementBeanInfo() {
    super(CheckpointConfigElement.class);

    createPropertyGroup("Checkpoint Info",
                        new String[] {
                          "checkpointName",
                          "startValue"
                        });
    PropertyDescriptor p;
    p = property("checkpointName");
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "checkpoint");

    p = property("startValue");
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "1");
  }

}