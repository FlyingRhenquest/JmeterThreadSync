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
 * Jmeter GUI stuff for checkpoint sampler
 */

package com.flyingrhenquest.JmeterThreadSync;

import java.beans.PropertyDescriptor;
import org.apache.jmeter.testbeans.BeanInfoSupport;

public class CheckpointSamplerBeanInfo extends BeanInfoSupport {
  public CheckpointSamplerBeanInfo() {
    super(CheckpointSampler.class);

    createPropertyGroup("Checkpoint Info",
                        new String[] {
                          "checkpointName"
                        });
    PropertyDescriptor p;
    p = property("checkpointName");
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "checkpoint");
  }
}