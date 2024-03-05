package org.mindustack.minterpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MemoryFactory {
  Map<Long, Memory> memories = new HashMap<>();

  MemoryFactory() {
  }

  public Memory getMem(Variable memoryIndex) {
    if (memories.containsKey(memoryIndex.asInteger())) {
      return memories.get(memoryIndex.asInteger());
    }
    Memory m = new Memory();
    memories.put(memoryIndex.asInteger(), m);
    return m;

  }

}

public class Memory {
  List<Double> mem;

  protected Memory() {
    mem = new ArrayList<Double>(Collections.nCopies(512, 0.0));
  }

  public void read(Variable ret, Variable index) {
    ret.value = mem.get((int) index.asInteger());
  }

  public void write(Variable source, Variable index) {
    mem.set((int) index.asInteger(), source.value);
  }
}
