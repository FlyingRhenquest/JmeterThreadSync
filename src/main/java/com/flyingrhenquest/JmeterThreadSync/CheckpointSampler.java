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
 * Checkpoint sampler takes a named checkpoint object and calls its
 * decrement method in sample. This will cause the thread to pause unless
 * the checkpoint object has reached zero, in which case it will cause
 * all previously waited threads to start again.
 */

package com.flyingrhenquest.JmeterThreadSync;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.samplers.Entry;

public class CheckpointSampler extends AbstractSampler implements Sampler, TestBean {
  private String checkpointName;
  private static final long serialVersionUID = 240L;

  public String getCheckpointName() {
    return checkpointName;
  }

  public void setCheckpointName(String checkpointName) {
    this.checkpointName = checkpointName;
  }

  public SampleResult sample(Entry e) {
    SampleResult retval = new SampleResult();
    JMeterVariables vars = JMeterContextService.getContext().getVariables();
    CheckpointObject checkpoint = (CheckpointObject) vars.getObject(checkpointName);
    if (null == checkpoint) {
      retval.setSampleLabel("ERROR: No CheckpointConfig object with a name of " + checkpointName + " exists in this test!");
      retval.setErrorCount(1);
      retval.setSuccessful(false);
    } else {
      retval.setSampleLabel("Current Checkpoint value is: " + checkpoint.toString() + "... going to sleep until it reaches 0...");
      retval.setSuccessful(true);
      checkpoint.decrement();
    }
    return retval;
  }

}