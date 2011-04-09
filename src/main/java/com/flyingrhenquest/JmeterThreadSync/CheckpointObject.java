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
 * An object we can wait() on. This object contains a start value which
 * it is initialized to, a current value and a decrement method. Each time
 * the decrement function is called, it checks to see if the current value
 * is zero after being decremented. If not, it waits() on this object. If
 * it is zero, it calls notifyAll() to awaken all waiters, and resets its
 * current value to the start value.
 */

package com.flyingrhenquest.JmeterThreadSync;

public class CheckpointObject {

  private int startValue;
  private int currentValue;

  public CheckpointObject(int startValue) {
    this.startValue = startValue;
    this.currentValue = startValue;
  }

  public int getStartValue() {
    return startValue;
  }

  public synchronized int getCurrentValue() {
    return currentValue;
  }

  public synchronized String toString() {
    return Integer.toString(currentValue);
  }

  /**
   * Decrement functions as noted in the header. It returns false
   * if currentValue has not yet reached 0 and true if it has.
   */

  public synchronized boolean decrement() {
    boolean retval = false;
      currentValue--;    
      if (0 == currentValue) {
        retval = true;
        currentValue = startValue; 
        notifyAll();
      } else {
      try {
        wait();
      } catch (InterruptedException e) {
        // Well we stopped waiting (Maybe should log this?)
      }
    }

    return retval;
  }
  
}