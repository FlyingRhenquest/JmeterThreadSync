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
 * CheckpointConfigElement wraps a CheckpointObject in a jmeter config element.
 * This allows creation and management of the element during a test. This
 * actually binds a CheckpointObject to a jmeter variable so you can
 * access later from another plugin.
 */

package com.flyingrhenquest.JmeterThreadSync;

import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.testelement.TestListener;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.engine.util.NoThreadClone;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.engine.event.LoopIterationEvent;

public class CheckpointConfigElement extends ConfigTestElement
  implements TestBean, TestListener, NoThreadClone {
  private static final long serialVersionUID = 240L;

  /**
   * Name to bind to in vars
   */
  private String checkpointName;
  
  /**
   * Checkpoint start value
   */
  private int startValue;

  /**
   * CheckpointObject itself. This will be created at test start and
   * the reference will be bound to a variable in vars. You can't access
   * it directly from jmeter using this object.
   */

  private CheckpointObject checkpoint;

  public String getCheckpointName() {
    return checkpointName;
  }

  public int getStartValue() {
    return startValue;
  }

  public void setCheckpointName(String checkpointName) {
    this.checkpointName = checkpointName;
  }

  public void setStartValue(int startValue) {
    this.startValue = startValue;
  }


  /**
   * @inheritdoc
   */

  public void testStarted() {
    checkpoint = new CheckpointObject(startValue);
    JMeterVariables vars = JMeterContextService.getContext().getVariables();
    vars.putObject(checkpointName, (Object) checkpoint);
  }

  /**
   * @inheritdoc
   */

  public void testStarted(String host) {
    testStarted();
  }

  /**
   * @inheritdoc
   */

  public void testEnded() {
  }

  /**
   * @inheritdoc
   */

  public void testEnded(String host) {

  }

  /**
   * @inheritdoc
   */

  public void testIterationStart(LoopIterationEvent event) {

  }

}