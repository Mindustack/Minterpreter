package org.mindustack.minterpreter;

import java.util.HashMap;
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
